package alpha_beta.model.game;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Moulin implements Iterable<Placement> {

    /**
     * premier emplacement du moulin
     */
    private Placement A;

    /**
     * second emplacement du moulin
     */
    private Placement B;

    /**
     * troisième emplacement du moulin
     */
    private Placement C;

    /**
     * Constructeur du moulin par ses trois emplacements
     * @param A premier emplacement
     * @param B second emplacement
     * @param C troisième emplacement
     */
    public Moulin (char A, char B, char C) {
        this.A = new Placement(A);
        this.B = new Placement(B);
        this.C = new Placement(C);
    }

    /**
     * Retourne si l'emplacement a est contenu dans ce moulin
     * @param a emplacement à tester
     * @return vrai si a fait parti du moulin, faux sinon
     */
    public boolean contains (char a) {
        return (A.getCoordinate() == a || B.getCoordinate() == a || C.getCoordinate() == a);
    }

    /**
     * @return premier emplacement
     */
    public Placement getA() {
        return A;
    }

    /**
     * @return second emplacement
     */
    public Placement getB() {
        return B;
    }

    /**
     * @return troisième emplacement
     */
    public Placement getC() {
        return C;
    }

    /**
     * @return itérateur sur les emplacements du moulin
     */
    @Override
    public Iterator<Placement> iterator() {
        ArrayList<Placement> moulin = new ArrayList<>();
        moulin.add(A);
        moulin.add(B);
        moulin.add(C);

        return moulin.iterator();
    }
}
