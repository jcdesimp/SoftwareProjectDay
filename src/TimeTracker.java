/**
 * File created by jcdesimp on 10/21/14.
 */
public class TimeTracker extends Thread {

    public int currTime;


    /**
     * Constructor for TimeTracker
     */
    public TimeTracker() {
        super("TimeTracker");
        this.currTime = 0;
    }

    /**
     * Run method for TimeTracker Thread
     */
    @Override
    public void run() {

        while (currTime < 48) {
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
    public int getCurrTime() {
        return currTime;
    }


    public String getTimestamp() {
        int timeNow = currTime;
        int hours = timeNow / 60;
        int remMins = timeNow % 60;
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
