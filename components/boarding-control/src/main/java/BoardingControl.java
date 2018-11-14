import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BoardingControl {
    private static BoardingControl instance = new BoardingControl();
    public Port port;

    private Object checkInDeskPort;
    private PassengerList boardedPassengerList;

    private BoardingControl() {
        port = new Port();
        checkInDeskPort = null;
        // boardedPassengerList = dbManager.getPassengerList();
    }

    public static BoardingControl getInstance() {
        return instance;
    }

    private void innerCallPassengers(PassengerList passengerList) {
        printMethodCall("callPassengers", false);
        if (!passengerList.getPassengerList().isEmpty()) {
            Passenger examplePassenger = passengerList.getPassengerList().get(0);
            String flightNumber = examplePassenger.getBoardingPass().getFlight();
            Source source = examplePassenger.getBoardingPass().getSource();
            Destination destination = examplePassenger.getBoardingPass().getDestination();
            String gate = examplePassenger.getBoardingPass().getGate();

            System.out.printf("Boarding for Flight %s from %s to %s is now beginning!\n", flightNumber,
                    source.name(), destination.name());

            System.out.println("Following passengers are now advised to adjourn to gate '" + gate + "':");
            printPassengers(passengerList);
        } else {
            System.out.println("No passengers to board available!");
        }
        printMethodCall("callPassengers", true);
    }

    private boolean innerInspectPassport(Passport passport) {
        return true;
    }

    private boolean innerScanBoardingPass(BoardingPass boardingPass) {
        return true;
    }

    private void innerPrintPassengerList(PassengerList passengerList) {
        printMethodCall("printPassengerList", false);
        printPassengers(passengerList);
        printMethodCall("printPassengerList", true);
    }

    private void innerNotifyGroundOperations(Object groundOperationPort) {
        try {
            Method method = groundOperationPort.getClass().getMethod("receive", BoardingControlReceipt.class);
            method.invoke(groundOperationPort, new BoardingControlReceipt(checkInDeskPort, boardedPassengerList));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    private void printPassengers(PassengerList passengers) {
        int passengerNumber = 1;
        for (Passenger passenger : passengers.getPassengerList()) {
            System.out.printf("%3d: %s\n", passengerNumber, passenger.toString());
            passengerNumber++;
        }
    }

    private void printMethodCall(String methodName, boolean end) {
        System.out.printf("--- %s.%s\n", (end ? "End " : "") + getClass().getName(), methodName);
    }

    public class Port implements IBoardingControl {

        public void call(PassengerList passengerList) {
            innerCallPassengers(passengerList);
        }

        public boolean inspect(Passport passport) {
            return innerInspectPassport(passport);
        }

        public boolean scan(BoardingPass boardingPass) {
            return innerScanBoardingPass(boardingPass);
        }

        public void printPassengerList(PassengerList passengerList) {
            innerPrintPassengerList(passengerList);
        }

        public void notifyGroundOperations(Object groundOperationPort) {
            innerNotifyGroundOperations(groundOperationPort);
        }
    }
}
