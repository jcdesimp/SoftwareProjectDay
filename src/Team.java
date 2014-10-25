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

    public Team(int teamId, Office office, CountDownLatch startSignal) {
        this.teamId = teamId;
        this.office = office;
        this.developers = new ArrayList<Developer>();
        this.teamLead = new TeamLead(this, 1, startSignal);
        developers.add(teamLead);
        for (int i = 2; i < 4; i++) {
            developers.add( new Developer(this, i, startSignal) );
        }
    }

    public void startDevs() {
        for (Developer d : developers) {
            d.start();
        }

    }

    public int getTeamId() {
        return teamId;
    }

    public Office getOffice() {
        return office;
    }


}
