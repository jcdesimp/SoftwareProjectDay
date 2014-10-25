import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/24/14.
 */
public class Office {

    private OfficeLogger logger;
    private TimeTracker timeTracker;
    private Manager projectManager;
    private ArrayList<Team> teams;
    private ConferenceRoom confRoom;



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
