/**
 * File created by jcdesimp on 10/24/14.
 */
public class Developer extends Employee {

    private int devId;
    private Team team;

    private int timeWaiting;

    private boolean morningMeeting;

    private boolean waitingForQuestion;


    public Developer(Team team, int devId) {
        super(("Developer " + team.getTeamId() + devId));
        this.devId = devId;
        this.team = team;
        this.timeWaiting = 0;

        this.morningMeeting = false;
        this.waitingForQuestion = false;

    }


    public void askQuestion() {

    }

}
