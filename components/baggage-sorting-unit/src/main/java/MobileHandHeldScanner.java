import java.util.ArrayList;
import java.util.stream.Collectors;

public class MobileHandHeldScanner {
    private BaggageSortingUnit baggageSortingUnit;
    private DestinationBox destinationBox;
    private ContainerType containerType;
    // TODO: wird das Feld container gebraucht?
    private Container container;
    private ArrayList<BaggageIdentificationTag> baggageIdentificationTagList;
    private Roboter roboter;


    public void register(DestinationBox destinationBox){
        this.destinationBox = destinationBox;
    }

    public ArrayList<BaggageIdentificationTag> select(ContainerCategory containerCategory) {
        ArrayList<BaggageIdentificationTag> returnList = new ArrayList<BaggageIdentificationTag>();
        for (Baggage baggage: destinationBox.getBaggegeList()) {
            BaggageIdentificationTag tag = this.resolveTag(baggage);
            if(tag.getBoardingPass().getTicketClass().toString().toLowerCase().contains(containerCategory.toString().toLowerCase())){
                returnList.add(tag);
            }
        }

        return returnList;
    }

    private BaggageIdentificationTag resolveTag(Baggage baggage) {
        for (BaggageIdentificationTag tag : this.baggageIdentificationTagList) {
            if(tag.getId() == baggage.getId()) {
                return tag;
            }
        }
        return null;
    }

    public void orderRoboterToLoad(ArrayList<BaggageIdentificationTag> baggageIdentificationTagList, Container container, BaggageVehicle vehicle) {
        container.getProfile().setNumberOfBaggages(baggageIdentificationTagList.size());
        ArrayList<Baggage> tagBaggages = this.getBaggageForTags(baggageIdentificationTagList);
        this.roboter.load(tagBaggages, container);
        this.roboter.load(container, vehicle);
        this.roboter.orderBaggageVehicleMove(vehicle, this.baggageSortingUnit.getPosition());
    }

    private ArrayList<Baggage> getBaggageForTags(ArrayList<BaggageIdentificationTag> baggageIdentificationTagList) {
        ArrayList<Baggage> returnList = new ArrayList<Baggage>();
        for (BaggageIdentificationTag tag: baggageIdentificationTagList) {
           returnList.add(this.resolveBaggage(tag));
        }

        return returnList;
    }

    private Baggage resolveBaggage(BaggageIdentificationTag tag) {
        for (Baggage baggage : this.destinationBox.getBaggegeList()) {
            if(tag.getId() == baggage.getId()) {
                return baggage;
            }
        }
        return null;
    }

    public MobileHandHeldScanner(ContainerType containerType, BaggageSortingUnit baggageSortingUnit, ArrayList<BaggageIdentificationTag> baggageIdentificationTagList) {
        this.containerType = containerType;
        this.baggageSortingUnit = baggageSortingUnit;
        this.roboter = new Roboter();
        this.baggageIdentificationTagList = baggageIdentificationTagList;
    }

}
