import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
/**
 * File created by jcdesimp on 10/24/14.
 */
public class Office {

    private OfficeLogger logger;
    private TimeTracker timeTracker;
    private Manager projectManager;
    private ArrayList<Team> teams;
    private ConferenceRoom confRoom;

    private CyclicBarrier leadMeeting = new CyclicBarrier(4);

    public Office(TimeTracker time, CountDownLatch startSignal) {

        this.timeTracker = time;
        this.logger =  new OfficeLogger(timeTracker);

        this.projectManager = new Manager(this, startSignal);
        this.confRoom =  new ConferenceRoom();
        
        this.teams = new ArrayList<Team>();
        for (int i = 1; i < 4; i++) {
            teams.add( new Team(i, this, startSignal) );
        }

    }

    /**
     * Called by team leads to join the lead meeting
     */
    public void joinLeadMeeting() {
        try {
            leadMeeting.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        try {
            leadMeeting.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called by manager to start the lead meeting
     */
    public void holdLeadMeeting() {
        try {
            leadMeeting.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
            getLogger().logAtTime(Thread.currentThread().getName() + " holds the initial stand-up meeting.");
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            leadMeeting.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


    }

    /**
     * Called to "Start the day!"
     * Starts all the threads in the office.
     */
    public void startDay() {
        timeTracker.start();
        projectManager.start();
        for (Team t : teams) {
            t.startDevs();
        }

    }

    /**
     * Get the timeTracker of the office
     * @return the TimeTracker
     */
    public TimeTracker getTimeTracker() {
        return timeTracker;
    }

    /**
     * Get the officeLogger
     * @return OfficeLogger
     */
    public OfficeLogger getLogger() {
        return logger;
    }

    /**
     * Get the one office conference room
     * @return ConferenceRoom
     */
    public ConferenceRoom getConferenceRoom() {
    	return confRoom;
    }

    /**
     * Get the projectManager of the office
     * @return ProjectManager
     */
    public Manager getProjectManager() {
        return projectManager;
    }
}
