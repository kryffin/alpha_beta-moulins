package alpha_beta.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Board extends State {

    private MoulinBoardStructure struct;

    private HashMap<Placement, Player> board;

    private Player player1; //joueur courant

    private Player player2;

    private int ownPawnsToPlace;

    private int ownPawnsCount;

    private int advPawnsToPlace;

    private int advPawnsCount;

    public Board (Board b) {
        this.struct = new MoulinBoardStructure();
        this.board = new HashMap<>(b.getBoard());
        this.player1 = b.getPlayer1();
        this.player2 = b.getPlayer2();
        this.ownPawnsToPlace = b.getOwnPawnsToPlace();
        this.ownPawnsCount = b.getOwnPawnsCount();
        this.advPawnsToPlace = b.getAdvPawnsToPlace();
        this.advPawnsCount = b.getAdvPawnsCount();
    }

    public Board(Player player1, Player player2) {
        this.struct = new MoulinBoardStructure();
        board = new HashMap<>();
        for (char a = 'A'; a <= 'X'; a++) {
            board.put(new Placement(String.valueOf(a)), null);
        }
        this.player1 = player1;
        this.player2 = player2;
        this.ownPawnsToPlace = 9;
        this.ownPawnsCount = 9;
        this.advPawnsToPlace = 9;
        this.advPawnsCount = 9;
    }

    public Board(HashMap<Placement, Player> board, Player player1, Player player2, int ownPawnsToPlace, int ownPawnsCount, int advPawnsToPlace, int advPawnsCount) {
        this.struct = new MoulinBoardStructure();
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
    public Player currentPlayer() {
        return null;
    }

    @Override
    public boolean isGameOver() {
        return (advPawnsCount <= 2 || !iterator().hasNext());
    }

    @Override
    public Iterator<State> iterator() {
        ArrayList<State> moves = new ArrayList<>();

        if (ownPawnsToPlace != 0) {
            //placement : il reste des pions à placer on créer donc des fils de cet état pour chaque placement de pion possible

            for (char a = 'A'; a <= 'X'; a++) {
                if (board.get(new Placement(String.valueOf(a))) == null) {
                    HashMap<Placement, Player> h = new HashMap<>(board);
                    h.replace(new Placement(String.valueOf(a)), player1);
                    Board b = new Board(this);
                    b.setBoard(h);
                    b.setOwnPawnsToPlace(b.getOwnPawnsToPlace()-1);
                    moves.add(b);
                }
            }

        } else {
            //jeu : plus de pions à placer, on cherche donc à faire des déplacements

            if (ownPawnsCount == 3) {

                for (Placement p : board.keySet()) {
                    if (board.get(p) == player1) {
                        //parcours de nos pions

                        for (Placement pp : board.keySet()) {
                            //parcours de toutes les positions
                            if (board.get(pp) == null) {
                                HashMap<Placement, Player> h = new HashMap<>(board);
                                h.replace(p, null);
                                h.replace(pp, player1);
                                Board b = new Board(this);
                                b.setBoard(h);
                                moves.add(b);
                            }
                        }

                    }
                }

            } else {

                for (Placement p : board.keySet()) {
                    if (board.get(p) == player1) {
                        //parcours de nos pions

                        for (Placement pp : struct.neighbors(p)) {
                            //parcours des voisins
                            if (board.get(pp) == null) {
                                HashMap<Placement, Player> h = new HashMap<>(board);
                                h.replace(p, null);
                                h.replace(pp, player1);
                                Board b = new Board(this);
                                b.setBoard(h);
                                moves.add(b);
                            }
                        }

                    }
                }

            }

        }

        return moves.iterator();
    }

    public HashMap<Placement, Player> getBoard() {
        return board;
    }

    public void setBoard(HashMap<Placement, Player> board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
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
            sb.append(board.get(new Placement(String.valueOf(a))));

            if (a == 'C' || a == 'F' || a == 'I' || a == 'O' || a == 'R' || a == 'U' || a == 'X') {
                sb.append("\n");
            }

        }

        return sb.toString();
    }
}
