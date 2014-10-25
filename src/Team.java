import java.util.ArrayList;

/**
 * File created by jcdesimp on 10/24/14.
 */
public class Team {

    private int teamId;
    private Office office;
    private TeamLead teamLead;
    private ArrayList<Developer> developers;

    public Team(int teamId, Office office) {
        this.teamId = teamId;
        this.office = office;
        this.developers = new ArrayList<Developer>();
        this.teamLead = new TeamLead(this, 1);
        developers.add(teamLead);
        for (int i = 2; i < 4; i++) {
            developers.add( new Developer(this, i) );
        }
    }

    public int getTeamId() {
        return teamId;
    }
}
