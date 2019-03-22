package alpha_beta.model.game;

import java.util.ArrayList;
import java.util.Iterator;

public class Moulin implements Iterable<Placement> {

    private Placement A;
    private alpha_beta.model.game.Placement B;
    private alpha_beta.model.game.Placement C;

    public Moulin (char A, char B, char C) {
        this.A = new alpha_beta.model.game.Placement(A);
        this.B = new alpha_beta.model.game.Placement(B);
        this.C = new alpha_beta.model.game.Placement(C);
    }

    public boolean contains (char a) {
        return (A.getCoordinate() == a || B.getCoordinate() == a || C.getCoordinate() == a);
    }

    public alpha_beta.model.game.Placement getA() {
        return A;
    }

    public alpha_beta.model.game.Placement getB() {
        return B;
    }

    public alpha_beta.model.game.Placement getC() {
        return C;
    }

    @Override
    public Iterator<Placement> iterator() {
        ArrayList<Placement> moulin = new ArrayList<>();
        moulin.add(A);
        moulin.add(B);
        moulin.add(C);

        return moulin.iterator();
    }
}
