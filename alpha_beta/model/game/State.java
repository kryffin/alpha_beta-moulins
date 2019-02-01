package alpha_beta.model.game;

import java.util.Iterator;

public abstract class State implements Iterable<State> {

    public abstract int evaluate ();

    public abstract Player currentPlayer ();

    public abstract boolean isGameOver ();

    @Override
    public abstract Iterator<State> iterator ();

}
