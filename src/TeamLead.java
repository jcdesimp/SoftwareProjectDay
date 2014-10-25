/**
 * File created by jcdesimp on 10/24/14.
 */
public class TeamLead extends Developer {

    private boolean managerMeeting;

    public TeamLead(Team team, int devId) {
        super(team, devId);
        this.managerMeeting = false;
    }
}
