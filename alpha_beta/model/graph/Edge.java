package alpha_beta.model.graph;

import alpha_beta.model.game.State;

public class Edge {

    private int cost;

    private State from;

    private State to;

    public Edge(State from, State to) {
        this.from = from;
        this.to = to;
    }

    public Edge(int cost, State from, State to) {
        this(from, to);
        this.cost = cost;
    }

    public State prev () {
        return from;
    }

    public State next () {
        return to;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
