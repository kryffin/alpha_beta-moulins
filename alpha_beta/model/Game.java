package alpha_beta.model;

import alpha_beta.model.game.Board;
import alpha_beta.model.game.Player;
import alpha_beta.model.game.State;

import java.util.Iterator;

public class Game /*extends Application*/ {

    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("./alpha_beta/view/boardView.fxml"));
        primaryStage.setTitle("Moulins");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }*/

    public static void main(String[] args) {
        //launch(args);

        State s = new Board(new Player("1"), new Player("2"));

        int i = 0;
        while (!s.isGameOver()) {
            double eval = 0.d;
            State bestMove = null;

            Iterator<State> ite = s.iterator();
            bestMove = ite.next();
            State tmp;

            while (ite.hasNext()) {
                tmp = ite.next();
                if (eval < tmp.evaluate()) {
                    eval = tmp.evaluate();
                    bestMove = tmp;
                }
            }

            s.makeMove(bestMove);
            System.out.println(bestMove);
            i++;
        }

        System.out.println("\n\tWinner : " + s.getWinner());

    }

}
