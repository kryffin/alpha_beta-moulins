package alpha_beta.game;

import java.util.ArrayList;
import java.util.HashMap;

public class MoulinBoardStructure {

    private HashMap<Placement, Iterable<Placement>> neighbors;

    public MoulinBoardStructure () {
        initBoard();
    }

    private void initBoard () {
        neighbors = new HashMap<>();
        ArrayList<Placement> l = new ArrayList<>();

        l.add(new Placement("B"));
        l.add(new Placement("J"));
        neighbors.put(new Placement("A"), l);

        l = new ArrayList<>();
        l.add(new Placement("A"));
        l.add(new Placement("C"));
        l.add(new Placement("E"));
        neighbors.put(new Placement("B"), l);

        l = new ArrayList<>();
        l.add(new Placement("B"));
        l.add(new Placement("O"));
        neighbors.put(new Placement("C"), l);

        l = new ArrayList<>();
        l.add(new Placement("E"));
        l.add(new Placement("K"));
        neighbors.put(new Placement("D"), l);

        l = new ArrayList<>();
        l.add(new Placement("B"));
        l.add(new Placement("D"));
        l.add(new Placement("F"));
        l.add(new Placement("H"));
        neighbors.put(new Placement("E"), l);

        l = new ArrayList<>();
        l.add(new Placement("E"));
        l.add(new Placement("N"));
        neighbors.put(new Placement("F"), l);

        l = new ArrayList<>();
        l.add(new Placement("H"));
        l.add(new Placement("L"));
        neighbors.put(new Placement("G"), l);

        l = new ArrayList<>();
        l.add(new Placement("E"));
        l.add(new Placement("G"));
        l.add(new Placement("I"));
        neighbors.put(new Placement("H"), l);

        l = new ArrayList<>();
        l.add(new Placement("H"));
        l.add(new Placement("M"));
        neighbors.put(new Placement("I"), l);

        l = new ArrayList<>();
        l.add(new Placement("A"));
        l.add(new Placement("K"));
        l.add(new Placement("V"));
        neighbors.put(new Placement("J"), l);

        l = new ArrayList<>();
        l.add(new Placement("D"));
        l.add(new Placement("J"));
        l.add(new Placement("L"));
        l.add(new Placement("S"));
        neighbors.put(new Placement("K"), l);

        l = new ArrayList<>();
        l.add(new Placement("G"));
        l.add(new Placement("K"));
        l.add(new Placement("P"));
        neighbors.put(new Placement("L"), l);

        l = new ArrayList<>();
        l.add(new Placement("I"));
        l.add(new Placement("N"));
        l.add(new Placement("R"));
        neighbors.put(new Placement("M"), l);

        l = new ArrayList<>();
        l.add(new Placement("F"));
        l.add(new Placement("M"));
        l.add(new Placement("O"));
        l.add(new Placement("U"));
        neighbors.put(new Placement("N"), l);

        l = new ArrayList<>();
        l.add(new Placement("C"));
        l.add(new Placement("N"));
        l.add(new Placement("X"));
        neighbors.put(new Placement("O"), l);

        l = new ArrayList<>();
        l.add(new Placement("L"));
        l.add(new Placement("Q"));
        neighbors.put(new Placement("P"), l);

        l = new ArrayList<>();
        l.add(new Placement("P"));
        l.add(new Placement("R"));
        l.add(new Placement("T"));
        neighbors.put(new Placement("Q"), l);

        l = new ArrayList<>();
        l.add(new Placement("M"));
        l.add(new Placement("Q"));
        neighbors.put(new Placement("R"), l);

        l = new ArrayList<>();
        l.add(new Placement("K"));
        l.add(new Placement("T"));
        neighbors.put(new Placement("S"), l);

        l = new ArrayList<>();
        l.add(new Placement("Q"));
        l.add(new Placement("S"));
        l.add(new Placement("U"));
        l.add(new Placement("W"));
        neighbors.put(new Placement("T"), l);

        l = new ArrayList<>();
        l.add(new Placement("N"));
        l.add(new Placement("T"));
        neighbors.put(new Placement("U"), l);

        l = new ArrayList<>();
        l.add(new Placement("J"));
        l.add(new Placement("W"));
        neighbors.put(new Placement("V"), l);

        l = new ArrayList<>();
        l.add(new Placement("T"));
        l.add(new Placement("V"));
        l.add(new Placement("X"));
        neighbors.put(new Placement("W"), l);

        l = new ArrayList<>();
        l.add(new Placement("O"));
        l.add(new Placement("W"));
        neighbors.put(new Placement("X"), l);
    }

    public Iterable<Placement> neighbors (Placement p) {
        return neighbors.get(p);
    }

}
