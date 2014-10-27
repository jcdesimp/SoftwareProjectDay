import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/17/14.
 */
public class Main {
    /**
     * Main driver static method
     * @param args given at commandline
     */
    public static void main(String [ ] args) {

        CountDownLatch startSignal = new CountDownLatch(1);
        TimeTracker timeTracker = new TimeTracker(startSignal);


        Office mainOffice = new Office(timeTracker, startSignal);

        // Start the day!
        mainOffice.startDay();
        startSignal.countDown();


    }
}
