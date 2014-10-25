/**
 * File created by jcdesimp on 10/21/14.
 */
public class Manager extends Employee {

    private Office office;

    private boolean leadMeeting;
    private boolean eMeeting1;
    private boolean eMeeting2;

    public Manager(Office office) {
        super("Manager");
        this.office = office;
        this.leadMeeting = false;
        this.eMeeting1 = false;
        this.eMeeting2 = false;
    }
}
