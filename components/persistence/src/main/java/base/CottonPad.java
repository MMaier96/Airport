package base;

public class CottonPad {
    private String id;
    private String surface;

    public CottonPad(String id ,String surface) {
        this.id = id;
        this.surface = surface;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    @Override
    public String toString() {
        return "CottonPad{" +
                "surface='" + surface + '\'' +
                '}';
    }
}
