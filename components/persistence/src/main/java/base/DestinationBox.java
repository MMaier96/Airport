package base;import java.util.ArrayList;

public class DestinationBox {

    private ArrayList<Baggage> baggageList;

    public DestinationBox(ArrayList<Baggage> baggageList) {
        this.baggageList = baggageList;
    }

    public ArrayList<Baggage> getBaggegeList() {
        return baggageList;
    }

    public void setBaggegeList(ArrayList<Baggage> baggegeList) {
        this.baggageList = baggegeList;
    }

    @Override
    public String toString() {
        return "DestinationBox{" +
                ", baggegeList=" + baggageList +
                '}';
    }
}
