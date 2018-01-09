package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

@RestController
@RequestMapping("/jason")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicInteger counter2= new AtomicInteger();
    Node[] nodes;
    Arc[] arcs;


    void setUp() throws Exception {
        Random rand = new Random();
        nodes = new Node[10];
        arcs=new Arc[10];
        for(int i=0; i < 10; ++i){
            nodes[i] = new Node(i, "Name"+i, NodeType.ENTRY, new LinkedList<Arc>(), new LinkedList<Arc>());}

//        for(int i=0; i < 10; ++i) {
//            Integer f = rand.nextInt(10);
//            Integer s = (f + (rand.nextInt(10) % 9)) % 10;
//            Double val = rand.nextDouble();
//            Arc arc = new Arc(nodes[f], nodes[s], val);
//            nodes[f].insertToOutgoing(arc);
//            nodes[s].insertToIncoming(arc);
//        }
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET, produces = "application/json")
    public Node[] jason() throws Exception {

//        Integer[] aIn={5};
//        Integer[] aOut={3};
//        Integer[] bIn={6,5};
//        Integer[] bOut={3};
//        Integer[] cIn={1};
//        Integer[] cOut={4,6};
//        Integer[] dIn={3};
//        Integer[] dOut={5,7};
//        Integer[] eIn={4};
//        Integer[] eOut={1,2};
//        Integer[] fIn={3,7};
//        Integer[] fOut={2};
//        Integer[] gIn={4};
//        Integer[] gOut={6};
//        Node node1=new Node(counter2.incrementAndGet(),"1",NodeType.ENTRY,aIn,aOut);
//        Node node2=new Node(counter2.incrementAndGet(),"2",NodeType.REGULAR,bIn,bOut);
//        Node node3=new Node(counter2.incrementAndGet(),"3",NodeType.REGULAR,cIn,cOut);
//        Node node4=new Node(counter2.incrementAndGet(),"4",NodeType.REGULAR,dIn,dOut);
//        Node node5=new Node(counter2.incrementAndGet(),"5",NodeType.REGULAR,eIn,eOut);
//        Node node6=new Node(counter2.incrementAndGet(),"6",NodeType.REGULAR,fIn,fOut);
//        Node node7=new Node(counter2.incrementAndGet(),"7",NodeType.EXIT,gIn,gOut);
//        Node[] nodes={node1,node2,node3,node4,node5,node6,node7};
//
//        Arc arc1=new Arc(node1,node3,3);
//        Arc arc2=new Arc(node5,node1,2);
//        Arc arc3=new Arc(node2,node3,5);
//        Arc arc4=new Arc(node3,node4,1);
//        Arc arc5=new Arc(node3,node6,3);
//        Arc arc6=new Arc(node4,node7,4);
//        Arc arc7=new Arc(node4,node5,3);
//        Arc arc8=new Arc(node5,node2,1);
//        Arc arc9=new Arc(node6,node2,5);
//        Arc arc10=new Arc(node7,node6,9);
//        Arc[] arcs={arc1,arc2,arc3,arc4,arc5,arc6,arc7,arc8,arc9,arc10};
//        Node node1=new Node(counter2.incrementAndGet(),"1",NodeType.ENTRY,null,null);

        this.setUp();
        return nodes;

    }
    @RequestMapping(value = "/greedy",method = RequestMethod.GET, produces = "application/json")
    public  LinkedList<Integer> test() throws Exception {
        this.setUp();
        HashMap<Integer, Node> graph = new HashMap<Integer, Node>();
        LinkedList<Integer> path = nodes[0].findShortestPathGreedy(graph, nodes[0]);
        return path;

    }


//
//    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
//    public String post(@PathVariable String text,
//                      @RequestBody String[] transforms) {
//
//        // log the parameters
//        logger.debug(text);
//        logger.debug(Arrays.toString(transforms));
//
//        // do the transformation, you should run your logic here, below just a silly example
//        TextTransformer transformer = new TextTransformer(transforms);
//        return transformer.transform(text);
//    }



}




