package base;import java.util.ArrayList;

public class PassengerList {

    private String id;
    private ArrayList<Passenger> passengerList;

    public PassengerList(ArrayList<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(String id, ArrayList<Passenger> passengerList) {
        this.id = id;
        this.passengerList = passengerList;
    }

    @Override
    public String toString() {
        return "PassengerList{" +
                "id='" + id + '\'' +
                ", passengerList=" + passengerList +
                '}';
    }
}
