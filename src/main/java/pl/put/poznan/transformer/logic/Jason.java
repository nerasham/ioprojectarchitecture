package pl.put.poznan.transformer.logic;

import org.json.JSONArray;

import java.util.LinkedList;
import java.util.Random;

public class Jason {
    public Integer id;
    String name;
    NodeType type;
    public Object[] outgoing;
    public Object[] incoming;

    public Jason(Node node) {

        this.id = node.id;
        this.name = node.name;
        this.type = node.type;


           this.incoming=node.incoming.toArray();
           this.outgoing=node.incoming.toArray();



    }


    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public NodeType getType() {
        return type;
    }

    public Object[] getIncoming() {
        return incoming;
    }

    public Object[] getOutgoing() {
        return outgoing;
    }

}
