package pl.put.poznan.transformer.logic;

import java.util.LinkedList;
import java.util.Random;

public class Jason {
    public Arc[] arcs;
    public Node[] nodes;
    public Jason(Node[] nodes,Arc[] arcs) {
        this.arcs=arcs;
        this.nodes=nodes;

    }

    public Arc[] getArcs() {
        return arcs;
    }

    public Node[] getNodes() {
        return nodes;
    }
}
