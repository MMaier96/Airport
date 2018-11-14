package base;

public class Baggage {

    private String UUID;
    private String content;
    private double weight;
    private BaggageType baggageType;


    public Baggage(String UUID, String content, double weight, BaggageType baggageType) {
        this.UUID = UUID;
        this.weight = weight;
        this.baggageType = baggageType;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUUID() {
        return UUID;
    }

    public double getWeight() {
        return weight;
    }

    public BaggageType getBaggageType() {
        return baggageType;
    }

    public void setId(String id) {
        this.UUID = id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setBaggageType(BaggageType baggageType) {
        this.baggageType = baggageType;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "UUID='" + UUID + '\'' +
                ", content='" + content + '\'' +
                ", weight=" + weight +
                ", baggageType=" + baggageType +
                '}';
    }
}
