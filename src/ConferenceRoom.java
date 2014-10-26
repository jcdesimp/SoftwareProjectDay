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
		
	public ConferenceRoom()
	{
		team_barriers = new ArrayList<CyclicBarrier>();
		for (int i = 0; i < 4; i++ )
		{
			team_barriers.add(new CyclicBarrier(4));
		}
		
		
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
	
	public void finishMeeting(int teamId)
	{
		while(teamId != waitList.get(0))
		{
			if (!occupied)
			{
				occupied = true;
				System.out.println("Holding team meeting");
			}
		}
		
		System.out.println("Team meeting start");
		Thread.sleep(1);
		System.out.println("Team meeting ended");
		occupied = false;
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
