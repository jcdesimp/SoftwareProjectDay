/**
 * File created by jcdesimp on 10/22/14.
 */
public class OfficeLogger {

    private TimeTracker time;

    /**
     * Constructor for office logger
     * @param time TimeTracker to print timestamps
     */
    public OfficeLogger(TimeTracker time) {
        this.time = time;
    }

    /**
     * Logs a message with a timestamp
     * @param message to be logged
     */
    public void logAtTime(String message) {

        System.out.println( "[" + time.getTimestamp() + "] " + message);
    }

}
