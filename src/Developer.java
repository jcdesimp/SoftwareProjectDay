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
    
    public Team getTeam()
    {
    	return team;
    }


    @Override
    public void run() {

        team.getOffice().getLogger().logAtTime(getName() + " arrives at the office.");
        setArrivalTime(team.getOffice().getTimeTracker().getCurrTime());

        while ( team.getOffice().getTimeTracker().getCurrTime() - getArrivalTime() < 4800 ||
                team.getOffice().getTimeTracker().getCurrTime() < 5100 ) {





        }

        team.getOffice().getLogger().logAtTime(getName() + " leaves the office.");
        setEndTime(team.getOffice().getTimeTracker().getCurrTime());
    }

}
