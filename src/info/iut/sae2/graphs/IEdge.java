package info.iut.sae2.graphs;

import java.util.ArrayList;

/**
 *
 * @author rbourqui
 */
public interface IEdge {

    /**
     * Returns the source of the edge.
     *
     * @return the source of the edge
     */
    public INode getSource();

    /**
     * Returns the target of the edge.
     *
     * @return the target of the edge
     */
    public INode getTarget();

    /**
     * Gets the reference Graph of the edge the method is applied on.
     *
     * @return the reference Graph of the edge
     */
    public IGraph getGraph();

    /**
     * Sets the given graph as a reference for the edge.
     *
     * @param edgeGraph the graph to reference the edge on
     */
    public void setGraph(IGraph edgeGraph);

    /**
     * Sets the source node of the edge
     * @param source the new source
     */
    void setSource(INode source);

    /**
     * Sets the target node of the edge
     * @param target the new target
     */
    void setTarget(INode target);

    /**
     * Returns the edge color.
     *
     * @return edge color
     */
    public Color getColor();

    /**
     * Sets a new color for the edge.
     *
     * @param color the new edge color
     */
    public void setColor(Color color);

    /**
     * Gets the edge coordinates.
     *
     * @return edge coordinates
     */
    public ArrayList<Coord> getCoord();

    /**
     * Sets the edge coordinates.
     *
     * @param coord new edge coordinates
     */
    public void setCoord(ArrayList<Coord> coord);

    /**
     * Gets the edge width.
     *
     * @return edge width
     */
    public double getWidth();

    /**
     * Sets the edge width.
     *
     * @param w new edge width
     */
    public void setWidth(Double w);

    /**
     * Instanciates an IEdge. This method is used to create a new copy edge of
     * the same specific type as the target object.
     *
     * @return the new copy
     */
    public IEdge copy();
}
