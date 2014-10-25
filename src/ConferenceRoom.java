package src;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * File created by jcdesimp on 10/24/14.
 */

public class ConferenceRoom {
	ArrayList<CyclicBarrier> team_barriers;

	//Everyone meeting, a total of 13 people (12developers + 1 manager)
	private CyclicBarrier all_barrier;
	
	public boolean occupied;
	
	public ArrayList<Team> waitList;
	
	public int used_by;
	
	public ConferenceRoom()
	{
		team_barriers = new ArrayList<CyclicBarrier>();
		for (int i = 0; i < 4; i++ )
		{
			team_barriers.add(new CyclicBarrier(4));
		}
		
		
		this.all_barrier = new CyclicBarrier(13);
		occupied = false;
		waitList = new ArrayList<Team>();
		used_by = 0;
	}
	
	public void setupTeamMeeting(int teamId)
	{
		team_barriers.get(teamId).await();
		if (waitList.size() == 0)
		{
			occupied = true;
		}
		else
		{
			//waitList.add();
		}
	}
	
	public void joinAllMeeting()
	{
		all_barrier.await();
	}
	
	
}
