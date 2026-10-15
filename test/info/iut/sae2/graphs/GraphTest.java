/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package info.iut.sae2.graphs;

import info.iut.sae2.properties.ColorProperty;
import info.iut.sae2.properties.LayoutProperty;
import info.iut.sae2.properties.SizeProperty;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author temet
 */
public class GraphTest {

    /**
     * Test of addNode method, of class Graph.
     */
    @Test
    public void testAddNode_0args() {
        // Empty Graph
        Graph g = new Graph();
        assertEquals(0, g.numberOfNodes());

        INode node = g.addNode();

        assertNotNull(node);
        assertEquals(1, g.numberOfNodes());
        assertEquals(g, node.getGraph());
        assertTrue(g.getNodes().contains(node));
    }

    /**
     * Test of addNode method, of class Graph.
     */
    @Test
    public void testAddNode_INode() {
        // Empty Graph qnd a node to add
        Graph g = new Graph();
        assertEquals(0, g.numberOfNodes());
        INode node = new Node();

        INode result = g.addNode(node);

        assertEquals(node, result);
        assertEquals(1, g.numberOfNodes());
        assertEquals(g, node.getGraph());
        assertTrue(g.getNodes().contains(node));
    }

    /**
     * Test of addEdge method, of class Graph.
     */
    @Test
    public void testAddEdge_IEdge() {
        // Empty Graph and an edge to add (from 2 nodes)
        Graph g = new Graph();
        assertEquals(0, g.numberOfNodes());
        INode node1 = new Node();
        INode node2 = new Node();
        IEdge edge = new Edge(node1, node2);

        IEdge result = g.addEdge(edge);

        assertEquals(edge, result);
        assertEquals(1, g.numberOfEdges());
        assertEquals(g, result.getGraph());
        assertTrue(g.getEdges().contains(result));
    }

    /**
     * Test of addEdge method, of class Graph.
     */
    @Test
    public void testAddEdge_INode_INode() {
        // Empty Graph and 2 Inodes
        Graph g = new Graph();
        assertEquals(0, g.numberOfNodes());
        INode node1 = new Node();
        INode node2 = new Node();

        IEdge result = g.addEdge(node1, node2);

        assertNotNull(result);
        assertEquals(node1, result.getSource());
        assertEquals(node2, result.getTarget());
        assertEquals(1, g.numberOfEdges());
        assertEquals(g, result.getGraph());
        assertTrue(g.getEdges().contains(result));
    }

    /**
     * Test of getNeighbors method, of class Graph.
     */
    @Test
    public void testGetNeighbors() {
        IGraph g = new Graph();

        // Create 3 nodes
        INode node = new Node();
        INode predecessor = new Node();
        INode successor = new Node();
        g.addNode(node);
        g.addNode(predecessor);
        g.addNode(successor);

        // No neighbors
        ArrayList<INode> result = g.getNeighbors(node);
        assertEquals(0, result.size());

        // 1 neighbor
        node.addPredecessor(predecessor);
        result = g.getNeighbors(node);
        assertEquals(1, result.size());

        // 2 neighbors
        node.addSuccessor(successor);
        result = g.getNeighbors(node);
        assertEquals(2, result.size());

        // More neighbors
        node.addSuccessor(successor);
        node.addSuccessor(successor);
        node.addSuccessor(successor);
        node.addSuccessor(predecessor);
        result = g.getNeighbors(node);
        assertEquals(6, result.size());
    }

    /**
     * Test of getNodes method, of class Graph.
     */
    @Test
    public void testGetNodes() {
        IGraph g = new Graph();

        ArrayList<INode> result = g.getNodes();
        assertEquals(0, result.size());

        // For each case, we also test that there are no additionnal nodes (size of the returned ArrayList)
        // 1 node
        INode node1 = new Node();
        g.addNode(node1);
        assertEquals(1, result.size());

        result = g.getNodes();
        assertTrue(result.contains(node1));

        // 2 Nodes
        INode node2 = new Node();
        g.addNode(node2);
        result = g.getNodes();
        assertTrue(result.contains(node1));
        assertTrue(result.contains(node2));
        assertEquals(2, result.size());

        // More Nodes
        INode node3 = new Node();
        INode node4 = new Node();
        INode node5 = new Node();
        g.addNode(node3);
        g.addNode(node4);
        g.addNode(node5);
        assertTrue(result.contains(node1));
        assertTrue(result.contains(node2));
        assertTrue(result.contains(node3));
        assertTrue(result.contains(node4));
        assertTrue(result.contains(node5));
        assertEquals(5, result.size());

    }

    /**
     * Test of numberOfNodes method, of class Graph.
     */
    @Test
    public void testNumberOfNodes() {
        IGraph g = new Graph();

        // No Nodes
        assertEquals(0, g.numberOfNodes());

        // 1 Node
        g.addNode();
        assertEquals(1, g.numberOfNodes());

        // 2 Nodes
        g.addNode();
        assertEquals(2, g.numberOfNodes());

        // More Nodes
        g.addNode();
        g.addNode();
        g.addNode();
        g.addNode();
        g.addNode();
        assertEquals(7, g.numberOfNodes());

    }

    /**
     * Test of existEdge method, of class Graph.
     */
    @Test
    public void testExistEdge() {
        IGraph g = new Graph();
        INode source = g.addNode();
        INode target = g.addNode();

        // No edge
        assertFalse(g.existEdge(source, target, true));
        assertFalse(g.existEdge(source, target, false));

        // Edge between the two nodes
        // Oriented
        g.addEdge(source, target);
        assertTrue(g.existEdge(source, target, true));
        assertFalse(g.existEdge(target, source, true));

        // Non Oriented
        assertTrue(g.existEdge(source, target, false));
        assertTrue(g.existEdge(target, source, false));
    }

    /**
     * Test of setNodeColor method, of class Graph.
     */
    @Test
    public void testSetNodeColor() {
        IGraph g = new Graph();
        INode node = new Node();
        node.setGraph(g);
        Color color = new Color(67, 49, 3, 255);

        g.setNodeColor(node, color);

        assertEquals(color, g.getNodeColor(node));

        color = new Color(12, 21, 93, 123);
        g.setNodeColor(node, color);
        g.setNodeColor(node, color);
    }

    /**
     * Test of setAllNodesColors method, of class Graph.
     */
    @Test
    public void testSetAllNodesColors() {
        IGraph g = new Graph();

        // Add some nodes with different colors
        g.addNode();
        g.addNode().setColor(new Color(255, 254, 253, 252));
        g.addNode().setColor(new Color(0, 1, 2, 3));
        g.addNode().setColor(new Color(100, 200, 50, 250));
        g.addNode().setColor(new Color(10, 110, 210, 201));

        Color colorToApply = new Color(255, 0, 0, 230);
        g.setAllNodesColors(colorToApply);

        for (INode node : g.getNodes()) {
            assertEquals(node.getColor(), colorToApply);
        }

    }
}
