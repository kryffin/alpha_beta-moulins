package alpha_beta.model.game;

import alpha_beta.view.BoardView;

import java.util.*;

public class Board extends State {

    private BoardView view;

    public static int PLACEMENT_COUNT = 24;

    private MoulinBoardStructure struct;

    private HashMap<Placement, Player> board;

    private Player player1; //currentPlayer = true

    private Player player2; //currentPlayer = false

    private boolean currentPlayer;

    private int ownPawnsToPlace;

    private int ownPawnsCount;

    private int advPawnsToPlace;

    private int advPawnsCount;

    private char whereToPlace;
    private char whichToRemove;
    private char whichToKill;

    public Board (Board b) {
        this.struct = new MoulinBoardStructure();
        this.board = new HashMap<>(b.getBoard());
        this.player1 = b.getPlayer1();
        this.player2 = b.getPlayer2();
        this.ownPawnsToPlace = b.getOwnPawnsToPlace();
        this.ownPawnsCount = b.getOwnPawnsCount();
        this.advPawnsToPlace = b.getAdvPawnsToPlace();
        this.advPawnsCount = b.getAdvPawnsCount();
        this.currentPlayer = b.isCurrentPlayer();
        this.view = b.view;
    }

    public Board (Player player1, Player player2, BoardView view) {
        this.struct = new alpha_beta.model.game.MoulinBoardStructure();
        board = new HashMap<>();
        for (char a = 'A'; a <= 'X'; a++) {
            board.put(new Placement(a), null);
        }
        this.player1 = player1;
        this.player2 = player2;
        this.ownPawnsToPlace = 9;
        this.ownPawnsCount = 9;
        this.advPawnsToPlace = 9;
        this.advPawnsCount = 9;
        this.currentPlayer = true;
        this.view = view;
    }

    public Board (HashMap<Placement, Player> board, Player player1, Player player2, int ownPawnsToPlace, int ownPawnsCount, int advPawnsToPlace, int advPawnsCount, boolean currentPlayer) {
        this.struct = new MoulinBoardStructure();
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.ownPawnsToPlace = ownPawnsToPlace;
        this.ownPawnsCount = ownPawnsCount;
        this.advPawnsToPlace = advPawnsToPlace;
        this.advPawnsCount = advPawnsCount;
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void makeMove (State s) {

        if (s == null) {
            System.out.println("State to make null");
            return;
        }

        if (s.getClass() != this.getClass()) {
            System.out.println("Error on type for making a move in the Board");
            return;
        }

        Board b = (Board) s;

        this.board = b.getBoard();
        this.player1 = b.getPlayer1();
        this.player2 = b.getPlayer2();
        this.ownPawnsToPlace = b.getOwnPawnsToPlace();
        this.ownPawnsCount = b.getOwnPawnsCount();
        this.advPawnsToPlace = b.getAdvPawnsToPlace();
        this.advPawnsCount = b.getAdvPawnsCount();
        this.currentPlayer = b.isCurrentPlayer();
    }

    @Override
    public double evaluate () {
        Player p1 = player1;
        Player p2 = player2;

        if (!isCurrentPlayer()) {
            p1 = player2;
            p2 = player1;
        }

        int aligned1 = alignedCount(p1);
        int aligned2 = alignedCount(p2);

        double gamma = 0.5d;

        double res = (aligned1 - aligned2 + (gamma * ((PLACEMENT_COUNT - aligned1) - (PLACEMENT_COUNT - aligned2))));
        return res;
    }

    private int alignedCount (Player p) {
        int count = 0;

        for (Placement pl : board.keySet()) {
            if (isMoulin(pl, p, board)) {
                count++;
            }
        }

        return count;
    }

    @Override
    public Player currentPlayer() {
        return null;
    }

    @Override
    public boolean isGameOver() {
        return (advPawnsCount <= 2 || ownPawnsCount <= 2 || !iterator().hasNext());
    }

    @Override
    public Iterator<State> iterator() {
        ArrayList<State> moves = new ArrayList<>();

        Player p1 = player1;
        Player p2 = player2;
        int toPlace = ownPawnsToPlace;
        int count = ownPawnsToPlace;
        if (!isCurrentPlayer()) {
            p1 = player2;
            p2 = player1;
            toPlace = advPawnsToPlace;
            count = advPawnsCount;
        }

        if (toPlace != 0) {
            //placement : il reste des pions à placer on créer donc des fils de cet état pour chaque placement de pion possible

            for (char a = 'A'; a <= 'X'; a++) {
                if (board.get(new Placement(a)) == null) {
                    HashMap<Placement, Player> h = new HashMap<>(board);
                    h.replace(new Placement(a), p1);
                    Board b = new Board(this);
                    b.setBoard(h);
                    if (isCurrentPlayer()) {
                        b.setOwnPawnsToPlace(b.getOwnPawnsToPlace()-1);
                    } else {
                        b.setAdvPawnsToPlace(b.getAdvPawnsToPlace() - 1);
                    }
                    b.setCurrentPlayer(!b.isCurrentPlayer());
                    b.setMoves(a + "ZZ"); //placement d'un pion en a
                    moves.add(b);
                }
            }

        } else {
            //jeu : plus de pions à placer, on cherche donc à faire des déplacements

            if (count == 3) {

                for (Placement p : board.keySet()) {
                    if (board.get(p) == p1) {
                        //parcours de nos pions

                        for (Placement pp : board.keySet()) {
                            //parcours de toutes les positions
                            if (board.get(pp) == null) {
                                HashMap<Placement, Player> h = new HashMap<>(board);
                                h.replace(p, null);
                                h.replace(pp, p1);
                                Board b = new Board(this);
                                b.setBoard(h);
                                if (isMoulin(pp, p1, h)) {
                                    //il y a un moulin, on cherche donc à tuer un pion
                                    for (Placement ppp : board.keySet()) {
                                        if (board.get(ppp) == p2) {
                                            HashMap<Placement, Player> hh = new HashMap<>(h);
                                            hh.replace(ppp, null);
                                            Board bb = new Board(this);
                                            bb.setBoard(hh);
                                            bb.setCurrentPlayer(!b.isCurrentPlayer());
                                            if (isCurrentPlayer()) {
                                                bb.setAdvPawnsCount(b.getAdvPawnsCount() - 1);
                                            } else {
                                                bb.setOwnPawnsCount(b.getOwnPawnsCount() - 1);
                                            }
                                            bb.setMoves(p.getCoordinate() + pp.toString() + ppp.toString());
                                            moves.add(bb);
                                        }
                                    }
                                } else {
                                    b.setCurrentPlayer(!b.isCurrentPlayer());
                                    b.setMoves(p.toString() + pp.toString() + 'Z');
                                    moves.add(b);
                                }
                            }
                        }

                    }
                }

            } else {

                for (Placement p : board.keySet()) {
                    if (board.get(p) == p1) {
                        //parcours de nos pions

                        for (Placement pp : struct.neighbors(p)) {
                            //parcours des voisins
                            if (board.get(pp) == null) {
                                HashMap<Placement, Player> h = new HashMap<>(board);
                                h.replace(p, null);
                                h.replace(pp, p1);
                                Board b = new Board(this);
                                b.setBoard(h);
                                if (isMoulin(pp, p1, h)) {
                                    for (Placement ppp : board.keySet()) {
                                        if (board.get(ppp) == p2) {
                                            HashMap<Placement, Player> hh = new HashMap<>(h);
                                            hh.replace(ppp, null);
                                            Board bb = new Board(this);
                                            bb.setBoard(hh);
                                            bb.setCurrentPlayer(!b.isCurrentPlayer());
                                            if (isCurrentPlayer()) {
                                                bb.setAdvPawnsCount(b.getAdvPawnsCount() - 1);
                                            } else {
                                                bb.setOwnPawnsCount(b.getOwnPawnsCount() - 1);
                                            }
                                            bb.setMoves(p.toString() + pp.toString() + ppp.toString());
                                            moves.add(bb);
                                        }
                                    }
                                } else {
                                    b.setCurrentPlayer(!b.isCurrentPlayer());
                                    b.setMoves(p.toString() + pp.toString() + 'Z');
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

    private boolean isMoulin (Placement p, Player player, HashMap<Placement, Player> b) {
        boolean isMoulin = false;
        for (Moulin m : struct.moulinOf(p)) {
            if (b.get(m.getA()) == player && b.get(m.getB()) == player && b.get(m.getC()) == player) {
                isMoulin = true;
            }
        }
        return isMoulin;
    }

    @Override
    public Player getWinner() {
        if (!isGameOver()) {
            return null;
        }

        if (isCurrentPlayer()) {
            if (ownPawnsCount <= 2) {
                return player2;
            }
            if (advPawnsCount <= 2 || !iterator().hasNext()) {
                return player1;
            }
        } else {
            if (ownPawnsCount <= 2) {
                return player1;
            }
            if (advPawnsCount <= 2 || !iterator().hasNext()) {
                return player2;
            }
        }

        return null;
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

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
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

    public void setAdvPawnsToPlace(int advPawnsToPlace) {
        this.advPawnsToPlace = advPawnsToPlace;
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

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getMoves () {
        StringBuilder sb = new StringBuilder();
        sb.append(whereToPlace);
        sb.append(whichToRemove);
        sb.append(whichToKill);
        return sb.toString();
    }

    private void updateBoard () {
        //si l'on reçois un mouvement c'est donc celui du joueur 2
        board.replace(new Placement(whereToPlace), player2);
        if (whichToRemove != 'Z') {
            board.replace(new Placement(whichToRemove), null);
        }
        if (whichToKill != 'Z') {
            board.replace(new Placement(whichToKill), null);
        }
    }

    public void setMoves (String s) {
        whereToPlace = s.charAt(0);
        whichToRemove = s.charAt(1);
        whichToKill = s.charAt(2);
        updateBoard();
    }

    public void updateView () {
        view.update(board, player1, player2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (isCurrentPlayer()) {
            sb.append(">>" + player1 + "  " +  player2 + "\nPawns : " + ownPawnsCount + "  advPawns : " + advPawnsCount + "\n");
            sb.append("alignés : " + alignedCount(player1) + "\n");
        } else {
            sb.append(player1 + "  >>" +  player2 + "\nPawns : " + advPawnsCount + "  advPawns : " + ownPawnsCount + "\n");
            sb.append("alignés : " + alignedCount(player2) + "\n");
        }

        sb.append(evaluate() + "\n");

        for (char a = 'A'; a <= 'X'; a++) {
            Player p = board.get(new Placement(a));
            if (p == null) {
                sb.append("X");
            } else {
                sb.append(p);
            }


            if (a == 'U') {
                sb.append(" |\n");
            } else if (a == 'A' || a == 'B' || a == 'V' || a == 'W') {
                sb.append("-----");
            } else if (a == 'D' | a == 'E' || a == 'S' || a == 'T') {
                sb.append("---");
            } else if (a == 'L') {
                sb.append("   ");
            } else if (a == 'R') {
                sb.append(" | |\n| ");
            } else if (a == 'C') {
                sb.append("\n| ");
            } else if (a == 'F') {
                sb.append(" |\n| | ");
            } else if (a == 'O') {
                sb.append("\n| | ");
            } else if (a == 'G' || a == 'H' || a == 'P' || a == 'Q' || a == 'J' || a == 'K' || a == 'M' || a == 'N') {
                sb.append("-");
            } else if (a == 'I') {
                sb.append(" | |\n");
            }

        }

        return sb.toString();
    }
}
