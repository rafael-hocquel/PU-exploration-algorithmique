/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package info.iut.sae2.algorithm;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.graphs.GraphLoader;
import info.iut.sae2.algorithm.SixColorationPlanarAlgorithm;
import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.IEdge;
import info.iut.sae2.graphs.Node;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author lhampegarrid
 */
public class AlgorithmTests {

    @Test
    public void AlgorithmsTest() {
        String graphFiles[] = {"dataset/testCJ_nodes.csv", "dataset/testEB_nodes.csv", "dataset/testEBB_nodes.csv", "dataset/planar_grid_10_nodes.csv", "dataset/planar_grid_100_nodes.csv",};
        String edgeFiles[] = {"dataset/testCJ_edges.csv", "dataset/testEB_edges.csv", "dataset/testEBB_edges.csv", "dataset/planar_grid_10_edges.csv", "dataset/planar_grid_100_edges.csv"};
        WelshAndPowell welshAndPowell = new WelshAndPowell();
        SixColorationPlanarAlgorithm sixColors = new SixColorationPlanarAlgorithm();
        FiveColorationPlanarAlgorithm fiveColors = new FiveColorationPlanarAlgorithm();

        for (int i = 0; i < graphFiles.length; i++) {
            IGraph g = GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]);
            g.setColorProperty(welshAndPowell.apply(g, new HashMap<String, Object>()));
            validColoration(g);
            assertEquals(GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]).getNodes().size(), g.getNodes().size());
            assertEquals(GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]).getEdges().size(), g.getEdges().size());

            g = GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]);
            g.setColorProperty(sixColors.apply(g, new HashMap<String, Object>()));
            assertTrue(validColoration(g) <= 6);
            assertEquals(GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]).getNodes().size(), g.getNodes().size());
            assertEquals(GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]).getEdges().size(), g.getEdges().size());

            g = GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]);
            g.setColorProperty(fiveColors.apply(g, new HashMap<String, Object>()));
            assertTrue(validColoration(g) <= 5);
            assertEquals(GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]).getNodes().size(), g.getNodes().size());
            assertEquals(GraphLoader.loadFromFile(graphFiles[i], edgeFiles[i]).getEdges().size(), g.getEdges().size());
        }

    }

    /**
     * Checks if a graph has a valid coloration and returns how many colors wher
     * used
     *
     * @param g the graph to check
     * @return the amount of colors used
     */
    public static int validColoration(IGraph g) {
        HashSet<Color> colors = new HashSet<>();
        for (IEdge edge : g.getEdges()) {
            assertNotNull(edge.getSource());
            assertNotNull(edge.getTarget());
            assertNotNull(edge.getSource().getColor());
            assertNotNull(edge.getTarget().getColor());
            assertNotEquals(edge.getSource().getColor(), edge.getTarget().getColor());
            colors.add(edge.getSource().getColor());
            colors.add(edge.getTarget().getColor());
        }
        return colors.size();
    }

}
