import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/24/14.
 */
public class Developer extends Employee {

    private int devId;
    private Team team;

    private int timeWaiting;

    private boolean morningMeeting;

    private boolean waitingForQuestion;


    public Developer(Team team, int devId, CountDownLatch startSignal) {
        super(("Developer " + team.getTeamId() + "" + devId), startSignal);
        this.devId = devId;
        this.team = team;
        this.timeWaiting = 0;

        this.morningMeeting = false;
        this.waitingForQuestion = false;

    }


    public void askQuestion() {

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

        // Take care of arrival tasks
        getTeam().getOffice().getLogger().logAtTime(getName() + " arrives at the office.");
        setArrivalTime(getTeam().getOffice().getTimeTracker().getCurrTime());

        while ( getTeam().getOffice().getTimeTracker().getCurrTime() - getArrivalTime() < 4800 ||
                getTeam().getOffice().getTimeTracker().getCurrTime() < 5100 ) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check for all time sensitive things first (Lunch, Meetings, Etc)
            // Also check to see of the time sensitive things have already been done,
            // if so don't bother checking the time.

            // If there are no time sensitive things then the "else" will determine
            // Whether or not a question should be asked.

            // if so then ask, otherwise loop again.


        }

        // Take care of pre-departure tasks
        getTeam().getOffice().getLogger().logAtTime(getName() + " leaves the office.");
        setEndTime(getTeam().getOffice().getTimeTracker().getCurrTime());
    }

    public void addWaitingTime(long tMillis) {
        timeWaiting += tMillis;
    }

}
