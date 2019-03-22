package alpha_beta.view;

import alpha_beta.client_server.Client;
import alpha_beta.model.game.Board;
import alpha_beta.model.game.Placement;
import alpha_beta.model.game.Player;
import alpha_beta.model.game.State;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class BoardView extends AnchorPane {

    private HashMap<Character, Button> buttons;
    private HashMap<String, Label> labels;
    private Board s;
    private Client client;
    private Stage stage;
    private Player win;

    public BoardView () {
        super();
        win = null;
        ImageView iv = new ImageView(new Image("board.jpg"));
        getChildren().add(iv);
        initButtons();
        s = new Board(new Player("1"), new Player("2"), this);
        initLabels();
        update();
    }

    public BoardView (Client client) {
        this();
        this.client = client;
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }

    private void move (char move) {
        System.out.println("Il y a eu volonté de clicker sur " + move + ".");
    }

    private void checkWin () {
        if (s.getOwnPawnsCount() < 3) {
            System.out.println("\n\nVICTOIRE DU JOUEUR 2\n\n");
            win = s.getPlayer2();
        } else if (s.getAdvPawnsCount() < 3) {
            System.out.println("\n\nVICTOIRE DU JOUEUR 1\n\n");
            win = s.getPlayer1();
        }
    }

    private void printWinner (Player winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nous avons un gagnant!");
        alert.setHeaderText(null);
        alert.setContentText("Victoire du Joueur " + winner);

        alert.showAndWait();
        stage.close();
        Platform.exit();
    }

    private void ownTurn () {
        double eval;
        State bestMove;

        //première passe pour évaluer le meilleur score

        Iterator<State> ite = s.iterator();

        if (!ite.hasNext()) {
            win = s.getPlayer2();
        } else {

            ArrayList<State> states = new ArrayList<>();
            bestMove = ite.next();
            eval = bestMove.evaluate(false);
            State tmp;
            String res = "";

            while (ite.hasNext()) {
                tmp = ite.next();
                states.add(tmp);
                if (eval < tmp.evaluate(false)) {
                    eval = tmp.evaluate(false);
                    bestMove = tmp;
                }
            }

            //deuxième passe pour récupérer les états dont le score est égal au score max

            ArrayList<State> bestStates = new ArrayList<>();

            for (State st : states) {
                if (st.evaluate(false) == eval) {
                    bestStates.add(st);
                }
            }

            Random r = new Random();
            bestMove = bestStates.get(r.nextInt(bestStates.size()));

            s.makeMove(((Board)bestMove).getMoves(), s.getPlayer1());

            System.out.println(">>" + s.getPlayer1() + " : " + s.getPlayer2());
            System.out.println(s);
            System.out.println("Mouvement effectué : " + ((Board)bestMove).getMoves() + "\n\n");

        }

        //envoi du move au serveur/client
        /*try {
            client.send(((Board)bestMove).getMoves());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        update();
    }

    private void enemyTurn () {
        double eval;
        State bestMove;

        //première passe pour évaluer le meilleur score

        Iterator<State> ite = s.enemyIterator();

        if (!ite.hasNext()) {
            win = s.getPlayer1();
        } else {

            ArrayList<State> states = new ArrayList<>();
            bestMove = ite.next();
            eval = bestMove.evaluate(true);
            State tmp;

            while (ite.hasNext()) {
                tmp = ite.next();
                states.add(tmp);
                if (eval < tmp.evaluate(true)) {
                    eval = tmp.evaluate(true);
                }
            }

            //deuxième passe pour récupérer les états dont le score est égal au score max

            ArrayList<State> bestStates = new ArrayList<>();

            for (State st : states) {
                if (st.evaluate(true) == eval) {
                    bestStates.add(st);
                }
            }

            Random r = new Random();
            bestMove = bestStates.get(r.nextInt(bestStates.size()));

            s.makeMove(((Board)bestMove).getMoves(), s.getPlayer2());
            //envoi du move au serveur/client
            /*try {
            client.send(((Board)bestMove).getMoves());
            } catch (Exception e) {
            e.printStackTrace();
            }*/
            System.out.println(s.getPlayer1() + " : >>" + s.getPlayer2());
            System.out.println(s);
            System.out.println("Mouvement effectué : " + ((Board)bestMove).getMoves() + "\n\n");

        }

        update();
    }

    private void initLabels () {
        labels = new HashMap<>();

        labels.put("j1", new Label("Joueur 1"));
        labels.put("j2", new Label("Joueur 2"));
        labels.get("j1").setStyle("-fx-background-color: #6666ff;");
        labels.get("j2").setStyle("-fx-background-color: #ff6666;");
        labels.get("j1").setLayoutX(100.d);
        labels.get("j1").setLayoutY(0.d);
        labels.get("j2").setLayoutX(400.d);
        labels.get("j2").setLayoutY(0.d);

        labels.put("aPlacer", new Label("Pions à placer : " + s.getOwnPawnsToPlace()));
        labels.put("nbPions", new Label("Pions en jeu : " + s.getOwnPawnsCount()));

        labels.get("aPlacer").setLayoutX(100.d);
        labels.get("aPlacer").setLayoutY(50.d);
        labels.get("aPlacer").setStyle("-fx-background-color: #6666ff;");

        labels.get("nbPions").setLayoutX(100.d);
        labels.get("nbPions").setLayoutY(150.d);
        labels.get("nbPions").setStyle("-fx-background-color: #6666ff;");

        labels.put("aPlacerE", new Label("Pions à placer (adversaire) : " + s.getOwnPawnsToPlace()));
        labels.put("nbPionsE", new Label("Pions en jeu (adversaires) : " + s.getOwnPawnsCount()));

        labels.get("aPlacerE").setLayoutX(400.d);
        labels.get("aPlacerE").setLayoutY(50.d);
        labels.get("aPlacerE").setStyle("-fx-background-color: #ff6666;");

        labels.get("nbPionsE").setLayoutX(400.d);
        labels.get("nbPionsE").setLayoutY(150.d);
        labels.get("nbPionsE").setStyle("-fx-background-color: #ff6666;");

        getChildren().addAll(labels.values());
    }

    private void initButtons () {
        buttons = new HashMap<>();

        for (char a = 'A'; a <= 'Y'; a++) {
            buttons.put(a, new Button(a + ""));
            if (a != 'Y') {
                char button = a;
                buttons.get(a).setOnAction(actionEvent -> move(button));
            }
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

        buttons.get('Y').setLayoutX(275.d);
        buttons.get('Y').setLayoutY(290.d);
        buttons.get('Y').setText("AI turn");
        buttons.get('Y').setStyle("-fx-background-color: #66ff66;");
        buttons.get('Y').setOnAction(actionEvent -> {
            ownTurn();
            checkWin();
            if (win == null) {
                enemyTurn();
                checkWin();
            }
            if (win != null) {
                printWinner(win);
            }
        });

        getChildren().addAll(buttons.values());
    }

    public void update() {
        Player p;
        for (char a = 'A'; a <= 'X'; a++) {
            p = s.getBoard().get(new Placement(a));
            if (p == null) {
                buttons.get(a).setStyle("-fx-background-color: #ffffff;");
            } else if (p == s.getPlayer1()) {
                buttons.get(a).setStyle("-fx-background-color: #6666ff;");
            } else if (p == s.getPlayer2()) {
                buttons.get(a).setStyle("-fx-background-color: #ff6666;");
            }
        }

        labels.get("aPlacer").setText("Pions à placer : " + s.getOwnPawnsToPlace());
        labels.get("nbPions").setText("Pions en jeu : " + s.getOwnPawnsCount());

        labels.get("aPlacerE").setText("Pions à placer (adversaire) : " + s.getAdvPawnsToPlace());
        labels.get("nbPionsE").setText("Pions en jeu (adversaire) : " + s.getAdvPawnsCount());
    }
}
