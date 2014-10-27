import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/24/14.
 *
 * Base class to represent employees in the company
 */
public abstract class Employee extends Thread {

    private boolean busy;

    private long arrivalTime;
    private long endTime;
    private long lunchStartTIme;

    private long timeWorking;
    private long timeLunch;
    private long timeMeeting;

    private boolean atWork;
    private boolean ateLunch;
    private boolean closingMeeting;

    private final CountDownLatch startSignal;


    /**
     * Constructor for Employee
     * @param name of employee, will become name of thread
     * @param startSignal latch to ensure all threads start at once
     */
    public Employee(String name, CountDownLatch startSignal) {
        super(name);

        this.startSignal = startSignal;

        this.busy = false;

        this.arrivalTime = -1;
        this.endTime = -1;
        this.lunchStartTIme = -1;

        this.timeWorking = 0;
        this.timeLunch = 0;
        this.timeMeeting = 0;

        this.atWork = false;
        this.ateLunch = false;
        this.closingMeeting = false;

    }

    /**
     * print the statistical data of the employee
     */
    public abstract void printLog();

    /**
     * Get the startSignal CountdownLatch
     * @return
     */
    public CountDownLatch getStartSignal() {
        return startSignal;
    }

    /**
     * Get the time the employee arrived at work
     * @return num milliseconds
     */
    public long getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Set the arrival time of the employee
     * @param arrivalTime of the employee
     */
    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Get the time the employee left work
     * @return endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * set the time the employee left for work
     * @param endTime time employee left
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * Add time to total working time
     * @param tMillis working
     */
    public void addTimeWorking(long tMillis) {
        timeWorking += tMillis;
    }

    /**
     * Add time to total lunch time
     * @param tMillis to add
     */
    public void addTimeLunch(long tMillis) {
        timeLunch += tMillis;
    }

    /**
     * Add time to total meeting time
     * @param tMillis to add
     */
    public void addTimeMeeting(long tMillis) {
        timeMeeting += tMillis;
    }

    /**
     * Did emplyee eat lunch
     * @return boolean
     */
    public boolean ateLunch() {
        return ateLunch;
    }

    /**
     * Set ateLunch to true
     */
    public void eatLunch() {
        ateLunch = true;
    }

    /**
     * get the amount of time spent eating lunch
     * @return amount of time
     */
    public long getTimeLunch() {
        return timeLunch;
    }

    /**
     * Get time spent in meetings
     * @return time spent
     */
    public long getTimeMeeting() {
        return timeMeeting;
    }


}
