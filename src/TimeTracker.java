/**
 * File created by jcdesimp on 10/21/14.
 */
import java.util.concurrent.CountDownLatch;


public class TimeTracker extends Thread {

    public long currTime;
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

        try {
            // Starting all threads at the same time (clock == 0 / "8:00AM").
            startSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currTime = System.currentTimeMillis();

        while (this.getTime() <= 5400) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currTime += 1;
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
