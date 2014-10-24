/**
 * File created by jcdesimp on 10/24/14.
 *
 * Base class to represent employees in the company
 */
public abstract class Employee extends Thread {

    private Boolean busy;

    public Employee(String name) {
        super(name);
        this.busy = false;
    }

    public synchronized boolean isBusy() {
        return busy;
    }

}
