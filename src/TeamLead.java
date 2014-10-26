import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/24/14.
 */
public class TeamLead extends Developer {

    private boolean managerMeeting;

    public TeamLead(Team team, int devId, CountDownLatch startSignal) {
        super(team, devId, startSignal);
        this.managerMeeting = false;
    }

    @Override
    public void run() {

        // Wait for start signal
        try {
            getStartSignal().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Random r = new Random();

        //Randomly decide when to arrive
        try {
            Thread.sleep(r.nextInt(30)*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Take care of arrival tasks
        getTeam().getOffice().getLogger().logAtTime(getName() + " arrives at the office.");
        setArrivalTime(getTeam().getOffice().getTimeTracker().getCurrTime());

        while ( getTeam().getOffice().getTimeTracker().getCurrTime() - getArrivalTime() < 4800 ||
                getTeam().getOffice().getTimeTracker().getCurrTime() < 5100 ) {



            // Check for all time sensitive things first (Lunch, Meetings, Etc)
            // Also check to see of the time sensitive things have already been done,
            // if so don't bother checking the time.
            if (!ateLunch() && getTeam().getOffice().getTimeTracker().getRealCurrTime() >= 7200) {

                long startTime = getTeam().getOffice().getTimeTracker().getCurrTime();

                getTeam().getOffice().getLogger().logAtTime(getName() + " goes to lunch.");
                try {
                    Thread.sleep( 300 + r.nextInt((int) (7500 -
                            getTeam().getOffice().getTimeTracker().getRealCurrTime())));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eatLunch();

                getTeam().getOffice().getLogger().logAtTime(getName() + " returns from lunch.");
                addTimeLunch(getTeam().getOffice().getTimeTracker().getRealCurrTime() - startTime);
            }

            // See if anyone is waiting to ask you a question
            // if so then attempt to answer the question
            // if can't answer then request an answer form the manager

            // If there are no time sensitive things then the "else" will determine
            // Whether or not a question should be asked.

            // if so then ask, otherwise loop again.
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        // Take care of pre-departure tasks
        getTeam().getOffice().getLogger().logAtTime(getName() + " leaves the office.");
        setEndTime(getTeam().getOffice().getTimeTracker().getCurrTime());
    }
}
