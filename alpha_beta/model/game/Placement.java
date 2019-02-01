package alpha_beta.model.game;

public class Placement {

    private char coordinate;

    public Placement(char coordinate) {
        this.coordinate = coordinate;
    }

    public char getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return coordinate + "";
    }

    @Override
    public int hashCode() {
        return new Character(coordinate).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Placement p = (Placement) obj;
        return coordinate == p.getCoordinate();
    }
}
