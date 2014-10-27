import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/21/14.
 */

public class TimeTracker extends Thread {

    private long currTime;
    private long startTime;
    private CountDownLatch startSignal;

    /**
     * Constructor for TimeTracker
     */
    public TimeTracker(CountDownLatch startSignal) {
        this.startSignal = startSignal;
        this.currTime = 0;
    }

    /**
     * Run method for TimeTracker Thread
     */
    @Override
    public void run() {

        // Wait for start signal
        try {
            startSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        //System.out.println(currTime);
        return currTime;
    }


    /**
     * Gets the time in minutes of the day, not just elapsed
     * since start of day. (e.g. )
     * @return actual time of day.
     */
    public long getRealCurrTime(){
        //return System.currentTimeMillis() - currTime;
        return 4800 + currTime;
    }


    /**
     * Get a well-formatted timestamp of the curent time
     * @return String of the timestamp
     */
    public String getTimestamp() {
        long timeNow = getRealCurrTime();
        long hours = timeNow / 600;
        long remMins = (timeNow % 600)/10;
        String meridiem = "am";
        if (hours >= 12) {
            meridiem = "pm";
        }
        if (hours > 12) {
            hours = hours - 12;
        }

        return hours + ":" + String.format("%02d", remMins) + " " + meridiem;

    }
}
