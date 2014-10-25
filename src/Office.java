import java.util.ArrayList;

/**
 * File created by jcdesimp on 10/24/14.
 */
public class Office {

    private OfficeLogger logger;
    private TimeTracker timeTracker;
    private Manager projectManager;
    private ArrayList<Team> teams;
    private ConferenceRoom confRoom;



    public Office(TimeTracker time) {

        this.timeTracker = time;
        this.logger =  new OfficeLogger(timeTracker);

        this.projectManager = new Manager(this);
        this.confRoom =  new ConferenceRoom();
        
        this.teams = new ArrayList<Team>();
        for (int i = 1; i < 4; i++) {
            teams.add( new Team(i, this) );
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

}
