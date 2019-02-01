package alpha_beta.model.graph;

import alpha_beta.model.game.State;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private int vertices;

    private HashMap<State, ArrayList<Edge>> adjNext;
    private HashMap<State, ArrayList<Edge>> adjPrev;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjNext = new HashMap<>();
        adjPrev = new HashMap<>();
    }

    public void addEdge (Edge e) {

        ArrayList<Edge> l = adjNext.get(e.prev());

        if (l == null) {
            l = new ArrayList<>();
            l.add(e);
            adjNext.put(e.prev(), l);
        } else {
            l.add(e);
            adjNext.replace(e.prev(), l);
        }

        l = adjPrev.get(e.next());

        if (l == null) {
            l = new ArrayList<>();
            l.add(e);
            adjNext.put(e.next(), l);
        } else {
            l.add(e);
            adjNext.replace(e.next(), l);
        }

    }

    public Iterable<Edge> prev (State s) {
        return adjPrev.get(s);
    }

    public Iterable<Edge> next (State s) {
        return adjNext.get(s);
    }

}
