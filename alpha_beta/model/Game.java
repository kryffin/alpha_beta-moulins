package alpha_beta.model;

import alpha_beta.model.game.Board;
import alpha_beta.model.game.Player;
import alpha_beta.model.game.State;

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

        for (State ss : s) {
            System.out.println(ss);
            System.out.println();
            for (State sss : ss) {
                System.out.println(sss);
                System.out.println();
            }
        }

    }

}
