package alpha_beta.model.game;

import java.util.Iterator;

public abstract class State implements Iterable<State> {

    public abstract void makeMove (String move, Player p);

    public abstract double evaluate (boolean enemy);

    public abstract Player currentPlayer ();

    public abstract boolean isGameOver ();

    public abstract Player getWinner ();

    @Override
    public abstract Iterator<State> iterator ();

}
