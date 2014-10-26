import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/24/14.
 */
public class Developer extends Employee {

    private int devId;
    private Team team;

    private int timeWaiting;

    private boolean morningMeeting;


    public Developer(Team team, int devId, CountDownLatch startSignal) {
        super(("Developer " + team.getTeamId() + "" + devId), startSignal);
        this.devId = devId;
        this.team = team;
        this.timeWaiting = 0;

        this.morningMeeting = false;

    }


    public void answerQuestion() {

    }
    
    public Team getTeam()
    {
    	return team;
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

        getTeam().getOffice().getConferenceRoom().setupTeamMeeting(getTeam().getTeamId());
        getTeam().getOffice().getConferenceRoom().holdMeeting(getTeam().getTeamId(), getTeam().getOffice(), this);

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

            // If there are no time sensitive things then the "else" will determine
            // Whether or not a question should be asked.
            else {
                if (r.nextInt(100) < 1) {
                    long startQ = getTeam().getOffice().getTimeTracker().getCurrTime();
                    getTeam().getOffice().getLogger().logAtTime(getName() +
                            " asks team lead a question.");
                    getTeam().getTeamLead().askQuestion();
                    getTeam().getOffice().getLogger().logAtTime(
                            Thread.currentThread().getName() +"'s question has been answered.");
                    addWaitingTime(getTeam().getOffice().getTimeTracker().getCurrTime() - startQ);
                }
            }

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

    public void addWaitingTime(long tMillis) {
        timeWaiting += tMillis;
    }

    public int getTimeWaiting() {
        return timeWaiting;
    }

    public int getDevId()
    {
        return devId;
    }

    @Override
    public void printLog() {
        System.out.println("------ " + getName() + " Log ------");
        System.out.println("  Total time working: " + (getEndTime() - getArrivalTime())/10 + " minutes.");
        System.out.println("  Time spent at Lunch: " + getTimeLunch()/10 + " minutes.");
        System.out.println("  Time spent in meetings: " + getTimeMeeting()/10 + " minutes.");
        System.out.println("  Time spent waiting for answers: " + timeWaiting/10 + " minutes." );
    }
}
