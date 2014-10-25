import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/21/14.
 */
public class Manager extends Employee {

    private Office office;

    private boolean leadMeeting;
    private boolean eMeeting1;
    private boolean eMeeting2;

    public Manager(Office office, CountDownLatch startSignal) {
        super("Manager", startSignal);
        this.office = office;
        this.leadMeeting = false;
        this.eMeeting1 = false;
        this.eMeeting2 = false;
    }

    @Override
    public void run() {

        // Wait for start signal
        try {
            getStartSignal().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Take care of arrival tasks
        office.getLogger().logAtTime(getName() + " arrives at the office.");
        setArrivalTime(office.getTimeTracker().getCurrTime());

        while ( office.getTimeTracker().getCurrTime() < 5400 ) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check for all time sensitive things first (Lunch, Meetings, Etc)
            // Also check to see of the time sensitive things have already been done,
            // if so don't bother checking the time.

            // See if anyone is waiting to ask you a question
            // if so then attempt to answer the question
            // if can't answer then request an answer form the manager

            // If there are no time sensitive things then do
            // "managerial tasks" and loop again.


        }

        // Take care of pre-departure tasks
        office.getLogger().logAtTime(getName() + " leaves the office.");
        setEndTime(office.getTimeTracker().getCurrTime());
    }
}
