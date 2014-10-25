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



    public Employee(String name) {
        super(name);
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
}
