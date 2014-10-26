import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/24/14.
 */
public class Team {

    private int teamId;
    private Office office;
    private TeamLead teamLead;
    private ArrayList<Developer> developers;

    /**
     * Constructor for a Team
     * @param teamId id of the team
     * @param office office object that team is a part of
     * @param startSignal to tell developer threads when to start
     */
    public Team(int teamId, Office office, CountDownLatch startSignal) {
        this.teamId = teamId;
        this.office = office;
        this.developers = new ArrayList<Developer>();
        this.teamLead = new TeamLead(this, 1, startSignal);
        developers.add(teamLead);
        for (int i = 2; i <= 4; i++) {
            developers.add( new Developer(this, i, startSignal) );
        }
    }

    /**
     * Start all the team's developer threads
     */
    public void startDevs() {
        for (Developer d : developers) {
            d.start();
        }

    }

    /**
     * Get the id of the team
     * @return teamid
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * get the office object of the tam
     * @return Office of team
     */
    public Office getOffice() {
        return office;
    }
    
    public ArrayList<Developer> getDevelopers() {
    	return developers;
    }

    public TeamLead getTeamLead() {
        return teamLead;
    }
}
