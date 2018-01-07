package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/jason")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicInteger counter2= new AtomicInteger();



    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Jason jason() {
        Integer[] aIn={5};
        Integer[] aOut={3};
        Integer[] bIn={6,5};
        Integer[] bOut={3};
        Integer[] cIn={1};
        Integer[] cOut={4,6};
        Integer[] dIn={3};
        Integer[] dOut={5,7};
        Integer[] eIn={4};
        Integer[] eOut={1,2};
        Integer[] fIn={3,7};
        Integer[] fOut={2};
        Integer[] gIn={4};
        Integer[] gOut={6};
        Node node1=new Node(counter2.incrementAndGet(),"1",NodeType.ENTRY,aIn,aOut);
        Node node2=new Node(counter2.incrementAndGet(),"2",NodeType.REGULAR,bIn,bOut);
        Node node3=new Node(counter2.incrementAndGet(),"3",NodeType.REGULAR,cIn,cOut);
        Node node4=new Node(counter2.incrementAndGet(),"4",NodeType.REGULAR,dIn,dOut);
        Node node5=new Node(counter2.incrementAndGet(),"5",NodeType.REGULAR,eIn,eOut);
        Node node6=new Node(counter2.incrementAndGet(),"6",NodeType.REGULAR,fIn,fOut);
        Node node7=new Node(counter2.incrementAndGet(),"7",NodeType.EXIT,gIn,gOut);
        Node[] nodes={node1,node2,node3,node4,node5,node6,node7};

        Arc arc1=new Arc(1,3,3);
        Arc arc2=new Arc(5,1,2);
        Arc arc3=new Arc(2,3,5);
        Arc arc4=new Arc(3,4,1);
        Arc arc5=new Arc(3,6,3);
        Arc arc6=new Arc(4,7,4);
        Arc arc7=new Arc(4,5,3);
        Arc arc8=new Arc(5,2,1);
        Arc arc9=new Arc(6,2,5);
        Arc arc10=new Arc(7,6,9);
        Arc[] arcs={arc1,arc2,arc3,arc4,arc5,arc6,arc7,arc8,arc9,arc10};

        return new Jason(nodes,arcs);

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




