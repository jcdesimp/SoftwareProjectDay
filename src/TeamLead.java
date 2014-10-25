/**
 * File created by jcdesimp on 10/24/14.
 */
public class TeamLead extends Developer {

    private boolean managerMeeting;

    public TeamLead(Team team, int devId) {
        super(team, devId);
        this.managerMeeting = false;
    }

    @Override
    public void run() {

        // Take care of arrival tasks
        getTeam().getOffice().getLogger().logAtTime(getName() + " arrives at the office.");
        setArrivalTime(getTeam().getOffice().getTimeTracker().getCurrTime());

        while ( getTeam().getOffice().getTimeTracker().getCurrTime() - getArrivalTime() < 4800 ||
                getTeam().getOffice().getTimeTracker().getCurrTime() < 5100 ) {
            // Check for all time sensitive things first (Lunch, Meetings, Etc)
            // Also check to see of the time sensitive things have already been done,
            // if so don't bother checking the time.

            // See if anyone is waiting to ask you a question
            // if so then attempt to answer the question
            // if can't answer then request an answer form the manager

            // If there are no time sensitive things then the "else" will determine
            // Whether or not a question should be asked.

            // if so then ask, otherwise loop again.


        }

        // Take care of pre-departure tasks
        getTeam().getOffice().getLogger().logAtTime(getName() + " leaves the office.");
        setEndTime(getTeam().getOffice().getTimeTracker().getCurrTime());
    }
}
