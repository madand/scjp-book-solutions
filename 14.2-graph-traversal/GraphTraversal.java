import java.util.*;

public class GraphTraversal {
    public static void main(String[] args) {
        Integer[][] neighbors = {
                {1, 3}, // Vertex 0
                {2},    // Vertex 1
                {4},    // Vertex 2
                {1, 2}, // Vertex 3
                {}      // Vertex 4
        };
        Map<Integer, Collection<Integer>> graph = new HashMap<>();

        for (int i = 0; i < neighbors.length; i++) {
            graph.put(i, Arrays.asList(neighbors[i]));
        }

        int startVertex;
        try {
            startVertex = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException|NumberFormatException e) {
            printHelpAndExit();
            return;
        }

        Set<Integer> visitedSet = findVerticesOnPath(graph, startVertex);
        System.out.println("Vertex " + startVertex + " is connected to " + visitedSet);
    }

    /**
     * Finds the vertices on a path from a given vertex in a directed graph.
     * In the map, the key is a vertex, and the value is the collection containing its neighbors.
     *
     * @param graph the graph.
     * @param startVertex the vertex to start traversal from.
     * @param <N> the type of graph vertices.
     * @return the set of vertices reachable from the {@code startVertex}.
     */
    public static <N> Set<N> findVerticesOnPath(Map<N, Collection<N>> graph, N startVertex) {
        var result = new HashSet<N>();

        var nextUnvisitedVertices = new HashSet<N>();
        var unvisitedVertices = new HashSet<N>(graph.get(startVertex));
        while (!unvisitedVertices.isEmpty()) {
            result.addAll(unvisitedVertices);

            nextUnvisitedVertices.clear();
            for (N vertex : unvisitedVertices) {
                nextUnvisitedVertices.addAll(graph.get(vertex));
            }
            unvisitedVertices = nextUnvisitedVertices;
        }

        return result;
    }


    static void printHelpAndExit() {
        printHelp();
        System.exit(0);
    }

    /**
     * Print the help message to the stdout.
     */
    static void printHelp() {
        String help = "Usage: java " + GraphTraversal.class.getName() + " [0-4]";
        System.out.print(help);
    }
}
