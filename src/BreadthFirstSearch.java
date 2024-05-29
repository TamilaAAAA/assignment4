import java.util.*;

public class BreadthFirstSearch implements Search {
    private final WeightedGraph graph;

    public BreadthFirstSearch(WeightedGraph graph) {
        this.graph = graph;
    }

    @Override
    public boolean hasPath(String source, String destination) {
        return !getPath(source, destination).isEmpty();
    }

    @Override
    public List<Vertex> getPath(String source, String destination) {
        Vertex start = new Vertex(source);
        Vertex end = new Vertex(destination);
        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Vertex> previous = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current.equals(end)) {
                return reconstructPath(previous, start, end);
            }
            for (Edge edge : graph.getNeighbors(current)) {
                Vertex neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Vertex> reconstructPath(Map<Vertex, Vertex> previous, Vertex start, Vertex end) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path.size() > 1 && path.get(0).equals(start) ? path : Collections.emptyList();
    }
}