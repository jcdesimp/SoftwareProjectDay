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


    public void startDay() {
        timeTracker.start();
        projectManager.start();
        for (Team t : teams) {
            t.startDevs();
        }

    }

    public TimeTracker getTimeTracker() {
        return timeTracker;
    }

    public OfficeLogger getLogger() {
        return logger;
    }
    
    public ConferenceRoom getConferenceRoom() {
    	return confRoom;
    }

    public Manager getProjectManager() {
        return projectManager;
    }
}
