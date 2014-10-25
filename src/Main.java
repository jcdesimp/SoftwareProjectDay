import java.util.concurrent.CountDownLatch;

/**
 * File created by jcdesimp on 10/17/14.
 */
public class Main {
    public static void main(String [ ] args) {

        CountDownLatch startSignal = new CountDownLatch(1);
        TimeTracker timeTracker = new TimeTracker(startSignal);


        Office mainOffice = new Office(timeTracker, startSignal);

        // Start the day!
        mainOffice.startDay();
        startSignal.countDown();



        /*
        int NUM_TEAMS = 3;
        int NUM_MEMBERS = 4;


        ArrayList<ArrayList<Employee>> teams = new ArrayList<ArrayList<Employee>>();


        for (int t = 0; t < NUM_TEAMS; t++) {

            ArrayList<Employee> currTeam = new ArrayList<Employee>();

            for (int m = 0; m < NUM_MEMBERS; m++) {
                //currTeam.add();
            }

        }
        */



    }
}
