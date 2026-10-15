package info.iut.sae2.graphs;

import info.iut.sae2.properties.ColorProperty;
import info.iut.sae2.properties.SizeProperty;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class used to load nodes and edges from the dataset files.
 * @author rbourqui
 */
public class GraphLoader {

    /**
     * Load and return a IGraph from the two specified files.
     *
     * @param nodeFileName path to the file containing nodes informations
     * @param edgeFileName path to the file containing edges informations
     * @return the graph containing the nodes and edges of the specified files
     */
    public static IGraph loadFromFile(String nodeFileName, String edgeFileName) {
        if (nodeFileName == null) {
            return null;
        }

        IGraph g = new Graph();
        HashMap<Integer, INode> nodeMap = loadNodesFromFile(g, nodeFileName);

        if (edgeFileName != null) {
            loadEdgesFromFile(g, nodeMap, edgeFileName);
        }

        return g;
    }

    /**
     * Load and add the nodes described in the specified file.
     *
     * @param g the graph where the nodes are added
     * @param nodeFileName path to the file containing nodes informations
     * @return a map associating the node ids in the file and the graph nodes
     */
    private static HashMap<Integer, INode> loadNodesFromFile(IGraph g, String nodeFileName) {
        Path pathToFile = Paths.get(nodeFileName);
        HashMap<Integer, INode> nodeMap = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                if (attributes.length != 2) {
                    System.err.println("Error while loading nodes : " + attributes.length + " column(s)");
                    continue;
                }
                int id = Integer.parseInt(attributes[0]);
                String[] coords = attributes[1].split(" ");
                if (coords.length != 2) {
                    System.err.println("Error while loading nodes : coordinates have " + coords.length + " dimensions");
                    continue;
                }
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);

                INode n = g.addNode();
                g.setNodePosition(n, new Coord(x, y));
                g.setNodeSize(n, new Size(SizeProperty.DEFAULT_NODE_SIZE.getW(), SizeProperty.DEFAULT_NODE_SIZE.getH()));
                g.setNodeColor(n, ColorProperty.DEFAULT_NODE_COL);
                nodeMap.put(id, n);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return nodeMap;
    }

    /* Load and add the edges described in the specified file.
     *
     * @param g the graph where the edges are added
     * @param nodeMap the map associating nodes ids in the file and nodes in the graph
     * @param edgeFileName path to the file containing edges informations
     */
    private static void loadEdgesFromFile(IGraph g, HashMap<Integer, INode> nodeMap, String edgeFileName) {

        // Load File and prepare edge map
        Path pathToFile = Paths.get(edgeFileName);
        HashMap<Integer, IEdge> edgeMap = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();
            int id = 0;
            while (line != null) {
                String[] attributes = line.split(";");

                // If the file isn't formatted correctly
                if (attributes.length != 2) {
                    System.err.println("Error while loading edges : " + attributes.length + " column(s)");
                    continue;
                }

                int idNode1 = Integer.parseInt(attributes[0]);
                int idNode2 = Integer.parseInt(attributes[1]);

                INode node1 = nodeMap.get(idNode1);
                INode node2 = nodeMap.get(idNode2);

                IEdge e = g.addEdge(node1, node2);
                node1.addSuccessor(node2);
                node2.addPredecessor(node1);
                e.setGraph(g);
                e.setColor(ColorProperty.DEFAULT_EDGE_COL);
                // ArrayList de coordonnées
                g.setEdgePosition(e, new ArrayList<Coord>());
                edgeMap.put(id, e);
                line = br.readLine();
                id++;
            }

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
