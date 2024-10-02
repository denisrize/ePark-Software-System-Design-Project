import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuardianAccount {
    private int ID;
    private Payment payment;
    private List<VisitorControl> visitorControls = new ArrayList<>();

    public GuardianAccount(int ID, Payment payment) {
        this.ID = ID;
        this.payment = payment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<VisitorControl> getVisitorControls() {
        return visitorControls;
    }

    public void setVisitorControls(List<VisitorControl> visitorControls) {
        this.visitorControls = visitorControls;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public static float measureChildHeight(){
        java.lang.System.out.println("Please press enter to measure your child height to e-Ticket.");
        try {
            java.lang.System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        float measured = MeasureMachine.getHeight();
        java.lang.System.out.println("Height measured: " + measured); return measured;
    }
    public static float measureChildWeight() {
        java.lang.System.out.println("Please press enter to measure your child weight to e-Ticket.");
        try{java.lang.System.in.read();}catch (IOException e) {
            e.printStackTrace();
        }
        float measured = MeasureMachine.getWeight();
        java.lang.System.out.println("Weight measured: " + measured); return measured;
    }
}
