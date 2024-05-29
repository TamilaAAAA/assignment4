import java.util.List;

public interface Search {
    boolean hasPath(String source, String destination);
    List<Vertex> getPath(String source, String destination);
}