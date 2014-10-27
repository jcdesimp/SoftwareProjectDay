import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * File created by jcdesimp on 10/24/14.
 */

public class ConferenceRoom {
	ArrayList<CyclicBarrier> team_barriers;

	//Everyone meeting, a total of 13 people (12developers + 1 manager)
	private CyclicBarrier all_barrier;
	private boolean occupied;
    private LinkedBlockingQueue<Integer> waitList;
    private ArrayList<Integer> teams_met;

    /**
     * Constructor for conference room
     */
	public ConferenceRoom()
	{
		team_barriers = new ArrayList<CyclicBarrier>();
		for (int i = 0; i < 3; i++ )
		{
			team_barriers.add(new CyclicBarrier(4));
		}

		teams_met = new ArrayList<Integer>();
		this.all_barrier = new CyclicBarrier(13);
		occupied = false;
		waitList = new LinkedBlockingQueue<Integer>();
	}

    /**
     * Method to setup a team meeting
     * @param teamId employee's team id
     * @param employee that is setting up the meeting
     * @param leader_flag to see if the employee is a teamlead
     */
    public void setupTeamMeeting(int teamId, Developer employee, boolean leader_flag)
    {
        try {
            team_barriers.get(teamId - 1).await();
            if (leader_flag) waitList.add(teamId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to hold the actual meeting
     * @param teamId of team that is meeting
     * @param office that team is a part of
     * @param employee holding the meeting
     * @param leader_flag is this employee the leader?
     */
	public void holdMeeting(int teamId, Office office, Developer employee, boolean leader_flag)
	{

        while(!teams_met.contains(teamId))
		{
            if (waitList.size() != 0)
            {
                if (!occupied && teamId == waitList.peek())
                {
                    synchronized (this)
                    {
                        occupied = true;
                        String gather_message = Thread.currentThread().getName() + " is gathering for a team meeting.";
                        office.getLogger().logAtTime(gather_message);
                        teams_met.add(teamId);
                    }
                }
            }
		}

        try {
            team_barriers.get(teamId - 1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
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
            String end_message = "Team " + teamId + " has ended the meeting.";
            office.getLogger().logAtTime(end_message);
        }
        occupied = false;
        if (leader_flag) {
            waitList.poll();
        }
    }

    /**
     * Setup the update meeting that everyone is in
     */
	public void setupAllMeeting()
	{
        try {
            all_barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * hold the actual update meeting
     * @param office that the employees work in
     */
    public void holdAllMeeting(Office office)
    {
        try {
            all_barrier.await();
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
