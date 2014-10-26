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

    public CountDownLatch getStartSignal() {
        return startSignal;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void addTimeWorking(long tMillis) {
        timeWorking += tMillis;
    }

    public void addTimeLunch(long tMillis) {
        timeLunch += tMillis;
    }

    public void addTimeMeeting(long tMillis) {
        timeMeeting += tMillis;
    }

    public boolean ateLunch() {
        return ateLunch;
    }

    public void eatLunch() {
        ateLunch = true;
    }

}
