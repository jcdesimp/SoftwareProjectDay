/**
 * File created by jcdesimp on 10/24/14.
 */
public class TeamLead extends Developer {

    private boolean managerMeeting;

    public TeamLead(Team team, int devId, boolean managerMeeting) {
        super(team, devId);
        this.managerMeeting = managerMeeting;
    }
}