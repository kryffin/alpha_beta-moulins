package alpha_beta.model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Board extends State {

    private MoulinBoardStructure struct;

    private HashMap<Placement, Player> board;

    private alpha_beta.model.game.Player player1; //joueur courant

    private alpha_beta.model.game.Player player2;

    private int ownPawnsToPlace;

    private int ownPawnsCount;

    private int advPawnsToPlace;

    private int advPawnsCount;

    public Board (Board b) {
        this.struct = new alpha_beta.model.game.MoulinBoardStructure();
        this.board = new HashMap<>(b.getBoard());
        this.player1 = b.getPlayer1();
        this.player2 = b.getPlayer2();
        this.ownPawnsToPlace = b.getOwnPawnsToPlace();
        this.ownPawnsCount = b.getOwnPawnsCount();
        this.advPawnsToPlace = b.getAdvPawnsToPlace();
        this.advPawnsCount = b.getAdvPawnsCount();
    }

    public Board(alpha_beta.model.game.Player player1, alpha_beta.model.game.Player player2) {
        this.struct = new alpha_beta.model.game.MoulinBoardStructure();
        board = new HashMap<>();
        for (char a = 'A'; a <= 'X'; a++) {
            board.put(new alpha_beta.model.game.Placement(a), null);
        }
        this.player1 = player1;
        this.player2 = player2;
        this.ownPawnsToPlace = 9;
        this.ownPawnsCount = 9;
        this.advPawnsToPlace = 9;
        this.advPawnsCount = 9;
    }

    public Board(HashMap<alpha_beta.model.game.Placement, alpha_beta.model.game.Player> board, alpha_beta.model.game.Player player1, alpha_beta.model.game.Player player2, int ownPawnsToPlace, int ownPawnsCount, int advPawnsToPlace, int advPawnsCount) {
        this.struct = new alpha_beta.model.game.MoulinBoardStructure();
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.ownPawnsToPlace = ownPawnsToPlace;
        this.ownPawnsCount = ownPawnsCount;
        this.advPawnsToPlace = advPawnsToPlace;
        this.advPawnsCount = advPawnsCount;
    }

    @Override
    public int evaluate() {
        return 0;
    }

    @Override
    public alpha_beta.model.game.Player currentPlayer() {
        return null;
    }

    @Override
    public boolean isGameOver() {
        return (advPawnsCount <= 2 || !iterator().hasNext());
    }

    @Override
    public Iterator<alpha_beta.model.game.State> iterator() {
        ArrayList<alpha_beta.model.game.State> moves = new ArrayList<>();

        if (ownPawnsToPlace != 0) {
            //placement : il reste des pions à placer on créer donc des fils de cet état pour chaque placement de pion possible

            for (char a = 'A'; a <= 'X'; a++) {
                if (board.get(new alpha_beta.model.game.Placement(a)) == null) {
                    HashMap<alpha_beta.model.game.Placement, alpha_beta.model.game.Player> h = new HashMap<>(board);
                    h.replace(new alpha_beta.model.game.Placement(a), player1);
                    Board b = new Board(this);
                    b.setBoard(h);
                    b.setOwnPawnsToPlace(b.getOwnPawnsToPlace()-1);
                    moves.add(b);
                }
            }

        } else {
            //jeu : plus de pions à placer, on cherche donc à faire des déplacements

            if (ownPawnsCount == 3) {

                for (alpha_beta.model.game.Placement p : board.keySet()) {
                    if (board.get(p) == player1) {
                        //parcours de nos pions

                        for (alpha_beta.model.game.Placement pp : board.keySet()) {
                            //parcours de toutes les positions
                            if (board.get(pp) == null) {
                                HashMap<alpha_beta.model.game.Placement, alpha_beta.model.game.Player> h = new HashMap<>(board);
                                h.replace(p, null);
                                h.replace(pp, player1);
                                Board b = new Board(this);
                                b.setBoard(h);
                                if (isMoulin(pp)) {
                                    for (alpha_beta.model.game.Placement ppp : board.keySet()) {
                                        if (board.get(ppp) == player2) {
                                            HashMap<alpha_beta.model.game.Placement, alpha_beta.model.game.Player> hh = new HashMap<>(board);
                                            hh.replace(ppp, null);
                                            Board bb = new Board(this);
                                            bb.setBoard(hh);
                                            moves.add(bb);
                                        }
                                    }
                                } else {
                                    moves.add(b);
                                }
                            }
                        }

                    }
                }

            } else {

                for (alpha_beta.model.game.Placement p : board.keySet()) {
                    if (board.get(p) == player1) {
                        //parcours de nos pions

                        for (alpha_beta.model.game.Placement pp : struct.neighbors(p)) {
                            //parcours des voisins
                            if (board.get(pp) == null) {
                                HashMap<alpha_beta.model.game.Placement, alpha_beta.model.game.Player> h = new HashMap<>(board);
                                h.replace(p, null);
                                h.replace(pp, player1);
                                Board b = new Board(this);
                                b.setBoard(h);
                                if (isMoulin(pp)) {
                                    for (alpha_beta.model.game.Placement ppp : board.keySet()) {
                                        if (board.get(ppp) == player2) {
                                            HashMap<alpha_beta.model.game.Placement, alpha_beta.model.game.Player> hh = new HashMap<>(board);
                                            hh.replace(ppp, null);
                                            Board bb = new Board(this);
                                            bb.setBoard(hh);
                                            moves.add(bb);
                                        }
                                    }
                                } else {
                                    moves.add(b);
                                }
                            }
                        }

                    }
                }

            }

        }

        return moves.iterator();
    }

    private boolean isMoulin (alpha_beta.model.game.Placement p) {
        boolean isMoulin = false;
        for (Moulin m : struct.moulinOf(p)) {
            if (board.get(m.getA()) == player1 && board.get(m.getB()) == player1 && board.get(m.getC()) == player1) {
                isMoulin = true;
            }
        }
        return isMoulin;
    }

    public HashMap<alpha_beta.model.game.Placement, alpha_beta.model.game.Player> getBoard() {
        return board;
    }

    public void setBoard(HashMap<alpha_beta.model.game.Placement, alpha_beta.model.game.Player> board) {
        this.board = board;
    }

    public alpha_beta.model.game.Player getPlayer1() {
        return player1;
    }

    public alpha_beta.model.game.Player getPlayer2() {
        return player2;
    }

    public int getOwnPawnsToPlace() {
        return ownPawnsToPlace;
    }

    public void setOwnPawnsToPlace(int ownPawnsToPlace) {
        this.ownPawnsToPlace = ownPawnsToPlace;
    }

    public int getAdvPawnsToPlace() {
        return advPawnsToPlace;
    }

    public int getOwnPawnsCount() {
        return ownPawnsCount;
    }

    public void setOwnPawnsCount(int ownPawnsCount) {
        this.ownPawnsCount = ownPawnsCount;
    }

    public int getAdvPawnsCount() {
        return advPawnsCount;
    }

    public void setAdvPawnsCount(int advPawnsCount) {
        this.advPawnsCount = advPawnsCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (char a = 'A'; a <= 'X'; a++) {
            sb.append(board.get(new alpha_beta.model.game.Placement(a)));

            if (a == 'C' || a == 'F' || a == 'I' || a == 'O' || a == 'R' || a == 'U' || a == 'X') {
                sb.append("\n");
            }

        }

        return sb.toString();
    }
}
