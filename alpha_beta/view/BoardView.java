package alpha_beta.view;

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

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoardView extends AnchorPane {

    /**
     * HashMap des boutons des emplacements du plateau
     */
    private HashMap<Character, Button> buttons;

    /**
     * HashMap des label d'affichage en mode debug (appui sur 'd' pour afficher le debug)
     */
    private HashMap<String, Label> labels;

    /**
     * Plateau actuel du jeu
     */
    private Board s;

    /**
     * Stage de la fenêtre graphique
     */
    private Stage stage;

    /**
     * Joueur gagnant, null si le jeu n'est pas fini
     */
    private Player win;

    /**
     * vrai si le debug est à afficher, faux sinon
     */
    private boolean showDebug = false;

    /**
     * Constructeur vide
     */
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

    /**
     * Afficher / cacher les labels de debug (appui sur 'd')
     */
    public void toggleDebug () {
        showDebug = !showDebug;
        if (showDebug) {
            labels.get("j1").setVisible(true);
            labels.get("j2").setVisible(true);
            labels.get("nbPions").setVisible(true);
            labels.get("nbPionsE").setVisible(true);
            labels.get("aPlacer").setVisible(true);
            labels.get("aPlacerE").setVisible(true);
        } else {
            labels.get("j1").setVisible(false);
            labels.get("j2").setVisible(false);
            labels.get("nbPions").setVisible(false);
            labels.get("nbPionsE").setVisible(false);
            labels.get("aPlacer").setVisible(false);
            labels.get("aPlacerE").setVisible(false);
        }
    }

    /**
     * Setteur sur le stage de la fenêtre graphique
     * @param stage stage à lier à la classe
     */
    public void setStage (Stage stage) {
        this.stage = stage;
    }

    /**
     * Méthode utilisée lors de l'appui sur une des cases du plateau (non utilisé)
     * @param move lettre définissant la position clickée
     */
    private void move (char move) {
        System.out.println("Appui sur " + move + ".");
    }

    /**
     * vérifie si le plateau actuel est en état de victoire pour un des deux joueurs ou non
     */
    private void checkWin () {
        if (s.getOwnPawnsCount() < 3) {
            win = s.getPlayer2();
        } else if (s.getAdvPawnsCount() < 3) {
            win = s.getPlayer1();
        }
    }

    /**
     * Méthode d'affichage du gagnant dans une nouvelle fenêtre, suivi de la fermeture du jeu
     * @param winner joueur gagnant à afficher
     */
    private void printWinner (Player winner) {
        //création d'une nouvelle fenêtre de type Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nous avons un gagnant!");
        alert.setHeaderText(null);
        alert.setContentText("Victoire du Joueur " + winner);

        //affiche de la fenêtre, à sa fermeture on arrête l'application
        alert.showAndWait();
        stage.close();
        Platform.exit();
    }

    /**
     * Tour du joueur 1
     */
    private void ownTurn () {
        double eval;
        State bestMove;

        //première passe pour évaluer le meilleur score

        Iterator<State> ite = s.iterator();

        if (!ite.hasNext()) {
            //si aucun mouvement n'est possible on perd la partie
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
        }

        //mise à jour du plateau
        update();
    }

    /**
     * Tour du joueur 2
     */
    private void enemyTurn () {
        double eval;
        State bestMove;

        //première passe pour évaluer le meilleur score

        Iterator<State> ite = s.enemyIterator();

        if (!ite.hasNext()) {
            //si aucun mouvement n'est possible on perd la partie
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
        }

        //mise à jour du plateau
        update();
    }

    /**
     * Initialisation des labels de debug
     */
    private void initLabels () {
        labels = new HashMap<>();

        labels.put("j1", new Label("Joueur 1"));
        labels.put("j2", new Label("Joueur 2"));
        labels.get("j1").setStyle("-fx-background-color: #6666ff;");
        labels.get("j2").setStyle("-fx-background-color: #ff6666;");
        labels.get("j1").setLayoutX(100.d);
        labels.get("j1").setLayoutY(0.d);
        labels.get("j1").setVisible(false);
        labels.get("j2").setLayoutX(400.d);
        labels.get("j2").setLayoutY(0.d);
        labels.get("j2").setVisible(false);


        labels.put("aPlacer", new Label("Pions à placer : " + s.getOwnPawnsToPlace()));
        labels.put("nbPions", new Label("Pions en jeu : " + s.getOwnPawnsCount()));

        labels.get("aPlacer").setLayoutX(100.d);
        labels.get("aPlacer").setLayoutY(50.d);
        labels.get("aPlacer").setStyle("-fx-background-color: #6666ff;");
        labels.get("aPlacer").setVisible(false);

        labels.get("nbPions").setLayoutX(100.d);
        labels.get("nbPions").setLayoutY(150.d);
        labels.get("nbPions").setStyle("-fx-background-color: #6666ff;");
        labels.get("nbPions").setVisible(false);

        labels.put("aPlacerE", new Label("Pions à placer (adversaire) : " + s.getOwnPawnsToPlace()));
        labels.put("nbPionsE", new Label("Pions en jeu (adversaires) : " + s.getOwnPawnsCount()));

        labels.get("aPlacerE").setLayoutX(400.d);
        labels.get("aPlacerE").setLayoutY(50.d);
        labels.get("aPlacerE").setStyle("-fx-background-color: #ff6666;");
        labels.get("aPlacerE").setVisible(false);

        labels.get("nbPionsE").setLayoutX(400.d);
        labels.get("nbPionsE").setLayoutY(150.d);
        labels.get("nbPionsE").setStyle("-fx-background-color: #ff6666;");
        labels.get("nbPionsE").setVisible(false);

        //ajout des labels à la fenêtre graphique
        getChildren().addAll(labels.values());
    }

    /**
     * Initialisation des boutons
     */
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

        //ajout des boutons à la fenêtre graphique
        getChildren().addAll(buttons.values());
    }

    /**
     * Mise à jour du plateau de la fenêtre graphique
     */
    public void update() {
        Player p;
        for (char a = 'A'; a <= 'X'; a++) {
            p = s.getBoard().get(new Placement(a));
            if (p == null) {
                buttons.get(a).setStyle("-fx-background-color: #ffffff;"); //bouton blanc si aucune appartenance
            } else if (p == s.getPlayer1()) {
                buttons.get(a).setStyle("-fx-background-color: #6666ff;"); //bouton bleu si appartient au joueur 1
            } else if (p == s.getPlayer2()) {
                buttons.get(a).setStyle("-fx-background-color: #ff6666;"); //bouton rouge si appartient au joueur 2
            }
        }

        //mise à jour des labels de debug

        labels.get("aPlacer").setText("Pions à placer : " + s.getOwnPawnsToPlace());
        labels.get("nbPions").setText("Pions en jeu : " + s.getOwnPawnsCount());

        labels.get("aPlacerE").setText("Pions à placer (adversaire) : " + s.getAdvPawnsToPlace());
        labels.get("nbPionsE").setText("Pions en jeu (adversaire) : " + s.getAdvPawnsCount());
    }
}
