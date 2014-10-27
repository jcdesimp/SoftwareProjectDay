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

	public void setupTeamMeeting(int teamId, Developer employee)
	{
        try {
            //System.out.println("Setup" + employee.getTeam().getTeamId() + employee.getDevId());

            team_barriers.get(teamId - 1).await();
            waitList.add(teamId); //todo print statement seems to  have duplicates
            System.out.println(waitList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

	public void holdMeeting(int teamId, Office office, Developer employee)
	{

        while(!teams_met.contains(teamId))
		{
            if (!occupied && teamId == waitList.peek())
			{
                synchronized (this)
                {

                    //System.out.println("Waiting" + employee.getTeam().getTeamId() + employee.getDevId());
                    occupied = true;
                    String gather_message = Thread.currentThread().getName() + " is gathering for a team meeting.";
                    office.getLogger().logAtTime(gather_message);
                    teams_met.add(teamId);
                    //System.out.println(teams_met);
                }
            }
		}
       // System.out.println(occupied);

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
		waitList.poll();
	}

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
