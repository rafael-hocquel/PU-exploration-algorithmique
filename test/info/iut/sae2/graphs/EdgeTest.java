/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package info.iut.sae2.graphs;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rhocquel
 */
public class EdgeTest {

    /**
     * Test of constructors of the class Edge.
     */
    @Test
    public void testConstructors() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);

        // Check that the edge was created
        assertNotNull(edge);

        // Check that source and target nodes are the ones we created before
        assertEquals(sourceNode, edge.getSource());
        assertEquals(targetNode, edge.getTarget());
    }

    /**
     * Test of copy method, of class Edge.
     */
    @Test
    public void testCopy() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        IEdge edge = new Edge(sourceNode, targetNode);
        Graph graph = new Graph();
        edge.setGraph(graph);
        edge.setColor(Color.intToColor(0));
        edge.setWidth(49.3);
        IEdge copyEdge = edge.copy();

        // Check that they do not have the same references
        assertFalse(edge == copyEdge);

        // Check that the source and target Nodes are still the original-edge ones
        assertTrue(sourceNode == copyEdge.getSource());
        assertTrue(targetNode == copyEdge.getTarget());

        // Check that properties are conserved
        assertTrue(edge.getGraph() == copyEdge.getGraph());
        assertTrue(edge.getColor() == copyEdge.getColor());
        assertTrue(edge.getCoord() == copyEdge.getCoord());
        assertTrue(edge.getWidth() == copyEdge.getWidth());
    }

    /**
     * Test of setGraph method, of class Edge.
     */
    @Test
    public void testSetGraph() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);

        // No Graph set -> getGraph() should return null
        assertNull(edge.getGraph());

        // graph = null -> graph1
        Graph graph1 = new Graph();
        edge.setGraph(graph1);
        assertEquals(graph1, edge.getGraph());

        // graph = graph1 -> graph2
        Graph graph2 = new Graph();
        edge.setGraph(graph2);
        assertEquals(graph2, edge.getGraph());
    }

    /**
     * Test of getColor method, of class Edge.
     */
    @Test
    public void testGetColor() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);
        Graph graph = new Graph();

        edge.setGraph(graph);

        Color color1 = new Color(255, 0, 0, 255);
        edge.setColor(color1);
        assertEquals(color1, edge.getColor());

        Color color2 = new Color(0, 255, 255, 0);
        edge.setColor(color2);
        assertEquals(color2, edge.getColor());
    }

    /**
     * Test of setColor method, of class Edge.
     */
    @Test
    public void testSetColor() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);
        Graph graph = new Graph();

        edge.setGraph(graph);

        Color color1 = new Color(255, 255, 255, 255);
        edge.setColor(color1);
        assertEquals(color1, edge.getColor());

        Color color2 = new Color(0, 0, 0, 255);
        edge.setColor(color2);
        assertEquals(color2, edge.getColor());

    }

    /**
     * Test of getCoord method, of class Edge.
     */
    @Test
    public void testGetCoord() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);
        Graph graph = new Graph();

        edge.setGraph(graph);

        // Not referenced to a graph -> Should be null
        assertNull(edge.getCoord());

        // Assigned to a Graph -> Should get the bends coordinate ArrayList
        Graph graph1 = new Graph();
        edge.setGraph(graph1);
        ArrayList<Coord> bends = new ArrayList<>();
        bends.add(new Coord(43.6, 49.1));
        bends.add(new Coord(67.67, 49.3));

        graph1.setEdgePosition(edge, bends);
        assertEquals(bends.size(), edge.getCoord().size());
        for (int i = 0; i < bends.size(); i++) {
            assertEquals(bends.get(i).toString(), edge.getCoord().get(i).toString());
        }
    }


    /**
     * Test of getWidth method, of class Edge.
     */
    @Test
    public void testGetWidth() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);
        Graph graph = new Graph();

        edge.setGraph(graph);

        // Assigned to a Graph -> Should get the correct width
        Graph graph1 = new Graph();
        edge.setGraph(graph1);
        edge.setWidth(49.3);
        assertEquals(49.3, edge.getWidth(), 0.01);

        edge.setWidth(11.);
        assertEquals(11., edge.getWidth(), 0.01);
    }

    /**
     * Test of setWidth method, of class Edge.
     */
    @Test
    public void testSetWidth() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);

        Graph graph1 = new Graph();
        edge.setGraph(graph1);

        edge.setWidth(73.5);

        assertEquals(73.5, edge.getWidth(), 0.01);

        edge.setWidth(null);
        assertNull(edge.getCoord());
    }

    /**
     * Test of getSource method, of class Edge.
     */
    @Test
    public void testGetSource() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);

        assertEquals(sourceNode, edge.getSource());
    }

    /**
     * Test of getTarget method, of class Edge.
     */
    @Test
    public void testGetTarget() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);

        assertEquals(targetNode, edge.getTarget());

    }

    /**
     * Test of getGraph method, of class Edge.
     */
    @Test
    public void testGetGraph() {
        Node sourceNode = new Node();
        Node targetNode = new Node();
        Edge edge = new Edge(sourceNode, targetNode);

        // No Graph set -> Should return null
        assertNull(edge.getGraph());

        // With a graph set -> Should return the graph
        Graph graph = new Graph();
        edge.setGraph(graph);

        assertEquals(graph, edge.getGraph());

        Graph graph2 = new Graph();
        edge.setGraph(graph2);

        assertEquals(graph2, edge.getGraph());
    }
}
