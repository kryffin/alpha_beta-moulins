package alpha_beta.view;

import alpha_beta.model.game.Board;
import alpha_beta.model.game.Placement;
import alpha_beta.model.game.Player;
import alpha_beta.model.game.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Iterator;

public class BoardView extends AnchorPane {

    private HashMap<Character, Button> buttons;
    private Board s;

    public BoardView () {
        super();
        ImageView iv = new ImageView(new Image("board.jpg"));
        getChildren().add(iv);
        initButtons();
        s = new Board(new Player("1"), new Player("2"), this);
    }

    private void move () {
        double eval = 0.d;
        State bestMove = null;

        Iterator<State> ite = s.iterator();
        bestMove = ite.next();
        eval = bestMove.evaluate();
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
        update(s.getBoard(), s.getPlayer1(), s.getPlayer2());
    }

    private void initButtons () {
        buttons = new HashMap<>();

        for (char a = 'A'; a <= 'X'; a++) {
            buttons.put(a, new Button(a + ""));
            buttons.get(a).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    move();
                }
            });
        }

        buttons.get('A').setLayoutX(0.d);
        buttons.get('A').setLayoutY(0.d);

        buttons.get('B').setLayoutX(600.d / 2.d);
        buttons.get('B').setLayoutY(0.d);

        buttons.get('C').setLayoutX(560.d);
        buttons.get('C').setLayoutY(0.d);

        buttons.get('D').setLayoutX(600.d / 5.d);
        buttons.get('D').setLayoutY(90.d);

        buttons.get('E').setLayoutX(600.d / 2.d);
        buttons.get('E').setLayoutY(90.d);

        buttons.get('F').setLayoutX((600.d / 5.d)*4.d);
        buttons.get('F').setLayoutY(90.d);

        buttons.get('G').setLayoutX((600.d / 6.d) * 2.d);
        buttons.get('G').setLayoutY(180.d);

        buttons.get('H').setLayoutX(600.d / 2.d);
        buttons.get('H').setLayoutY(180.d);

        buttons.get('I').setLayoutX((600.d / 6.d) * 4.d);
        buttons.get('I').setLayoutY(180.d);

        buttons.get('J').setLayoutX(0.d);
        buttons.get('J').setLayoutY(270.d);

        buttons.get('K').setLayoutX(600.d / 7.d);
        buttons.get('K').setLayoutY(270.d);

        buttons.get('L').setLayoutX((600.d / 7.d) * 2.d);
        buttons.get('L').setLayoutY(270.d);

        buttons.get('M').setLayoutX((650.d / 7.d) * 4.d);
        buttons.get('M').setLayoutY(270.d);

        buttons.get('N').setLayoutX((650.d / 7.d) * 5.d);
        buttons.get('N').setLayoutY(270.d);

        buttons.get('O').setLayoutX((650.d / 7.d) * 6.d);
        buttons.get('O').setLayoutY(270.d);

        buttons.get('P').setLayoutX((600.d / 6.d) * 2.d);
        buttons.get('P').setLayoutY(380.d);

        buttons.get('Q').setLayoutX(600.d / 2.d);
        buttons.get('Q').setLayoutY(380.d);

        buttons.get('R').setLayoutX((600.d / 6.d) * 4.d);
        buttons.get('R').setLayoutY(380.d);

        buttons.get('S').setLayoutX(600.d / 5.d);
        buttons.get('S').setLayoutY(450.d);

        buttons.get('T').setLayoutX(600.d / 2.d);
        buttons.get('T').setLayoutY(450.d);

        buttons.get('U').setLayoutX((600.d / 5.d)*4.d);
        buttons.get('U').setLayoutY(450.d);

        buttons.get('V').setLayoutX(0.d);
        buttons.get('V').setLayoutY(550.d);

        buttons.get('W').setLayoutX(600.d / 2.d);
        buttons.get('W').setLayoutY(550.d);

        buttons.get('X').setLayoutX(560.d);
        buttons.get('X').setLayoutY(550.d);


        getChildren().addAll(buttons.values());
    }

    public void update(HashMap<Placement, Player> board, Player p1, Player p2) {
        Player p;
        for (char a = 'A'; a <= 'X'; a++) {
            p = board.get(new Placement(a));
            if (p == null) {
                buttons.get(a).setText(a + "");
                buttons.get(a).setStyle("-fx-background-color: #ffffff;");
            } else if (p == p1) {
                buttons.get(a).setText(a + " " + p1);
                buttons.get(a).setStyle("-fx-background-color: #6666ff;");
            } else if (p == p2) {
                buttons.get(a).setText(a + " " + p2);
                buttons.get(a).setStyle("-fx-background-color: #ff6666;");
            }
        }
    }
}
