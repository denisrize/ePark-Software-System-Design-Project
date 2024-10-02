public class Slots {
    private Ride ride;
    private ETicket eTicket;
    private int slotsLeft;

    public Slots(Ride ride, ETicket eTicket, int slotsLeft) {
        this.ride = ride;
        this.eTicket = eTicket;
        this.slotsLeft = slotsLeft;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public ETicket geteTicket() {
        return eTicket;
    }

    public void seteTicket(ETicket eTicket) {
        this.eTicket = eTicket;
    }

    public int getSlotsLeft() {
        return slotsLeft;
    }

    public void setSlotsLeft(int slotsLeft) {
        this.slotsLeft = slotsLeft;
    }
}
