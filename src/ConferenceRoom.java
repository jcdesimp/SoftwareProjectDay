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
        try {
            team_barriers.get(teamId - 1).await();
            waitList.add(((Developer) Thread.currentThread()).getTeam());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
	}
	
	public void finishMeeting()
	{
		if (waitList.size() > 0)
		{
			startMeeting(waitList.remove(0));
		}
	}
	
	public void startMeeting(Team team)
	{
		synchronized (team)
		{
			notifyAll();
		}
	}
	
	public void joinAllMeeting()
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
