package alpha_beta.model.game;

import java.util.Iterator;

public abstract class State implements Iterable<State> {

    public abstract void makeMove (String move, Player p);

    public abstract double evaluate (boolean enemy);

    public abstract boolean isGameOver ();

    @Override
    public abstract Iterator<State> iterator ();

}
