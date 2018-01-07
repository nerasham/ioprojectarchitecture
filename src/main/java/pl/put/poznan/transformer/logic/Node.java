package pl.put.poznan.transformer.logic;


public class Node {
    private final Integer id;
    private final String name;
    private final NodeType type;
    private final Integer[] outgoing;
    private final Integer[] incoming;

    public Node(Integer id, String name, NodeType type, Integer[] incoming, Integer[] outgoing) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.outgoing = outgoing;
        this.incoming = incoming;
    }

    public String getName() {
        return name;
    }

    public NodeType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }
    public Integer[] getOutgoing() {
        return outgoing;
    }
    public Integer[] getIncoming() {
        return incoming;
    }


}
