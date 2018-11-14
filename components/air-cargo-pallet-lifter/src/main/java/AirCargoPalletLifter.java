
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class AirCargoPalletLifter {

    private boolean isDown;

    private static AirCargoPalletLifter instance = new AirCargoPalletLifter();

    public Port port;

    public AirCargoPalletLifter() {
        port = new Port();
        isDown = true;
    }

    public static AirCargoPalletLifter getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "AirCargoPalletLifter XYZ";
    }

    public boolean innerUp(){
        isDown = false;
        return isDown;
    }

    public boolean innerDown(){
        isDown = true;
        return isDown;
    }

    public int innerMove(ArrayList<AirCargoPallet> airCargoPalletList, Stowage stowage){
        if (isDown) {
            innerUp();
            int i = airCargoPalletList.size();
            stowage.setAirCargoPalletList(airCargoPalletList);
            return i;
        }
        else{
        int i = airCargoPalletList.size();
        stowage.setAirCargoPalletList(airCargoPalletList);
        return i;
        }

    }

    public void innerNotifyGroundOperations(AirCargoPalletLifterReceipt airCargoPalletLifterReceipt) {
        Object groundOperationsPort = ComponentLoader.loadComponent("ground-operations-center", "GroundOperationsCenter");
        try {
            groundOperationsPort.getClass().getMethod("receive", AirCargoPalletLifterReceipt.class).invoke(groundOperationsPort, airCargoPalletLifterReceipt);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("could not notify ground operations center", e);
        }
    }


    public class Port implements IAirCargoPalletLifter {

        public boolean up(){
            return innerUp();
        }

        public boolean down(){
            return innerDown();
        }

        public int move(ArrayList<AirCargoPallet> airCargoPalletList, Stowage stowage){
            return innerMove(airCargoPalletList, stowage);
        }

        public void notifyGroundOperations(AirCargoPalletLifterReceipt airCargoPalletLifterReceipt){
            innerNotifyGroundOperations(airCargoPalletLifterReceipt);
        }

        public String version() {
            return innerVersion();
        }


    }









}