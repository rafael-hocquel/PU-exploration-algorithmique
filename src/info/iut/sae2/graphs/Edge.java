package info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class used to represent an Edge and perform operations on / from them.
 *
 * @author lhampegarrid
 */
public class Edge implements IEdge {

    /**
     * The graph the edge is related to (or null if not part of a Graph)
     */
    private IGraph graph;

    /**
     * The source node of the edge.
     */
    private INode source;

    /**
     * The target node of the edge.
     */
    private INode target;

    /**
     * The Color of the edge.
     */
    private Color color;

    /**
     * Constructor for an edge.
     *
     * @param source the source Node of the Edge
     * @param target the target Node of the Edge
     */
    public Edge(INode source, INode target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Instanciates an IEdge. This method is used to create a new shallow copy
     * edge of the same specific type as the target object, copies the Nodes and
     * Edges as well.
     *
     * @return the new shallow copy of the edge
     */
    @Override
    public IEdge copy() {
        return new Edge(null, null);
    };
    
        /**
     * Sets the given graph as a reference for the edge
     * @param edgeGraph 
     */
    @Override
    public IGraph getGraph() {
        return this.graph;
    }

    /**
     * Sets the given graph as a reference for the edge.
     *
     * @param edgeGraph the graph to reference the edge on
     */
    @Override
    public void setGraph(IGraph edgeGraph) {
        this.graph = edgeGraph;
    }
    
            /**
     * Sets the given graph as a reference for the edge
     * @param edgeGraph 
     */
    public void setSource(INode source) {
        this.source = source;
    }
    
                /**
     * Sets the given graph as a reference for the edge
     * @param edgeGraph 
     */
    public void setTarget(INode target) {
        this.target = target;
    }
    
        /**
     * Gets the edge color
     * @return edge color
     */
    @Override
    public Color getColor() {
        return graph.getColor().getEdgeValue(this);
    }

    /**
     * Sets the color of the edge to the given color.
     *
     * @param color the color to give to the edge
     */
    @Override
    public void setColor(Color color) {
        graph.getColor().setEdgeValue(this, color);
    }

    /**
     * Gets the edge coordinates.
     *
     * @return edge coordinates
     */
    @Override
    public ArrayList<Coord> getCoord() {
        return graph.getLayout().getEdgeValue(this);
    }

    /**
     * Sets the edge coordinates.
     *
     * @param coord new edge coordinates
     */
    @Override
    public void setCoord(ArrayList<Coord> coord) {
        graph.getLayout().setEdgeValue(this, coord);
    }

    /**
     * Gets the edge width.
     *
     * @return edge width
     */
    @Override
    public double getWidth() {
        return graph.getSizes().getEdgeValue(this);
    }

    /**
     * Sets the edge width.
     *
     * @param w new edge width
     */
    @Override
    public void setWidth(Double w) {
        graph.getSizes().setEdgeValue(this, w);
    }

    /**
     * Returns the source of the edge
     *
     * @return the source of the edge
     */
    @Override
    public INode getSource() {
        return this.source;
    }

    /**
     * Returns the target of the edge
     *
     * @return the target of the edge
     */
    @Override
    public INode getTarget() {
        return this.target;
    }

    /**
     * Returns the hashCode for the Coordinates of the edgethe method is applied
     * on.
     *
     * @return hashcode of the color
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.graph);
        hash = 17 * hash + Objects.hashCode(this.source);
        hash = 17 * hash + Objects.hashCode(this.target);
        hash = 17 * hash + Objects.hashCode(this.color);
        return hash;
    }

    /**
     * Returns true if the given Coordinate and the Coordinate the method is applied on
     * are equal in values.
     *
     * @param obj the coordinate to check equality with
     * @return true if the coordinates are equals in values or false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (!Objects.equals(this.graph, other.graph)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.target, other.target)) {
            return false;
        }
        return Objects.equals(this.color, other.color);
    }

}
