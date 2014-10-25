/**
 * File created by jcdesimp on 10/22/14.
 */
public class OfficeLogger {

    private TimeTracker time;

    public OfficeLogger(TimeTracker time) {
        this.time = time;
    }

    public void logAtTime(String message) {

        System.out.println( "[" + time.getTimestamp() + "] " + message);
    }

}
