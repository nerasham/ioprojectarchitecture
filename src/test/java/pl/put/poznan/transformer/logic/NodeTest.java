package pl.put.poznan.transformer.logic;


        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import sun.security.ssl.Debug;

        import java.util.HashMap;
        import java.util.LinkedList;
        import java.util.Random;

        import static org.junit.jupiter.api.Assertions.*;

/** Klasa testująca klasę Node
 *
 */
class NodeTest {
    Node[] nodes;

    /** Przygotowuje 10 węzłów z losowo wygenerowanymi połączeniami o losowych kosztach
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        Random rand = new Random();
        nodes = new Node[10];

        for(int i=0; i < 10; ++i)
            nodes[i] = new Node(i, "Name", NodeType.ENTRY, new LinkedList<Arc>(), new LinkedList<Arc>());

        for(int i=0; i < 10; ++i) {
            Integer f = rand.nextInt(10);
            Integer s = (f + (rand.nextInt(10) % 9)) % 10;
            Double val = rand.nextDouble();
            Arc arc = new Arc(f, s, val);
            nodes[f].insertToOutgoing(arc);
            nodes[s].insertToIncoming(arc);
        }
    }

    /** Test metody findShortestPathGreedy
     * 1. Czy rzuca wyjątek, gdy podamy graf, w którym nie występuje węzeł początkowy i końcowy
     * 2. Czy znajduje ścieżkę w grafie jednowęzłowym
     * @throws Exception
     */
    @Test
    void findShortestPathGreedyTest() throws Exception {
        HashMap<Integer, Node> graph = new HashMap<Integer, Node>(),
                graph10 = new HashMap<Integer, Node>();
        Node n = new Node(0, "Name", NodeType.ENTRY, new LinkedList<Arc>(), new LinkedList<Arc>());

        Throwable exception = assertThrows(Exception.class, ()->{n.findShortestPathGreedy(graph, n);});

        graph.put(n.id, n);
        LinkedList<Integer> path = n.findShortestPathGreedy(graph, n);
        assertTrue(path.size() == 1 && path.getFirst() == n.id);


        for(int i=0; i < 10; ++i) {
            graph10.put(nodes[i].id, nodes[i]);
        }
    }


    /** Test metody computePathCost
     * 1. Czy zwraca poprawny koszt ścieżki składającej się z dwóch węzłów
     */
    @Test
    void computePathCostTest() throws Exception {
        HashMap<Integer, Node> graph = new HashMap<Integer, Node>();
        Double val = -1.;

        for (Arc arc : nodes[0].outgoing){
            if (arc.to == nodes[1].id) {
                val = arc.value;
                break;
            }
        }
        if(val == -1.) {
            Arc arc = new Arc(nodes[0].id, nodes[1].id, 13.);
            nodes[0].insertToOutgoing(arc);
            nodes[1].insertToIncoming(arc);
            val = 13.;
        }
        graph.put(0, nodes[0]);
        graph.put(1, nodes[1]);
        LinkedList<Integer> path = new LinkedList<Integer>();
        path.add(0, 0);
        path.add(1, 1);
        Double cost = nodes[0].computePathCost(graph, path);
        assertTrue(cost.compareTo(val) == 0.);
    }

    @Test
    void getOutgoingTest() {
        LinkedList<Arc> list = nodes[0].getOutgoing();
        assertTrue(list != null);
    }

    @Test
    void insertToOutgoingTest() throws Exception {
        Arc arc = new Arc(nodes[0].id, nodes[1].id,-1.);
        Throwable exception = assertThrows(Exception.class, ()->{nodes[0].insertToOutgoing(arc);});
    }

    @Test
    void getIncomingTest() {
        LinkedList<Arc> list = nodes[0].getIncoming();
        assertTrue(list != null);
    }

    /** Test metody insertToIncoming, wymaga poprawności metody getIncoming()
     * 1. Czy dodając połączenie wchodzące do węzła faktycznie możemy następnie takowe znaleźć
     * @throws Exception
     */
    @Test
    void insertToIncomingTest() throws Exception {
        Node n = new Node(11313133, "Name", NodeType.ENTRY, new LinkedList<Arc>(), new LinkedList<Arc>());
        Arc new_arc = new Arc(n.id, nodes[0].id,1.);
        Boolean test = false;

        nodes[0].insertToIncoming(new_arc);
        for(Arc arc : nodes[0].getIncoming())
        {
            if(arc.from == n.id && arc.to == nodes[0].id) {
                test = true;
                break;
            }
        };
        assertTrue(test);
    }
}
