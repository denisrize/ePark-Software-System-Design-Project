import java.util.*;

public class Main {
    public static void main(String[] args) {
        AmusementPark.init();
        AmusementPark.Ui();
    }
}

class AmusementPark {
        // Fields
        public static List<Object> systemObjects = new ArrayList<>();
        public static boolean exit = false;
        public static List<Ride> ParkRides = new ArrayList<>();
        public static List<GuardianAccount> guardianAccountList = new ArrayList<>();
        public static Scanner input = new Scanner(java.lang.System.in);

        /**
         * Initialize the park with 3 Rides "Mamba Ride", "Giant Wheel", "Carrousel"
         */
        // Methods


        public static void init() {
            Ride mambaRide = new ExtremeRide("Mamba Ride",
                    12, -1, (float) 1.4, 20);
            Ride giantWheel = new Ride("Giant Wheel",
                    -1, -1, -1, 0);
            Ride carrousel = new Ride("Carrousel",
                    8, -1, -1, 300);
            ParkRides.add(mambaRide);
            ParkRides.add(giantWheel);
            ParkRides.add(carrousel);
            systemObjects.add(mambaRide);
            systemObjects.add(giantWheel);
            systemObjects.add(carrousel);
        }

        /**
         * Interact with user as long as he doesn't exit
         */
        public static void Ui() {
            while (!AmusementPark.exit) {
                String input = getInput("Welcome to ePark!\n" +
                        "1.\tRegister child\n" +
                        "2.\tManage ticket: \n" +
                        "\t -Add Ride\n" +
                        "\t -Remove Ride\n" +
                        "3.\tExit park\n" +
                        "4.\tExit", new String[]{"1", "2", "3", "4"});
                if (input != null) // valid input
                    AmusementPark.handleInput(Integer.parseInt(input));
            }
        }

        /**
         * @param messageBeforeInput string printed before inputting. Null meaning don't print anything.
         * @param mustBe             string array containing all valid inputs user should insert. Null meaning no restrains on user input.
         * @return a string the user inputted. If the string was invalid returns null.
         */
        public static String getInput(String messageBeforeInput, String[] mustBe) {
            if (messageBeforeInput != null) java.lang.System.out.println(messageBeforeInput);
            String in = input.next();
            if (mustBe == null) return in;
            String mustBeAsString = "";
            for (String str : mustBe) {
                if (str.equals(in)) return in;
                mustBeAsString += ", " + str;
            }
            java.lang.System.out.println("Invalid input, input must be in " + mustBeAsString.substring(1) + "\nYou inserted " + in);
            return null;
        }

        /**
         * @param userChoice int representing the user choice of action.
         *                   This method only proceeds to call the relevant method according to user's choice.
         */
        public static void handleInput(int userChoice) {

            switch (userChoice) {
                case 1:
                    RegisterChild();
                    break;
                case 2:
                    ManageTicket();
                    break;
                case 3:
                    ExitPark();
                    break;
                case 4:
                    AmusementPark.exit = true;
                    break;
            }
        }

        /**
         * @param guardianId
         * @return a registered guardian that holds that ID, or null if such guardian doesn't exist
         */
        private static GuardianAccount findGuardian(String guardianId) {
            GuardianAccount guardianAccount = null;
            for (GuardianAccount g :
                    guardianAccountList) {
                try {
                    if (g.getID() == Integer.parseInt(guardianId)) guardianAccount = g;
                } catch (Exception e) {
                    java.lang.System.out.println("GuardianAccount's ID must be a number");
                }
            }
            return guardianAccount;
        }


        private static void ExitPark() {

            GuardianAccount guardianAccount = AmusementPark.findGuardian(AmusementPark.getInput("Please insert guardianAccount ID", null));
            if (guardianAccount == null) {
                java.lang.System.out.println("No such guardianAccount in the system.");
                return;
            }
            String appUserToDelete = AmusementPark.getInput("Please insert Visitor Control to remove", null);
            String sure = AmusementPark.getInput("Visitor control is about to removed, your decision is final?" +
                    "\n Press Y if yes, and N to no.", new String[]{"Y", "N"});
            if (sure == null) return;
            if (sure.equals("N")) {
                java.lang.System.out.println("Good to have you back.");
                return;
            }

            int charge = 0;
            for (VisitorControl au : guardianAccount.getVisitorControls()) {
                charge += au.getAmountToCharge();
                if (String.valueOf(au.getID()).equals(appUserToDelete)) {
                    for (Slots e : au.getETicket().getSlotsList()) {
                        systemObjects.remove(e);
                    }
                    systemObjects.remove(au.getETicket());
                    systemObjects.remove(au.getChild());
                    systemObjects.remove(au);
                    guardianAccount.getVisitorControls().remove(au);
                    if (guardianAccount.getVisitorControls().size() == 0) {
                        java.lang.System.out.println("Last Visitor control was removed, we removed your account until next time.");
                        systemObjects.remove(guardianAccount.getPayment());
                        systemObjects.remove(guardianAccount);
                        guardianAccountList.remove(guardianAccount);

                        java.lang.System.out.println("Calling credit card company to charge for all the visitors.");
                        CreditCardCompany.getCharged(guardianAccount.getPayment().getCreditCardNumber(), charge);
                        java.lang.System.out.println("Your account was removed Successfully, Thank you for visiting our Amusement Park we hope you had fun!");
                        return;
                    }
                }
            }
            java.lang.System.out.println("No such Visitor control.. try again");

        }

        /**
         * @param visitorControl app user to remove rides from.
         */
        private static void RemoveRide(VisitorControl visitorControl) {
            while (true) {
                java.lang.System.out.println("Which Ride would you like to remove?");
                java.lang.System.out.println("please choose the Ride by inserting the device number, or E to exit");
                for (int i = 0; i < visitorControl.getETicket().getSlotsList().size(); i++)
                    java.lang.System.out.println(i + ") " + visitorControl.getETicket().getSlotsList().get(i).getRide().getName());
                String choice = getInput(null, null);
                if (choice.equals("E")) break;
                try {
                    int iChoice = Integer.parseInt(choice);
                    Slots chosenSlots = visitorControl.getETicket().getSlotsList().get(iChoice);
                    Ride chosenRide = visitorControl.getETicket().getSlotsList().get(iChoice).getRide();
                    int slots = Integer.parseInt(AmusementPark.getInput("please choose the amount of slot to remove from e-Ticket", null));
                    if (slots > chosenSlots.getSlotsLeft())
                        java.lang.System.out.println("You don't have that much..");
                    else if (slots < 0) java.lang.System.out.println("Invalid input, try again");
                    else {
                        Slots toRem = visitorControl.getETicket().removeSlots(chosenRide, slots);
                        visitorControl.chargeForRide(chosenRide.getCost() * slots);
                        if (toRem != null) systemObjects.remove(toRem);
                        java.lang.System.out.println("Successfully removed!");

                    }
                } catch (Exception e) {
                    java.lang.System.out.println("Invalid input, try again");
                    continue;
                }
            }
        }

        /**
         * @param visitorControl to add rides to.
         */
        private static void AddRide(VisitorControl visitorControl) {
            Child child = visitorControl.getChild();
            List<Ride> allowedRides = new ArrayList<>();
            for (Ride ride : ParkRides) {
                if (ride.getMinAge() < child.getAge() && ride.getMinHeight() < child.getHeight() && ride.getMinWeight() < child.getWeight())
                    allowedRides.add(ride);
            }
            while (true) {
                java.lang.System.out.println("Allowed devices for your child:");
                for (int i = 0; i < allowedRides.size(); i++)
                    java.lang.System.out.println(i + ") " + allowedRides.get(i).getName() + ", costs: " + allowedRides.get(i).getCost());
                String choice = AmusementPark.getInput("please choose the ride you wish to add by number or press E to exit", null);
                if (choice.equals("E")) break;
                try {
                    int iChoice = Integer.parseInt(choice);
                    Ride chosenRide = allowedRides.get(iChoice);
                    if (!chosenRide.GivePermission()) {
                        java.lang.System.out.println("Not allowed");
                        continue;
                    }
                    ;
                    int slots = Integer.parseInt(AmusementPark.getInput("How many slots you want to add?", null));
                    if (chosenRide.getCost() * slots > visitorControl.getLimitLeft()) {
                        java.lang.System.out.println("You exceed your limit, you left with: " + visitorControl.getLimitLeft());
                        continue;
                    }
                    visitorControl.chargeForRide( chosenRide.getCost() * slots);
                    Slots slotsIfAdded = visitorControl.getETicket().addSlots(chosenRide, slots);
                    if (slotsIfAdded != null) systemObjects.add(slotsIfAdded);
                    java.lang.System.out.println("Ride has been add, enjoy!");
                } catch (Exception e) {
                    java.lang.System.out.println("Invalid input... Try again");
                    continue;
                }
            }

        }

        /**
         * either add or remove Slots from an Visitor control selected by the guardian after he verified.
         */
        private static void ManageTicket() {
            GuardianAccount guardianAccount = AmusementPark.findGuardian(AmusementPark.getInput("Please insert guardianAccount ID", null));
            if (guardianAccount == null) {
                java.lang.System.out.println("GuardianAccount was not found in the system. Please go to registration by option 1");
                return;
            }
            int userID = -1;
            try {
                userID = Integer.parseInt(AmusementPark.getInput("Please insert your visitor control user id:", null));
            } catch (Exception e) {
                java.lang.System.out.println("Invalid username");
                return;
            }
            String password = AmusementPark.getInput("Please insert your password:", null);
            boolean found = false;
            for (VisitorControl visitorControl :
                    guardianAccount.getVisitorControls()) {
                if (visitorControl.getID() == userID && visitorControl.getPassword().equals(password)) {
                    found = true;
                    java.lang.System.out.println("Would you like to see this visitor rides? press Y/N.");
                    String seeTicket = input.next();
                    if ( seeTicket.equals("Y")){
                        java.lang.System.out.println("Your visitor e-Ticket:");
                        visitorControl.getETicket().DisplayEticket();
                    }
                    else if(! seeTicket.equals("N")){
                        java.lang.System.out.println("wrong input, try again");
                        break;
                    }
                    java.lang.System.out.println("Would you like to see this visitor left money to spend? press Y/N.");
                    String seeAmountLeft = input.next();
                    if ( seeAmountLeft.equals("Y")) java.lang.System.out.println("Your visitor left with " + visitorControl.getLimitLeft() + " to spend." );
                    else if(!seeAmountLeft.equals("N")){
                        java.lang.System.out.println("wrong input, try again");
                        break;
                    }
                    java.lang.System.out.println("would you like to add or remove Ride to visitor? a = add, r = remove");
                    String addOrRemove = input.next();
                    if (addOrRemove.equals("a")) AddRide(visitorControl);
                    else if (addOrRemove.equals("r")) RemoveRide(visitorControl);
                    else java.lang.System.out.println("wrong input, try again");
                }
                if (!found) java.lang.System.out.println("Wrong username or password. Please retry.");
            }
        }

        /**
         * insert a new child in the Amusement park through the guardian.
         */
        private static void RegisterChild() {
            GuardianAccount activeGuardianAccount = findGuardianOrCreate();
            if (activeGuardianAccount == null) return;
            java.lang.System.out.println("Please fill the form: \n");
            String password = AmusementPark.getInput("Choose password:", null);
            String age = AmusementPark.getInput("Enter child age:", null);

            Child child = new Child(Integer.parseInt(age),
                    GuardianAccount.measureChildWeight(), GuardianAccount.measureChildHeight(), activeGuardianAccount);

            ETicket eTicket = new ETicket("Amusement Park entry", "31/12/2023");
            int limit = -1;
            while (limit == -1){
                try {
                    limit = Integer.parseInt(AmusementPark.getInput("Please insert limit money to spend: ", null));
                } catch (Exception e) {
                    java.lang.System.out.println("Invalid input.. Please try again");
                }
            }

            VisitorControl visitorControl = new VisitorControl(password, activeGuardianAccount,
                    eTicket, child,limit);
            java.lang.System.out.println("Your Visitor Control id is " + visitorControl.getID() + ", please remember that for later operations.");
            systemObjects.add(child);
            systemObjects.add(visitorControl);
            systemObjects.add(eTicket);
            activeGuardianAccount.getVisitorControls().add(visitorControl);
        }

        /**
         * @return an existing guardian if he is in the Amusement Park system, or creates a new one and returns it.
         */
        private static GuardianAccount findGuardianOrCreate() {
            String guardianId = AmusementPark.getInput("Please insert guardianAccount ID", null);
            GuardianAccount guardianAccount = AmusementPark.findGuardian(guardianId);
            if (guardianAccount != null) return guardianAccount;
            return CreateGuardian(guardianId);
        }

        /**
         * @param guardianId an id the created guardian will have
         * @return the created guardianAccount object.
         */
        private static GuardianAccount CreateGuardian(String guardianId) {
            String creditCardNumber = AmusementPark.getInput("Welcome!\n" +
                    "Please insert valid credit card number (five numbers)", null);
            String expirationDate = AmusementPark.getInput("Please insert credit card expiration date", null);
            String PaymentPassword = AmusementPark.getInput("Please insert credit card password", null);

            java.lang.System.out.println("Waiting for credit card validation");
            if (!CreditCardCompany.getValidated(creditCardNumber,expirationDate,PaymentPassword)){
                java.lang.System.out.println("Not valid, sorry try to register again.");
                return null;
            }

            Payment payment = new Payment(Integer.parseInt(creditCardNumber), expirationDate, PaymentPassword);
            GuardianAccount guardianAccount = new GuardianAccount(Integer.parseInt(guardianId),
                    payment);
            guardianAccountList.add(guardianAccount);
            systemObjects.add(guardianAccount);
            systemObjects.add(payment);
            return guardianAccount;

        }

        private static void PrintObjects() {
            for (Object o :
                    AmusementPark.systemObjects) {
                java.lang.System.out.println(o);
            }
        }
}

class CreditCardCompany {
        public static boolean getValidated(String creditNumber, String exp, String password) {
            if (creditNumber.length() != 5) return false;
            java.lang.System.out.println("The credit card with number: " +  creditNumber + " is valid.");
            return true;
        }
        public static void getCharged(int creditNumber, int charge){
            java.lang.System.out.println("Credit card with the following number: " + creditNumber + ", got charged for: " + charge);

        }
}
