import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * File created by jcdesimp on 10/21/14.
 */
public class Manager extends Employee {

    private Office office;

    private boolean eMeeting1;
    private boolean eMeeting2;
    private boolean reported;

    private LinkedBlockingQueue<Thread> waitingQuestions;
    private Thread answering;
    private CyclicBarrier waitOnAnswer;

    /**
     * Constructor for Manager
     * @param office that the manager is in
     * @param startSignal Latch to start all threads at once
     */
    public Manager(Office office, CountDownLatch startSignal) {
        super("Manager", startSignal);
        this.office = office;
        this.eMeeting1 = false;
        this.eMeeting2 = false;
        this.waitingQuestions = new LinkedBlockingQueue<Thread>();
        this.waitOnAnswer = new CyclicBarrier(2);
        this.reported = false;
    }

    /**
     * called when someone asks a question of the manager
     */
    public void askQuestion() {
        waitingQuestions.add(Thread.currentThread());
        while(answering == null || !answering.getName().equals(Thread.currentThread().getName())) {
            try {
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            waitOnAnswer.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


    }

    /**
     * Overriding run method from Thread
     */
    @Override
    public void run() {

        // Wait for start signal
        try {
            getStartSignal().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Take care of arrival tasks
        office.getLogger().logAtTime(getName() + " arrives at the office.");
        setArrivalTime(office.getTimeTracker().getCurrTime());

        Random r = new Random();

        long meetStart = office.getTimeTracker().getCurrTime();
        office.getLogger().logAtTime(getName() + " waits for team leads to start stand-up meeting.");
        office.holdLeadMeeting();
        office.getLogger().logAtTime(getName() + " returns from stand-up meeting.");
        addTimeMeeting(office.getTimeTracker().getCurrTime() - meetStart);


        while ( office.getTimeTracker().getCurrTime() < 5400 ) {



            // Check for all time sensitive things first (Lunch, Meetings, Etc)
            // Also check to see of the time sensitive things have already been done,
            // if so don't bother checking the time.
            if (!ateLunch() && office.getTimeTracker().getRealCurrTime() >= 7200) {

                long startTime = office.getTimeTracker().getCurrTime();

                office.getLogger().logAtTime(getName() + " goes to lunch.");
                try {
                    Thread.sleep( 300 + r.nextInt((int) (7500 -
                            office.getTimeTracker().getRealCurrTime())));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eatLunch();

                office.getLogger().logAtTime(getName() + " returns from lunch.");
                addTimeLunch(office.getTimeTracker().getCurrTime() - startTime);

            } else if (!eMeeting1 && office.getTimeTracker().getRealCurrTime() >= 6000) {
                long start = office.getTimeTracker().getCurrTime();
                try {
                    office.getLogger().logAtTime(getName() + " starts executive meeting 1.");
                    Thread.sleep(600 - (office.getTimeTracker().getRealCurrTime() - 6000) );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eMeeting1 = true;
                office.getLogger().logAtTime(getName() + " returns from executive meeting 1.");
                addTimeMeeting(office.getTimeTracker().getCurrTime() - start);
            } else if (!eMeeting2 && office.getTimeTracker().getRealCurrTime() >= 8400) {
                long start = office.getTimeTracker().getCurrTime();
                try {
                    office.getLogger().logAtTime(getName() + " starts executive meeting 2.");
                    Thread.sleep(600 - (office.getTimeTracker().getRealCurrTime() - 8400) );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eMeeting2 = true;
                office.getLogger().logAtTime(getName() + " returns from executive meeting 2.");
                addTimeMeeting(office.getTimeTracker().getCurrTime() - start);
            } else if (!reported && office.getTimeTracker().getRealCurrTime() >= 9750)
            {
                meetStart = office.getTimeTracker().getCurrTime();
                office.getConferenceRoom().setupAllMeeting();
                office.getLogger().logAtTime("The project status update meeting is now starting.");
                office.getConferenceRoom().holdAllMeeting(office);
                office.getLogger().logAtTime("The project status update meeting has finished.");
                reported = true;
                addTimeMeeting(office.getTimeTracker().getCurrTime() - meetStart);
            }


            // See if anyone is waiting to ask you a question
            // if so then attempt to answer the question
            // if can't answer then request an answer form the manager
            else if (!waitingQuestions.isEmpty()) {
                answering = waitingQuestions.poll();
                synchronized (answering) {
                    answering.notify();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    waitOnAnswer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }



            }
            // If there are no time sensitive things then do
            // "managerial tasks" and loop again.

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }



        // Take care of pre-departure tasks
        office.getLogger().logAtTime(getName() + " leaves the office.");
        setEndTime(office.getTimeTracker().getCurrTime());
        printLog();
    }

    /**
     * Print the statistics of Manager, overrides from Employee
     */
    @Override
    public void printLog() {
        System.out.println("------ " + getName() + " Log ------\n" +
                "  Total time working: " + (getEndTime() - getArrivalTime())/10 + " minutes.\n" +
                "  Time spent at Lunch: " + getTimeLunch()/10 + " minutes.\n" +
                "  Time spent in meetings: " + getTimeMeeting()/10 + " minutes.\n" +
                "---------------------------- "
        );
    }
}
