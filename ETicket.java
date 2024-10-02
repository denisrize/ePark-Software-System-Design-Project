import java.util.ArrayList;
import java.util.List;

public class ETicket {
    private String location;
    private String expiration;
    private List<Slots> slotsList = new ArrayList<>();

    public ETicket(String location, String expirationTime) {
        this.location = location;
        this.expiration = expirationTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExpirationTime() {
        return expiration;
    }

    public void setExpirationTime(String expirationTime) {
        this.expiration = expirationTime;
    }

    public List<Slots> getSlotsList() {
        return slotsList;
    }

    public void setSlotsList(List<Slots> slotsList) {
        this.slotsList = slotsList;
    }

    public void DisplayEticket() {
        if (slotsList.isEmpty()){
            java.lang.System.out.println("eTicket is empty"); return;
        }
        for (Slots slots :
                slotsList) {
            java.lang.System.out.println("Ride: "+ slots.getRide().getName()+", Slots: " + slots.getSlotsLeft());
        }
    }
    public Slots addSlots(Ride ride, int slots){
        for (Slots e:
                slotsList) {
            if(e.getRide().getName().equals(ride.getName())) {
                e.setSlotsLeft(e.getSlotsLeft()+slots);
                return null;
            }
        }
        Slots newSlots = new Slots(ride, this, slots);
        slotsList.add(newSlots);
        return newSlots;
    }
    public Slots removeSlots(Ride ride, int slots){
        Slots to_rem = null;
        for (Slots e:
                slotsList) {
            if(e.getRide().getName().equals(ride.getName())) {
                e.setSlotsLeft(e.getSlotsLeft()-slots);
                if(e.getSlotsLeft()==0) to_rem=e;
                break;
            }
        }
        if(to_rem!=null) slotsList.remove(to_rem);
        return to_rem;
    }
}
