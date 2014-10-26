import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * File created by jcdesimp on 10/24/14.
 */

public class ConferenceRoom {
	ArrayList<CyclicBarrier> team_barriers;

	//Everyone meeting, a total of 13 people (12developers + 1 manager)
	private CyclicBarrier all_barrier;
	
	private boolean occupied;
	
	private ArrayList<Integer> waitList;
	
	int teams_met;
	
	public ConferenceRoom()
	{
		team_barriers = new ArrayList<CyclicBarrier>();
		for (int i = 0; i < 4; i++ )
		{
			team_barriers.add(new CyclicBarrier(4));
		}
		
		teams_met = 0;
		this.all_barrier = new CyclicBarrier(13);
		occupied = false;
		waitList = new ArrayList<Integer>();
	}
	
	public void setupTeamMeeting(int teamId)
	{
        try {
            team_barriers.get(teamId - 1).await();
            waitList.add(teamId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
	}
	
	public void holdMeeting(int teamId, Office office)
	{
		while(teams_met != 4)
		{
			if (!occupied && teamId == waitList.get(0))
			{
				occupied = true;
				String gather_message = "Team " + teamId + " is gathering for a meeting.";
				office.getLogger().logAtTime(gather_message);
				team_barriers.get(teamId - 1).reset();
				break;
			}
		}
		
		team_barriers.get(teamId - 1).await();
		String start_message = "Team " + teamId + " is starting the meeting.";
		office.getLogger().logAtTime(start_message);
		Thread.sleep(150);
		String end_message = "Team " + teamId + " has ended the meeting.";
		office.getLogger().logAtTime(end_message);
		System.out.println("Team meeting ended");
		occupied = false;
		teams_met++;
		waitList.remove(0);
		
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
	
	
}
