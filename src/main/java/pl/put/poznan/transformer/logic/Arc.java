package pl.put.poznan.transformer.logic;

public class Arc {
    Node from;
    Node to;
    double value;

    public Arc(Node from, Node to, double value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }
}
