package pl.put.poznan.transformer.logic;

public class Jason {
    private final Node[] nodes;
    private final Arc[] arcs;

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
