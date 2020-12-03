import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Graph<E> {

    private final BadMap<E, List<E>> edges = new BadMap<>();

    public void addVertex(E vertex) {
        edges.put(vertex, new LinkedList<>());
    }

    public void addEdge(E from, E to) {
        if (!edges.containsKey(from)) addVertex(from);
        if (!edges.containsKey(to)) addVertex(to);
        edges.get(from).add(to);
    }

    public void addEdgeBidirectional(E from, E to){
        if (!edges.containsKey(from)) addVertex(from);
        if (!edges.containsKey(to)) addVertex(to);
        edges.get(from).add(to);
        edges.get(to).add(from);

    }

    public Set<E> vertices() { return edges.keys(); }

    public Set<E> edges(E vertex) {
        return new HashSet<>(this.edges.get(vertex));
    }

    public int numEdges(boolean bidirection) {
        int edges = 0;
        for (E v : this.edges.keys()) {
            edges += this.edges.get(v).size();
        }
        if (bidirection) {
            edges = edges / 2;
        }
        return edges;

    }

    public boolean hasVertex(E vertex) { return edges.containsKey(vertex); }

    public boolean hasEdge(E from, E to) {
        return (edges.get(from).contains(to));
    }

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();

        //Zero is not connected to anything
        g.addVertex(0);

        //4 Will be connected to stuff
        g.addVertex(4);

        //One direction adds 1 edge
        g.addEdge(4,5);
        g.addEdge(4,7);

        //Bidirectional directly between 2 (adds 2 edges)
        g.addEdgeBidirectional(5,49);

        //Bidirectional indirectly (adds 2 edges each time)
        g.addEdgeBidirectional(4,8);
        g.addEdgeBidirectional(8,6);
        g.addEdgeBidirectional(6,4);
        //Check presence of edges and all
        System.out.println(g.hasEdge(4,6));
        System.out.println(g.hasVertex(4));
        System.out.println(g.hasVertex(77));
        //Check edge count
        System.out.println(g.numEdges(false));
        System.out.println("Vertices:");
        g.vertices().forEach(System.out::println);
        System.out.println("Edges:");
        for(Integer i : g.vertices()){
            System.out.println("Edges of " + i + " are " + g.edges(i));
        }
    }
} 