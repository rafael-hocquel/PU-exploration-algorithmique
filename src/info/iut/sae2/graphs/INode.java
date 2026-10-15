package info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author rbourqui
 */
public interface INode {

    /**
     * Returns the neighbors (successors and predecessors) of a given node in
     * the graph.
     *
     * @return the neighbors of the node
     */
    public ArrayList<INode> getNeighbors();
    
    
    public HashSet<INode> getNeighborsHashSet();
    /**
     * Returns the successors of a given node in the graph.
     *
     * @return the successors of the node
     */
    public ArrayList<INode> getSuccessors();
    
            /**
     * Gets the node graph.
     *
     * @return the node's graph
     */
    public IGraph getGraph();

    /**
     * Adds the given node as a predecessor to the node the method is applied on.
     * 
     * @param node the node to add as a predecessor
     */
    public void addPredecessor(INode node);

    /**
     * Adds the given node as a successor to the node the method is applied on.
     * 
     * @param node  the node to add as a successor
     */
    public void addSuccessor(INode node);

    /**
     * Returns the degree of the node the method is applied on.
     * 
     * @return the degree of the node
     */
    public int getDegree();

    /**
     * Gets the node color.
     *
     * @return node color
     */
    public Color getColor();

    /**
     * Gets the node coordinates.
     *
     * @return node coordinates
     */
    public Coord getCoord();

    /**
     * Sets the node coordinates.
     *
     * @param coord new node coordinates
     */
    public void setCoord(Coord coord);

    /**
     * Gets the node size.
     *
     * @return node size
     */
    public Size getSize();

    /**
     * Sets the node size.
     *
     * @param size the new node size
     */
    public void setSize(Size size);

    /**
     * Sets the given graph as a reference for the node.
     *
     * @param nodeGraph
     */
    public void setGraph(IGraph nodeGraph);

    /**
     *
     * Sets the node color.
     *
     * @param color the new node color
     */
    public void setColor(Color color);

    /**
     * Returns the predecessors of a given node in the graph.
     *
     * @return the predecessors of the node
     */
    public ArrayList<INode> getPredecessors();

    /**
     * Returns the incident edges of a given node.
     *
     * @return the incident edges of the node
     */
    public ArrayList<IEdge> getInOutEdges();

    /**
     * Returns the in-going edges of a given node in the graph.
     *
     * @return the in-going edges of the node
     */
    public ArrayList<IEdge> getInEdges();

    /**
     * Returns the out-going edges of a given node in the graph.
     *
     * @return the out-going edges of the node
     */
    public ArrayList<IEdge> getOutEdges();

    /**
     * Instanciates an INode. This method is used to create a new copy node of
     * the same specific type as the target object.
     *
     * @return the new copy
     */
    public INode copy();
}
