import java.util.*;

public class WeightedGraph {
    private final Map<Vertex, List<Edge>> adjList = new HashMap<>();

    public void addVertex(String label) {
        adjList.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public void addEdge(String label1, String label2, int weight) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjList.get(v1).add(new Edge(v1, v2, weight));
        adjList.get(v2).add(new Edge(v2, v1, weight)); // Assuming undirected graph
    }

    public List<Edge> getNeighbors(Vertex vertex) {
        return adjList.get(vertex);
    }

    public void printGraph() {
        for (Vertex vertex : adjList.keySet()) {
            System.out.print("Vertex " + vertex + " connected to: ");
            for (Edge edge : adjList.get(vertex)) {
                System.out.print(edge.getDestination() + "(" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        return adjList.get(v1).stream().anyMatch(edge -> edge.getDestination().equals(v2));
    }

    public void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjList.get(v1).removeIf(edge -> edge.getDestination().equals(v2));
        adjList.get(v2).removeIf(edge -> edge.getDestination().equals(v1));
    }
}

class Edge {
    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}