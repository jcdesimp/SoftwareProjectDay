/**
 * File created by jcdesimp on 10/24/14.
 *
 * Base class to represent employees in the company
 */
public abstract class Employee extends Thread {

    private boolean busy;

    private int arrivalTime;
    private int endTime;
    private int lunchStartTIme;

    private int timeWorking;
    private int timeLunch;
    private int timeMeeting;

    private boolean atWork;
    private boolean ateLunch;
    private boolean closingMeeting;



    public Employee(String name) {
        super(name);
        this.busy = false;
    }

    public synchronized boolean isBusy() {
        return busy;
    }

}
