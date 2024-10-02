# Electronical Amusement Park
This project is part of my Software Systems Analysis and Design course. The main objective is to develop a complete software system starting from a use case. This includes creating use case diagrams, system sequence diagrams (SSD), and detailed class diagrams (DCD), as well as defining contracts and assigning responsibilities for each function. The final step is integrating all the components into a cohesive system.

## Story Framework:
ePark is an amusement park managed and operated through a computerized system that controls all the park's rides (devices), which are purchased from different manufacturers.
A child visiting the park, accompanied by a guardian, is registered and receives an electronic wristband to wear on their wrist. The wristband is used both for accessing rides and for locating the child within the park at any time (using GPS satellites). The guardian purchases ride entries for the child, and the child can only access a ride if all of the following conditions are met:

- Their electronic ticket (e-Ticket) contains a valid entry.
- The ride is appropriate for the child in terms of age, weight, and height restrictions.
- The time limit has not yet expired (see details below).
- The ride is operational and open.

Some rides are classified as "extreme" and require the guardian's special consent in addition to the conditions mentioned above.
The child can explore the park independently while the guardian monitors their activity through the internet, as detailed below. There are computer stations and a wireless network (Wi-Fi) available at the café next to the park, but in fact, the guardian can leave the child at the park and continue to monitor their activity online from anywhere by accessing the park’s website or via a dedicated mobile app, which can be downloaded from any app store.

Child registration for park use is done by an adult guardian on the park’s website using a credit card. Upon registration, the guardian receives an ID number for the child and a password that provides access to the child’s activities in the park. The child receives the wristband at the park entrance upon presenting the ID number. Upon registration, a purchase and credit account is opened for the guardian, which is managed as long as the child is in the park. When leaving the park and returning the wristband, the guardian’s credit card is charged for the total accumulated amount.

When the guardian logs into the website (or the app), they are presented with a map of the park, displaying the child’s location (accurate to 10 meters) using an appropriate icon. One guardian can monitor more than one child at a time, in which case the icons for all children under their supervision are displayed on the map. The children's locations are updated on the map every 5 seconds. The guardian can set a time limit for the child’s stay in the park—once the time expires, the child’s access to the rides will be blocked.

After successful registration, an electronic ticket is opened for the child, which the guardian can view on the website or app. Since access to some rides is restricted by age, weight, and height, there is a station at the park entrance for measuring height and weight, and the guardian can enter these details into the child's ticket. The ticket will include a list of entries to the park’s rides. The guardian can add or remove entries to specific rides. The ticket’s status (number of remaining entries, etc.) is updated each time the child enters or exits a ride. The guardian can check the status of the electronic ticket at any time by double-clicking on the child's icon on the map.

# Overview

This project is part of the Software Systems Analysis and Design course. It simulates an amusement park management system, focusing on use case-driven development. The system is built in Java and operates through a Command Line Interface (CLI). The goal is to allow users to manage a child's park experience, including registering children, managing their rides, and tracking their activity within the park.

## Features

![gui](https://github.com/user-attachments/assets/d4e7d063-4486-48f6-bf6c-dea99d924cc4)

- Register a child into the park system with an electronic ticket.
- Manage the child's ride entries by adding or removing rides.
- Support for three pre-defined rides:  
  1. **Mamba Ride**: Extreme ride, ages 12+, height 1.4+ meters  
  2. **Giant Wheel**: Suitable for all ages and heights  
  3. **Carrousel**: Suitable for ages 8+, all heights
- Simulate interactions with external systems (e.g., payments).
- The system continues to run until the user enters the `Exit` command.

## How to Use

### Prerequisites

- Ensure you have Java installed on your system.
- Compile the Java files to generate the necessary bytecode.

### Running the System

1. **Start the program:**  
   Run the compiled Java application from the command line. The system will initialize with the three pre-defined rides (Mamba Ride, Giant Wheel, and Carrousel).

2. **User Commands:**  
   The system will prompt you for input. Here are the supported commands and their descriptions:

   | Command                  | Description                                |
   | ------------------------ | ------------------------------------------ |
   | `Register child`          | Register a new child into the park system. |
   | `Manage ticket`           | Enter the ticket management menu.          |
   | `Add ride`                | Add a ride entry to the child's e-Ticket.  |
   | `Remove ride`             | Remove a ride entry from the child's e-Ticket. |
   | `Exit park`               | Log the child out of the park system.      |
   | `Exit`                    | Exit the entire system.                    |

3. **Command Input:**  
   Input commands one at a time, following the prompts. Each command should be entered on a separate line. The system will continue running until you input the `Exit` command, which will terminate the program.

### Example Workflow

1. **Register a child**: The system will ask for the necessary details (name, age, etc.).
2. **Manage ticket**: Enter the ticket management menu to add or remove rides for the child.
3. **Add ride**: Choose a ride from the available options and add it to the child's ticket.
4. **Remove ride**: If needed, you can remove a ride from the child's ticket.
5. **Exit park**: When the child is done, log them out of the system.
6. **Exit**: When you are finished, use the `Exit` command to close the program.

### Notes

- All inputs must be clear and unambiguous, as the system relies on precise command input.
- The system simulates interactions with external services (e.g., payment processing) as placeholders.
- The child’s ticket and ride statuses are updated in real-time as they interact with the system.


# Planing and Design:

## White diagram:

![whited1](https://github.com/user-attachments/assets/6fea1519-ad65-4756-b1dc-fa6eb1b1a768)

## Use Cases:

### Use Case 1

![usecase1](https://github.com/user-attachments/assets/ea2c69e4-33f9-4cd6-ac64-4d5ad24f0959)

#### Contracts:

**Operation: RegisterToPark()**
Cross References: Use case 1 Pre-conditions: - Singleton Amusement park is exist.
- Child Guardian is inside the web/app.
Post-conditions:
- A Child instance c was created(instance creation).
- A Guardian Account instance ga was created(instance creation).
- A e-Ticket instance ticket was created(instance creation).
- A visitor app instance va was created(instance creation).
- Va is associated with c and ga(association formed).
- ticket is associated with va(association formed).

**Operation**: enterChildWH (height: int, weight:int)
Cross References: Use case 1
Pre-conditions:
- Child Guardian has connected to his account instance ga.
- ga holds a visitor app instance va in the system.
Post-conditions:
- c.Height is updated to messured height (attribute modification).
- C.Weight is updated to messurd weight ( attribute modifications).

**DCD**

![DCD1](https://github.com/user-attachments/assets/e000b5ae-ff4a-4d08-a8fb-f068c1518567)

#### Responsibilities Decision:
**Operation: createGaurd()**
**Responsible class:** Amsuement Park
**Reason:** We decided to follow Larman's principal of controller Façade pattern, that suggest simplified interface to a complex subsystem such as Amusement Park in our case. Amusement Park holds all the necessary objects and control over the whole system to fulfill the responsibility to add a child for a Guardian to the system. In addition, System class is the front-facing interface masking this operation underlying.

**Operation: createChild(),createGuardian(),createVisitorControl(Child,e-Ticket, limit),createPayment(String, Integer)**
**Responsible class: Amsuement Park**
**Reason:** We decided to follow Larman's principal of controller Façade pattern for creation of this objects in the system. The main reason is that the Amusement park as the interface of the system and singleton object that exist when the system start to run, have to be responsible for the first creation of guardian object. it has the necessary objects and control to coordinate the interactions between the gaurdian and the other objects of the system. Also in the process of register, the Amusement park linked to the cerdit card company to validate the card so it already have the respobsibilty for the payment part, and so high cohesion it better to chose it for create payment.

**Operation: createETicket()**
**Responsible class:** Visitor control
**Reason:** We decided to follow Larman's principal of Information Expert for create the E-Ticket by the visitor control. The visitor control obtain all the necessary information and knowledge about the child and the payment for the ticket creation. For Low Coupling we decided that it better for the visitor control take this job instead of the Amsuement park which only have temporary visibility on this object as the system interface.

**Operation: setChildHeight(height: int),setChildWeight(weight:int)**
**Responsible class:** Amsuement Park
**Reason:** We decided to follow Larman's principal of controller Façade pattern, because it has the abillity to coordinate the information obtain from the guard to the child as the system controller. So for Low coupling we don’t want to connect directly the guard to child if we can avoide that.

### Use Case 2

![usecase22](https://github.com/user-attachments/assets/432f1339-9c6f-4c0e-bac3-3d060d34ff26)

#### Contracts:

**Operation: addSlotToRide(String rideName, Integer quantity)**
Cross References: Use case 2
Pre-conditions:
- Child Guardian has connected to his account instance ga.
- ga holds a visitor app instance va in the system.
- va has been validated that the associate child can use the specify ride.
- Ride instance ride with attribute name equals to ride rideName exist in the system.
Post-condition:
- ride has been associated with E-ticket instance ticket(instance creation).
- ticket was associated with ride (association formed).
- Slots instance slot is created if didn't exist already ( instance creation).
- slot was associated with both ticket and ride (association formed).

**Operation: removeSlotFromRide(String rideName, Integer quantity)**
Cross References: Use case 2
Pre-conditions:
- Child Guardian has connected to his account instance ga.
- ga holds a visitor app instance va in the system.
- va has been validated that the associate child can use the specify ride.
- Ride instance ride with attribute name equals to ride rideName exist in the system.
- Slot instance slot associated with both ticket and ride exist in the system.
Post-condition:
- slot attribute slot.amount is decreased by quantity ( attribute modification).

**SSD**:

![SSD2](https://github.com/user-attachments/assets/490cc75b-4f03-40da-9892-752271048bbb)

#### Responsibilities Decision:

**Operation: RetriveEticketInfo**
1. Responsible class: Visitor Control
2. Explanation: We considered using E-ticket as information expert but chose Visitor Control because of low coupling. We don’t have to create a coupling between the guardian and Eticket if we display through the system. That’s why we chose to look on Controller Pattern
   
**Operation: changeSlots(Boolean add)**
1. Responsible class: Ride
2. Explanation: We considered using Ride as information expert. In addition, add or remove entries is one of basis operation we want to able the user.

**Operation: modifySlotsToRide(String rideName, int quantity)** 
1. Responsible class: Eticket
2. Explanation: We considered using Eticket as information expert. In addition, add or remove entries is one of basis operation we want to able the user.

**Operation: isExtreme()**
1. Responsible class: Ride
2. Explanation: We considered using Ride as information expert, that is because only him now if the Ride Is a extreme device or not.

**Operation: removeSlots(String rideName, int quantity)** 
1. Responsible class: Eticket
2. Explanation: The same reason as the operation ModifySlotsToRide, We considered using Eticket as information expert.

### Use Case 3

![USECASE3](https://github.com/user-attachments/assets/17464483-22ab-4f5d-9b9f-cda70da9b01b)

#### Contracts:

**Operation: exitPark()**
Cross References: Use case 3
Pre-conditions:
- Child Guardian has connected to his account instance ga.
- Child has instance c in the system that ga holds.
- Visitor App has a instance va that belongs to ga and holds an E-Ticket instance ticket in the system.
Post-conditions:
- c,va,ticket instances are removed from the system.
- The guardian account was charged with the amount spended with the ticket.
Responsibilities Decision:
Operation:

**DCD**

![dcd3](https://github.com/user-attachments/assets/0c020213-ba46-4d72-8790-58017702b724)

#### Responsibilities Decision:

**Operation: getAmountToCharge()**
**Responssible class:** Amusement Park
**Reason:** We decided to follow Larman's principal of controller Façade , because the Amusement Park is the object that communicate with the credit card company so to cordinate the credit card details,amount to charge and send it to the credit card company is prefect for the job as the one that holds all the necessry objects for this job.

**Operation: chargeCreditCard( creditNumber, amount)**
**Responssible class:** Amusement Park
**Reason:** We decided to follow Larman's principal of controller Façade , because the Amusement Park holds all the necessry objects for this job and also it already had a communication with the credit card at the registeration of the gaurdian so for high cohesion it is best assign it to the job.

**Operation: removeChild()**
**Responssible class:** Amusement Park
**Reason:** We decided to follow Larman's principal Information Expert pattern. Amusement Park holds the information of childs and there Visitor control. Then Amusement Park need to have the ability to remove an exist child and is Visitor control. In addition, remove a child may cause a remove of his guardianAccount. As a result, Amusement Park should have all the information to operate a remove of a child.

### Use Case 4

This use case success scenrio do not include any functionallty requirements to the System from outside actor, instead it describes inner functionallty of our system. For that reason there is no SSD for it to write.
This inner functionallty happends every time a gaurdian tries to modify the E-Ticket of his child, which describes in use case 2. Base on use case 2 and this inner functionallty we bulid the following SD to clarify that full flow of modify Slots in E-Ticket on our system:

![usecase4](https://github.com/user-attachments/assets/8813ebde-3dac-47f3-91a3-25d5f8d6d71f)

#### Contracts:

**Operation: Add/RemoveRides(e-ticket e-ticket, int amount)**
Reference: Use Case 4
Preconditions:
- The child has a valid e-Ticket signed to system.
- The guardian asked to add or remove entries.
Postconditions:
- e-Ticket updated with current number of entries.
- The guardian's account updated.

**Operation: UpdateEticketEntries(int amount)**
Reference: Use Case 4
Preconditions:
- An instance of a valid e-Ticket signed to system.
- The guardian has a payment account.
- The guardian asked to add or remove entries, or the system identified entry or exit from a device. Postconditions:
- e-Ticket updated with current number of entries.
  
**Operation: UpdateAccountEntries(int amount)**
Reference: Use Case 4
Preconditions:
- The child has a valid e-Ticket signed to system.
- The guardian has a payment account.
- The guardian asked to add or remove entries, or the system identified entry or exit from a device.
Postconditions:
- The guardian's account updated.

#### Responsibilities Decision:

**Operation: Add/RemoveSlots**
**Responsible class:** Visitor Control
**Explanation:** Following Larman's principle Information Expert pattern we'll suggest assign Add/RemoveSlots() operation to Visitor Control class, due to that Visitor Control has the necessary information and objects to fulfill the responsibility.

**Operation: Verification that payment not exceeded limit**
**Responsible class:** Visitor Control
**Explanation:** Following Larman's principals Information Expert pattern we'll suggest assign verification of payment operation to Visitor Control class, due to that Visitor Control has the necessary information and objects to fulfill the responsibility.

## Final DCD

The following DCD do not include getters and setters despite that it is implemented in the code because we told that there is not need. Addionally the Amusement Park hold all the objects in the system but because it holds list of Rides and List of GaurdainAccount we added assosiation line to them to emphasize that:

![dcdfinal](https://github.com/user-attachments/assets/5385ba06-0494-4694-80bf-9e51666274dc)





