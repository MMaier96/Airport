package base;import java.util.ArrayList;

public class Storage {

    private ArrayList<Container> containerList;


    public ArrayList<Container> getContainerList() {
        return containerList;
    }

    public void setContainerList(ArrayList<Container> containerList) {
        this.containerList = containerList;
    }

    public Storage(ArrayList<Container> containerList) {
        this.containerList = containerList;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "containerList=" + containerList +
                '}';
    }
}
