public class Main {
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();

        graph.addVertex("Almaty");
        graph.addVertex("Astana");
        graph.addVertex("Shymkent");
        graph.addVertex("Pavlodar");
        graph.addVertex("Oral");

        graph.addEdge("Almaty", "Astana", 5);
        graph.addEdge("Almaty", "Shymkent", 8);
        graph.addEdge("Astana", "Pavlodar", 3);
        graph.addEdge("Astana", "Oral", 6);

        graph.printGraph();

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        System.out.println("BFS path from Almaty to Pavlodar: " + bfs.getPath("Almaty", "Pavlodar"));

        DijkstraSearch dijkstra = new DijkstraSearch(graph);
        System.out.println("Dijkstra path from Almaty to Pavlodar: " + dijkstra.getPath("Almaty", "Pavlodar"));
    }
}