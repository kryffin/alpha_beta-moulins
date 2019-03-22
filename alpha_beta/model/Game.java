package alpha_beta.model;

import alpha_beta.model.game.Board;
import alpha_beta.model.game.Player;
import alpha_beta.model.game.State;
import alpha_beta.view.BoardView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Game extends Application {

    private static BoardView view;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Moulins");
        view.setStage(primaryStage);
        Scene scene = new Scene(view, 600, 600);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.D) {
                    view.toggleDebug();
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        view = new BoardView();
        State s = new Board(new Player("1"), new Player("2"), view);

        launch(args);
    }

}
