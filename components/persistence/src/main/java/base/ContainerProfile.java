package base;

public class ContainerProfile {
    private String id;
    private String destination;
    private int numberOfBaggages;

    public ContainerProfile(String id, String destination, int numberOfBaggages) {
        this.id = id;
        this.destination = destination;
        this.numberOfBaggages = numberOfBaggages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumberOfBaggages() {
        return numberOfBaggages;
    }

    public void setNumberOfBaggages(int numberOfBaggages) {
        this.numberOfBaggages = numberOfBaggages;
    }

    @Override
    public String toString() {
        return "ContainerProfile{" +
                "destination='" + destination + '\'' +
                ", numberOfBaggages=" + numberOfBaggages +
                '}';
    }
}
