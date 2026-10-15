package info.iut.sae2.graphs;

import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class used to represent a Node and to perform operations on them.
 *
 * @author lhampegarrid
 */
public class Node implements INode {

    /**
     * The graph the Node is referenced to.
     */
    private IGraph graph;

    /**
     * The predecessors of the Node.
     */
    private ArrayList<INode> predecessors;

    /**
     * The successors of the Node.
     */
    private ArrayList<INode> successors;

    /**
     * The in-going edges of the Node.
     */
    private ArrayList<IEdge> inEdges;

    /**
     * The out-going edges of the Node.
     */
    private ArrayList<IEdge> outEdges;

    /**
     * Constructor for an empty Node
     */
    public Node() {
        this.predecessors = new ArrayList<>();
        this.successors = new ArrayList<>();
        this.inEdges = new ArrayList<>();
        this.outEdges = new ArrayList<>();
    }

    /**
     * Constructor for a Node with set predecessors, successors and oriented
     * edges
     *
     * @param predecessors predecessors of the node to create
     * @param successors successors of the node to create
     * @param inEdges in edges of the node to create
     * @param outEdges out edges of the node to create
     * @param graph the Graph the node is referenced to
     */
    public Node(ArrayList<INode> predecessors, ArrayList<INode> successors, ArrayList<IEdge> inEdges, ArrayList<IEdge> outEdges, Graph graph) {
        this.predecessors = predecessors;
        this.successors = successors;

        this.inEdges = inEdges;
        this.outEdges = outEdges;

        this.graph = graph;
    }

    /**
     * Sets the given graph as a reference for the node
     *
     * @param nodeGraph
     */
    @Override
    public void setGraph(IGraph nodeGraph) {
        this.graph = nodeGraph;
    }

    /**
     * Gets the node graph
     *
     * @return the node's graph
     */
    @Override
    public IGraph getGraph() {
        return this.graph;
    }

    /**
     * Returns the color of the Node.
     *
     * @return the color of the Node
     */
    @Override
    public Color getColor() {
        return graph.getColor().getNodeValue(this);
    }

    /**
     * Sets the given color to the Node.
     *
     * @param color the color to apply to the Node
     */
    @Override
    public void setColor(Color color) {
        graph.getColor().setNodeValue(this, color);
    }

    /**
     * Returns the coordinates of the Node.
     *
     * @return the coordinates of the Node
     */
    @Override
    public Coord getCoord() {
        return graph.getLayout().getNodeValue(this);
    }

    /**
     * Sets the coordinates of the Node.
     *
     * @param coord the coordinates to apply to the Node
     */
    @Override
    public void setCoord(Coord coord) {
        graph.getLayout().setNodeValue(this, coord);
    }

    /**
     * Adds the given Node as a predecessor to the node the method is applied
     * on.
     *
     * @param node the predecessor to apply to the node
     */
    @Override
    public void addPredecessor(INode node) {
        this.predecessors.add(node);
    }

    /**
     * Adds the given Node as a successor to the node the method is applied on.
     *
     * @param node the successor to apply to the node
     */
    @Override
    public void addSuccessor(INode node) {
        this.successors.add(node);
    }

    /**
     * Returns the Size of the Node the method is applied on.
     *
     * @return the size of the node
     */
    @Override
    public Size getSize() {
        return graph.getSizes().getNodeValue(this);
    }

    /**
     * Sets the given size to the Node the method is applied on.
     *
     * @param size the size to apply to the node
     */
    @Override
    public void setSize(Size size) {
        graph.getSizes().setNodeValue(this, size);
    }

    /**
     * Returns the neighbors (successors and predecessors) of a given node in
     * the graph.
     *
     * @return the neighbors of the node
     */
    public ArrayList<INode> getNeighbors() {
        ArrayList<INode> neighbors = new ArrayList<>(this.predecessors);
        neighbors.addAll(this.successors);
        return neighbors;
    }
    
    public HashSet<INode> getNeighborsHashSet() {
        HashSet<INode> neighbors = new HashSet<>(this.predecessors);
        neighbors.addAll(this.successors);
        return neighbors;
    }

    /**
     * Returns the degree of the node the method is applied on.
     * 
     * @return the degree of the node
     */
    @Override
    public int getDegree() {
        return this.getNeighbors().size();
    }

    /**
     * Returns the successors of the node the method is applied on.
     *
     * @return the successors of the node
     */
    @Override
    public ArrayList<INode> getSuccessors() {
        return this.successors;
    }

    /**
     * Returns the predecessors of the node the method is applied on.
     *
     * @return the predecessors of the node
     */
    @Override
    public ArrayList<INode> getPredecessors() {
        return this.predecessors;
    }

    /**
     * Returns the incident edges of a given node.
     *
     * @return the incident edges of the node
     */
    @Override
    public ArrayList<IEdge> getInOutEdges() {
        ArrayList<IEdge> inOutEdges = new ArrayList<>(this.inEdges);
        inOutEdges.addAll(this.outEdges);
        return inOutEdges;
    }

    /**
     * Returns the in-going edges of a given node in the graph.
     *
     * @return the in-going edges of the node
     */
    @Override
    public ArrayList<IEdge> getInEdges() {
        return this.inEdges;
    }

    /**
     * Returns the out-going edges of a given node in the graph.
     *
     * @return the out-going edges of the node
     */
    @Override
    public ArrayList<IEdge> getOutEdges() {
        return this.outEdges;
    }

    /**
     * Instanciates an INode. This method is used to create a new copy node of
     * the same specific type as the target object.
     *
     * @return the new copy
     */
    @Override
    public INode copy() {
        return new Node(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
    }
}
