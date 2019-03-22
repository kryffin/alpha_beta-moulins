package alpha_beta.model.game;

import java.util.ArrayList;
import java.util.HashMap;

public class MoulinBoardStructure {

    private HashMap<Placement, Iterable<alpha_beta.model.game.Placement>> neighbors;

    private ArrayList<alpha_beta.model.game.Moulin> moulins;

    public MoulinBoardStructure () {
        initBoard();
        initMoulins();
    }

    private void initBoard () {
        neighbors = new HashMap<>();
        ArrayList<alpha_beta.model.game.Placement> l = new ArrayList<>();

        l.add(new alpha_beta.model.game.Placement('B'));
        l.add(new alpha_beta.model.game.Placement('J'));
        neighbors.put(new alpha_beta.model.game.Placement('A'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('A'));
        l.add(new alpha_beta.model.game.Placement('C'));
        l.add(new alpha_beta.model.game.Placement('E'));
        neighbors.put(new alpha_beta.model.game.Placement('B'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('B'));
        l.add(new alpha_beta.model.game.Placement('O'));
        neighbors.put(new alpha_beta.model.game.Placement('C'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('E'));
        l.add(new alpha_beta.model.game.Placement('K'));
        neighbors.put(new alpha_beta.model.game.Placement('D'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('B'));
        l.add(new alpha_beta.model.game.Placement('D'));
        l.add(new alpha_beta.model.game.Placement('F'));
        l.add(new alpha_beta.model.game.Placement('H'));
        neighbors.put(new alpha_beta.model.game.Placement('E'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('E'));
        l.add(new alpha_beta.model.game.Placement('N'));
        neighbors.put(new alpha_beta.model.game.Placement('F'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('H'));
        l.add(new alpha_beta.model.game.Placement('L'));
        neighbors.put(new alpha_beta.model.game.Placement('G'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('E'));
        l.add(new alpha_beta.model.game.Placement('G'));
        l.add(new alpha_beta.model.game.Placement('I'));
        neighbors.put(new alpha_beta.model.game.Placement('H'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('H'));
        l.add(new alpha_beta.model.game.Placement('M'));
        neighbors.put(new alpha_beta.model.game.Placement('I'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('A'));
        l.add(new alpha_beta.model.game.Placement('K'));
        l.add(new alpha_beta.model.game.Placement('V'));
        neighbors.put(new alpha_beta.model.game.Placement('J'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('D'));
        l.add(new alpha_beta.model.game.Placement('J'));
        l.add(new alpha_beta.model.game.Placement('L'));
        l.add(new alpha_beta.model.game.Placement('S'));
        neighbors.put(new alpha_beta.model.game.Placement('K'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('G'));
        l.add(new alpha_beta.model.game.Placement('K'));
        l.add(new alpha_beta.model.game.Placement('P'));
        neighbors.put(new alpha_beta.model.game.Placement('L'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('I'));
        l.add(new alpha_beta.model.game.Placement('N'));
        l.add(new alpha_beta.model.game.Placement('R'));
        neighbors.put(new alpha_beta.model.game.Placement('M'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('F'));
        l.add(new alpha_beta.model.game.Placement('M'));
        l.add(new alpha_beta.model.game.Placement('O'));
        l.add(new alpha_beta.model.game.Placement('U'));
        neighbors.put(new alpha_beta.model.game.Placement('N'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('C'));
        l.add(new alpha_beta.model.game.Placement('N'));
        l.add(new alpha_beta.model.game.Placement('X'));
        neighbors.put(new alpha_beta.model.game.Placement('O'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('L'));
        l.add(new alpha_beta.model.game.Placement('Q'));
        neighbors.put(new alpha_beta.model.game.Placement('P'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('P'));
        l.add(new alpha_beta.model.game.Placement('R'));
        l.add(new alpha_beta.model.game.Placement('T'));
        neighbors.put(new alpha_beta.model.game.Placement('Q'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('M'));
        l.add(new alpha_beta.model.game.Placement('Q'));
        neighbors.put(new alpha_beta.model.game.Placement('R'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('K'));
        l.add(new alpha_beta.model.game.Placement('T'));
        neighbors.put(new alpha_beta.model.game.Placement('S'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('Q'));
        l.add(new alpha_beta.model.game.Placement('S'));
        l.add(new alpha_beta.model.game.Placement('U'));
        l.add(new alpha_beta.model.game.Placement('W'));
        neighbors.put(new alpha_beta.model.game.Placement('T'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('N'));
        l.add(new alpha_beta.model.game.Placement('T'));
        neighbors.put(new alpha_beta.model.game.Placement('U'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('J'));
        l.add(new alpha_beta.model.game.Placement('W'));
        neighbors.put(new alpha_beta.model.game.Placement('V'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('T'));
        l.add(new alpha_beta.model.game.Placement('V'));
        l.add(new alpha_beta.model.game.Placement('X'));
        neighbors.put(new alpha_beta.model.game.Placement('W'), l);

        l = new ArrayList<>();
        l.add(new alpha_beta.model.game.Placement('O'));
        l.add(new alpha_beta.model.game.Placement('W'));
        neighbors.put(new alpha_beta.model.game.Placement('X'), l);
    }

    private void initMoulins () {
        moulins = new ArrayList<>();

        moulins.add(new alpha_beta.model.game.Moulin('A', 'B', 'C'));
        moulins.add(new alpha_beta.model.game.Moulin('D', 'E', 'F'));
        moulins.add(new alpha_beta.model.game.Moulin('G', 'H', 'I'));
        moulins.add(new alpha_beta.model.game.Moulin('P', 'Q', 'R'));
        moulins.add(new alpha_beta.model.game.Moulin('S', 'T', 'U'));
        moulins.add(new alpha_beta.model.game.Moulin('V', 'W', 'X'));
        moulins.add(new alpha_beta.model.game.Moulin('A', 'J', 'V'));
        moulins.add(new alpha_beta.model.game.Moulin('D', 'K', 'S'));
        moulins.add(new alpha_beta.model.game.Moulin('G', 'L', 'P'));
        moulins.add(new alpha_beta.model.game.Moulin('T', 'M', 'R'));
        moulins.add(new alpha_beta.model.game.Moulin('F', 'N', 'U'));
        moulins.add(new alpha_beta.model.game.Moulin('C', 'O', 'X'));
        moulins.add(new alpha_beta.model.game.Moulin('J', 'K', 'L'));
        moulins.add(new alpha_beta.model.game.Moulin('M', 'N', 'O'));
        moulins.add(new alpha_beta.model.game.Moulin('B', 'E', 'H'));
        moulins.add(new alpha_beta.model.game.Moulin('Q', 'T', 'W'));
    }

    public Iterable<alpha_beta.model.game.Placement> neighbors (alpha_beta.model.game.Placement p) {
        return neighbors.get(p);
    }

    public Iterable<alpha_beta.model.game.Moulin> moulinOf (alpha_beta.model.game.Placement p) {
        ArrayList<alpha_beta.model.game.Moulin> lm = new ArrayList<>();
        for (alpha_beta.model.game.Moulin m : moulins) {
            if (m.contains(p.getCoordinate())) {
                lm.add(m);
            }
        }
        return lm;
    }

}
