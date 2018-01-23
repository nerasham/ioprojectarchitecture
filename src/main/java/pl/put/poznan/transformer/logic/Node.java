package pl.put.poznan.transformer.logic;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/** Klasa reprezentująca węzeł w sieci
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/** Klasa reprezentująca węzeł w sieci
 *
 */
public class Node {
    public Integer id;
    String name;
    NodeType type;
    LinkedList<Arc> outgoing;
    LinkedList<Arc> incoming;

    /** Tworzy węzeł na podstawie przekazanych parametrów
     * @param id        Unikalny identyfikator
     * @param name      Tekst opisujący węzeł
     * @param type      Rodzaj węzła w sieci: entry, exit, regular
     * @param outgoing  Lista połączeń wychodzących z tego węzła
     * @param incoming  Lista połączeń wchodzących z innych węzłów
     */
    public Node(Integer id, String name, NodeType type, LinkedList<Arc> outgoing, LinkedList<Arc> incoming) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.outgoing = outgoing;
        this.incoming = incoming;
    }

    /** Znajduje najtańszą ścieżkę w zadanym grafie od węzła, dla którego wykonano metodę, do węzła exit.
     * Metoda zakłada, że wszystkie węzły w listach outgoing i incoming węzłów należących do struktury graph znajdują się w tejże strukturze
     * @param graph     Węzły sieci, mapa z identyfikatora węzła na klasę reprezentującą dany węzeł
     * @param exit      Węzeł końcowy w poszukiwanej ścieżce
     * @return          Lista identyfikatorów węzłów na ścieżce oraz sumaryczny koszt
     */
    public LinkedList<Integer> findShortestPathGreedy(HashMap<Integer,Node> graph, Node exit) throws Exception {
        LinkedList<Integer> result = new LinkedList<Integer>();
        HashSet<Integer> queue = new HashSet<Integer>();
        HashMap<Integer, Node> predecessor = new HashMap<Integer, Node>();
        HashMap<Integer, Double> distance = new HashMap<Integer, Double>();

        if(!graph.containsKey(this.id) || !graph.containsKey(exit.id))
            throw new Exception("Podany graf nie zawiera zadanego węzła początkowego lub końcowego.\n");

        for(Node n : graph.values()){
            predecessor.put(n.id, null);
            distance.put(n.id, Double.POSITIVE_INFINITY);
            queue.add(n.id);
        }
        distance.put(this.id, 0.);

        while(!queue.isEmpty()){
            Integer temp_id = queue.iterator().next();
            Double temp_value = distance.get(temp_id);
            for(Integer node_id: queue){
                if(distance.get(node_id) < temp_value){
                    temp_id = node_id;
                    temp_value = distance.get(node_id);
                }
            }
            queue.remove(temp_id);
            Node temp_node = graph.get(temp_id);

            for(Arc arc: temp_node.outgoing) {
                Node successor = graph.get(arc.getTo());
                Double new_distance = distance.get(temp_id) + arc.getValue();
//                System.out.println(temp_id + " " + successor.id);
                if(queue.contains(successor.id) && (distance.get(successor.id) > new_distance)){
                    distance.put(successor.id, new_distance);
                    predecessor.put(successor.id, temp_node);
                }
            }
        }

        for(Node n = exit; n.id != this.id; n = predecessor.get(n.id)) {
            result.addFirst(n.id);
        }
        result.addFirst(this.id);
        return result;
    }

    /** Oblicza koszt ścieżki z danym grafie
     * @param graph     Graf sieci
     * @param path      Ścieżka w danej sieci
     * @return          Koszt ścieżki
     */
    double computePathCost(HashMap<Integer,Node> graph, LinkedList<Integer> path) throws Exception {
        double result = 0.;

        for(int i=0; i < (path.size()-1); ++i){
            Node pre = graph.get(path.get(i));
            Integer successor_id = path.get(i+1);
            Boolean found = false;
            for(Arc arc : pre.outgoing) {
                if (arc.getTo() == successor_id) {
                    result += arc.getValue();
                    found = true;
                    break;
                }
            }
            if(!found)
                throw new Exception("Graf niekompatybilny z daną ścieżką\n");
        }
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public LinkedList<Arc> getOutgoing() {
        return outgoing;
    }

    /** Dodaje nowe połączenie wychodzące z tego węzła
     * @param arc           Nowe połączenie
     * @throws Exception
     */
    public void insertToOutgoing(Arc arc) throws Exception {
        if(arc.getValue() < 0.)
            throw new Exception("Value < 0\n");
        if(arc.getFrom() != this.id)
            throw new Exception("Dane połączenie nie jest poprawne\n");
        outgoing.addFirst(arc);
    }

    public LinkedList<Arc> getIncoming() {
        return incoming;
    }

    /** Dodaje nowe połączenie wchodzące do tego węzła
     * @param arc           Nowe połączenie
     * @throws Exception
     */
    public void insertToIncoming(Arc arc) throws Exception {
        if(arc.getValue() < 0.)
            throw new Exception("Value < 0\n");
        if(arc.getTo() != this.id)
            throw new Exception("Dane połączenie nie jest poprawne\n");
        incoming.addFirst(arc);
    }
}