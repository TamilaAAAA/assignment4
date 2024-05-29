import java.util.*;

public class DijkstraSearch implements Search {
    private final WeightedGraph graph;

    public DijkstraSearch(WeightedGraph graph) {
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
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();
        PriorityQueue<VertexDistance> queue = new PriorityQueue<>(Comparator.comparingInt(VertexDistance::getDistance));

        for (Vertex vertex : graph.getNeighbors(start).stream().map(Edge::getDestination).toList()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        queue.add(new VertexDistance(start, 0));

        while (!queue.isEmpty()) {
            VertexDistance vertexDistance = queue.poll();
            Vertex current = vertexDistance.getVertex();

            if (current.equals(end)) {
                return reconstructPath(previous, start, end);
            }

            for (Edge edge : graph.getNeighbors(current)) {
                Vertex neighbor = edge.getDestination();
                int newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(new VertexDistance(neighbor, newDist));
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

class VertexDistance {
    private final Vertex vertex;
    private final int distance;

    public VertexDistance(Vertex vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getDistance() {
        return distance;
    }
}