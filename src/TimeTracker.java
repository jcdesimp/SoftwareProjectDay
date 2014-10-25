/**
 * File created by jcdesimp on 10/21/14.
 */

public class TimeTracker extends Thread {

    private long currTime;
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

        while (this.getCurrTime() <= 5400) {
            currTime = System.currentTimeMillis() - startTime;
        }
    }

    /**
     * Getter for current time
     * @return the current time
     */
    public long getCurrTime(){
        //return System.currentTimeMillis() - currTime;
        return currTime;
    }


    /**
     * Gets the time in minutes of the day, not just elapsed
     * since start of day. (e.g. )
     * @return
     */
    public long getRealCurrTime(){
        //return System.currentTimeMillis() - currTime;
        return 4800 + currTime;
    }




    public String getTimestamp() {
        long timeNow = getCurrTime() + 4800;
        long hours = timeNow / 600;
        long remMins = timeNow % 600;
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
