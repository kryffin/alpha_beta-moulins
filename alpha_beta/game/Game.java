package alpha_beta.game;

public class Game {

    public static void main (String[] args) {

        State s = new Board(new Player("N"), new Player("B"));

        for (State state : s) {
            System.out.println(state);
            System.out.println();
        }

    }

}
