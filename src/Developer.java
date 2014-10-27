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

    private boolean reported;

    /**
     * Developer constructor
     * @param team that developer is in
     * @param devId id of this developer
     * @param startSignal latch to make all threads start at once
     */
    public Developer(Team team, int devId, CountDownLatch startSignal) {
        super(("Developer " + team.getTeamId() + "" + devId), startSignal);
        this.devId = devId;
        this.team = team;
        this.timeWaiting = 0;

        this.morningMeeting = false;

        this.reported = false;
    }


    /**
     * Get the team this developer is part of
     * @return Team that dev is on
     */
    public Team getTeam()
    {
    	return team;
    }


    /**
     * run method overridden from Thread
     */
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

        long meetStart = getTeam().getOffice().getTimeTracker().getCurrTime();
        getTeam().getOffice().getConferenceRoom().setupTeamMeeting(getTeam().getTeamId(), this, false);
        getTeam().getOffice().getConferenceRoom().holdMeeting(getTeam().getTeamId(), getTeam().getOffice(), this, false);
        addTimeMeeting(getTeam().getOffice().getTimeTracker().getCurrTime() - meetStart);

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
                addTimeLunch(getTeam().getOffice().getTimeTracker().getCurrTime() - startTime);
            }

            else if (team.getOffice().getTimeTracker().getRealCurrTime() >= 9600 && !reported)
            {
                getTeam().getOffice().getLogger().logAtTime(getName() + " goes to status meeting.");
                meetStart = getTeam().getOffice().getTimeTracker().getCurrTime();
                team.getOffice().getConferenceRoom().setupAllMeeting();
                team.getOffice().getConferenceRoom().holdAllMeeting(team.getOffice());
                reported = true;
                addTimeMeeting(getTeam().getOffice().getTimeTracker().getCurrTime() - meetStart);
            }


            // If there are no time sensitive things then the "else" will determine
            // Whether or not a question should be asked.
            else {
                if ((getTeam().getOffice().getTimeTracker().getRealCurrTime() < 7050 || ateLunch()) && r.nextInt(1000) < 1) {
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
        printLog();
    }

    /**
     * Add time to total waiting time
     * @param tMillis to add
     */
    public void addWaitingTime(long tMillis) {
        timeWaiting += tMillis;
    }

    /**
     * get the amount of time waiting for an answer
     * @return milliseconds of time
     */
    public int getTimeWaiting() {
        return timeWaiting;
    }

    /**
     * Get the ID of the developer
     * @return int id
     */
    public int getDevId()
    {
        return devId;
    }

    /**
     * print the statistical data of the developer
     * Overridden from Employee
     */
    @Override
    public void printLog() {
        System.out.println("------ " + getName() + " Log ------\n" +
            "  Total time working: " + (getEndTime() - getArrivalTime())/10 + " minutes.\n" +
            "  Time spent at Lunch: " + getTimeLunch()/10 + " minutes.\n" +
            "  Time spent in meetings: " + getTimeMeeting()/10 + " minutes.\n" +
            "  Time spent waiting for answers: " + timeWaiting/10 + " minutes.\n" +
            "---------------------------- "
        );
    }
}
