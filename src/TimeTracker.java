/**
 * File created by jcdesimp on 10/21/14.
 */

public class TimeTracker extends Thread {

    public long currTime;
    private long startTime;

    /**
     * Constructor for TimeTracker
     */
    public TimeTracker() {
        this.currTime = 0;
    }

    /**
     * Run method for TimeTracker Thread
     */
    @Override
    public void run() {

        startTime = System.currentTimeMillis();

        while (this.getTime() <= 5400) {
            currTime = System.currentTimeMillis() - startTime;
        }
    }

    /**
     * Getter for current time
     * @return the current time
     */
    public long getTime(){
        return System.currentTimeMillis() - currTime;
    }


    public String getTimestamp() {
        long timeNow = currTime;
        long hours = timeNow / 60;
        long remMins = timeNow % 60;
        String meridiem = "am";
        if (hours >= 12) {
            meridiem = "pm";
        }
        if (hours > 12) {
            hours = hours - 12;
        }

        return hours + ":" + remMins + " " + meridiem;

    }
}
