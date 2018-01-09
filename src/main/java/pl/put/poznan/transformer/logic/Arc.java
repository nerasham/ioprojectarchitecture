package pl.put.poznan.transformer.logic;

public class Arc {
    Integer from;
    Integer to;
    double value;

    public Arc(Integer from, Integer to, double value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }
}
