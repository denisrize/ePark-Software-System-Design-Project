import java.util.Scanner;

public class ExtremeRide extends Ride {
    public ExtremeRide(String name, int minAge, float minWeight, float minHeight, float cost) {
        super(name, minAge, minWeight, minHeight, cost);
    }
    @Override
    public boolean GivePermission(){
        java.lang.System.out.println("This is an extreme device!\nDo you approve the visitor's use of it? Y/N");
        Scanner input = new Scanner(java.lang.System.in);
        String approval = input.next();
        return approval.equals("Y");
    }
}