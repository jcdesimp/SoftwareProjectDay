import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
/**
 * File created by jcdesimp on 10/24/14.
 */
public class Office {

    private OfficeLogger logger;
    private TimeTracker timeTracker;
    private Manager projectManager;
    private ArrayList<Team> teams;
    private ConferenceRoom confRoom;
    ArrayList<Integer> teamLeads;

    private CyclicBarrier leadMeeting = new CyclicBarrier(4);

    public Office(TimeTracker time, CountDownLatch startSignal) {

        this.timeTracker = time;
        this.logger =  new OfficeLogger(timeTracker);

        this.projectManager = new Manager(this, startSignal);
        this.confRoom =  new ConferenceRoom();
        
        this.teams = new ArrayList<Team>();
        for (int i = 1; i < 4; i++) {
            teams.add( new Team(i, this, startSignal) );
        }

    }

    public void setLeadMeeting()
    {
        try {
            leadMeeting.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /*
    public void holdLeadMeeting(int teamId, Office office, Developer employee)
    {

        while(!teamLeads.contains(teamId))
        {
            if () //if all team leads are here
            {
                synchronized (this)
                {

                    System.out.println("Waiting" + employee.getTeam().getTeamId() + employee.getDevId());
                    String gather_message = "Team " + teamId + " is gathering for the lead meeting.";
                    office.getLogger().logAtTime(gather_message);
                }
            }
        }

        if (employee.getDevId() == 1)
        {
            String start_message = "Team " + teamId + " is starting the meeting.";
            office.getLogger().logAtTime(start_message);

        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (employee.getDevId() == 1)
        {
            String end_message = "Team " + teamId + " has ended the lead meeting.";
            office.getLogger().logAtTime(end_message);
        }
    }
    */
    public void startDay() {
        timeTracker.start();
        projectManager.start();
        for (Team t : teams) {
            t.startDevs();
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

    public Manager getProjectManager() {
        return projectManager;
    }
}
