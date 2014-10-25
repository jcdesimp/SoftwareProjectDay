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
    }

    public int getTeamId() {
        return teamId;
    }
}
