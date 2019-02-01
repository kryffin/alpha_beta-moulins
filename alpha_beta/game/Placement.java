package alpha_beta.game;

public class Placement {

    private String coordinate;

    public Placement(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return coordinate;
    }

    @Override
    public int hashCode() {
        return coordinate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Placement p = (Placement) obj;
        return coordinate.equals(p.getCoordinate());
    }
}
