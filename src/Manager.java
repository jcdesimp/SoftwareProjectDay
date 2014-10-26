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

    private boolean leadMeeting;
    private boolean eMeeting1;
    private boolean eMeeting2;

    private LinkedBlockingQueue<Thread> waitingQuestions;
    private Thread answering;
    private CyclicBarrier waitOnAnswer;

    public Manager(Office office, CountDownLatch startSignal) {
        super("Manager", startSignal);
        this.office = office;
        this.leadMeeting = false;
        this.eMeeting1 = false;
        this.eMeeting2 = false;
        this.waitingQuestions = new LinkedBlockingQueue<Thread>();
        this.waitOnAnswer = new CyclicBarrier(2);
    }

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
                addTimeLunch(office.getTimeTracker().getRealCurrTime() - startTime);

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
    }
}
