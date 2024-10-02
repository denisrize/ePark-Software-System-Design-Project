public class VisitorControl {
    static int IdCounter = 0;
    private final Child child;
    private int ID;
    private String password;
    private GuardianAccount guardianAccount;
    private ETicket eTicket;
    private float limitLeft;
    private float amountToCharge;

    public VisitorControl(String password, GuardianAccount guardianAccount, ETicket eTicket, Child child, float limitLeft) {
        this.ID = ++IdCounter;
        this.password = password;
        this.guardianAccount = guardianAccount;
        this.eTicket = eTicket;
        this.child = child;
        this.amountToCharge = 0;
        this.limitLeft = limitLeft;
    }

    public Child getChild() {
        return child;
    }    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getLimitLeft() {
        return limitLeft;
    }

    public void setLimitLeft(float limitLeft) {
        this.limitLeft = limitLeft;
    }

    public void chargeForRide(float charge){
        this.limitLeft -= charge;
        this.amountToCharge += charge;
    }

    public float getAmountToCharge() {
        return amountToCharge;
    }

    public void setAmountToCharge(float amountToCharge) {
        this.amountToCharge = amountToCharge;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GuardianAccount getGuardian() {
        return guardianAccount;
    }

    public void setGuardian(GuardianAccount guardianAccount) {
        this.guardianAccount = guardianAccount;
    }

    public ETicket getETicket() {
        return eTicket;
    }

    public void setETicket(ETicket eTicket) {
        this.eTicket = eTicket;
    }
}
