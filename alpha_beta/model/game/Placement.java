package alpha_beta.model.game;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Placement {

    /**
     * coordonée de l'emplacement
     */
    private char coordinate;

    /**
     * Constructeur par coordonnée
     * @param coordinate coordonnée
     */
    public Placement(char coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return coordonnée de l'emplacement
     */
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
