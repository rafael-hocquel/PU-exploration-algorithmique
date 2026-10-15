package info.iut.sae2.graphs;

import info.iut.sae2.properties.ColorProperty;
import info.iut.sae2.properties.LayoutProperty;
import info.iut.sae2.properties.SizeProperty;
import info.iut.sae2.algorithm.WelshAndPowell;
import info.iut.sae2.algorithm.SixColorationPlanarAlgorithm;
import info.iut.sae2.algorithm.FiveColorationPlanarAlgorithm;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class used to represent a Graph and perform operations on it (or on it's
 * Nodes / Edges)
 *
 * @author rhocquel
 */
public class Graph implements IGraph {

    /**
     * The ColorProperty of the Graph.
     */
    private ColorProperty colorProperty;

    /**
     * The SizeProperty of the Graph.
     */
    private LayoutProperty layoutProperty;

    /**
     * The SizeProperty of the Graph.
     */
    private SizeProperty sizeProperty;

    /**
     * The boundingBox of the Graph.
     */
    private ArrayList<Coord> boundingBox = new ArrayList<>();

    /**
     * The list of all Edges of the Graph.
     */
    private ArrayList<INode> nodes = new ArrayList<>();

    /**
     * The list of all Nodes of the Graph.
     */
    private ArrayList<IEdge> edges = new ArrayList<>();

    /**
     * Constructor for an empty Graph
     */
    public Graph() {
        this.colorProperty = new ColorProperty();
        this.layoutProperty = new LayoutProperty();
        this.sizeProperty = new SizeProperty();
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * Sets a new ColorProperty for the Graph.
     * @param newColorProperty the ColorProperty to apply to the Graph
     */
    @Override
    public void setColorProperty(ColorProperty newColorProperty) {
        this.colorProperty = newColorProperty;
    }

    /**
     * Instanciates an Graph. This method is used to create a new empty graph
     * of the same specific type as the target object
     *
     * @return a new Graph
     */
    @Override
    public IGraph createGraph() {
        return new Graph();
    }

    /**
     * Instanciates an IGraph.
     * This method is used to create a new copy graph of the same
     * specific type as the target object, this copies the Nodes and Edges as well.
     *
     * @return the copy of the given Graph
     */
    @Override
    public IGraph copy() {
        IGraph newGraph = new Graph();
        
        HashMap<INode, INode> correspondingNodes = new HashMap<>();
        
        // Nodes
        for (INode oldNode : this.nodes) {
            INode newNode = oldNode.copy();
            
            newGraph.addNode(newNode);
            
            correspondingNodes.put(oldNode, newNode);
        }
        
        // Edges
        for (IEdge oldEdge : this.edges) {
            INode newSourceNode = correspondingNodes.get(oldEdge.getSource());
            
            INode newTargetNode = correspondingNodes.get(oldEdge.getTarget());
            
            IEdge newEdge = new Edge(newSourceNode, newTargetNode);
            
            newGraph.addEdge(newEdge);
            
            newSourceNode.addSuccessor(newTargetNode);
            
            newTargetNode.addPredecessor(newSourceNode);
        }
        
        return  newGraph;
    }

    /**
     * Adds a new empty node to the graph and returns it.
     *
     * @return the added node
     */
    @Override
    public INode addNode() {
        INode newNode = new Node();
        newNode.setGraph(this);
        nodes.add(newNode);
        return newNode;
    }

    /**
     * Adds a given node to the graph and returns it.
     *
     * @param n the node to add in the graph
     * @return the added node
     */
    @Override
    public INode addNode(INode n) {
        n.setGraph(this);
        nodes.add(n);
        return n;
    }

    /**
     * Adds a given edge to the graph and returns it.
     *
     * @param e the edge to add
     * @return the added edge
     */
    @Override
    public IEdge addEdge(IEdge e) {
        e.setGraph(this);
        edges.add(e);
        return e;
    }

    /**
     * Adds an edge to the Graph between the two given nodes and returns it.
     *
     * @param src source Node of the edge to add
     * @param tgt target Node of the edge to add
     * @return the added edge
     */
    @Override
    public IEdge addEdge(INode src, INode tgt) {
        IEdge newEdge = new Edge(src, tgt);
        newEdge.setGraph(this);
        edges.add(newEdge);
        //src.addSuccessor(tgt);
        //tgt.addPredecessor(src);
        return newEdge;
    }

    /**
     * Deletes a given node from the graph.
     *
     * @param n the node to be deleted
     */
    @Override
    public void delNode(INode n) {
        for(IEdge e : n.getInEdges()){
            e.getSource().getNeighbors().remove(n);
            edges.remove(e);
        }
        for(IEdge e: n.getOutEdges()){
            e.getTarget().getNeighbors().remove(n);
            edges.remove(e);
        }
        nodes.remove(n);
    }

    /**
     * Deletes a given edge from the graph.
     *
     * @param e the edge to be deleted
     */
    @Override
    public void delEdge(IEdge e) {
        edges.remove(e);
    }

    /**
     * Returns the number of nodes in the graph.
     *
     * @return the number of nodes
     */
    @Override
    public int numberOfNodes() {
        return nodes.size();
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges
     */
    @Override
    public int numberOfEdges() {
        return edges.size();
    }

    /**
     * Returns the neighbors (successors and predecessors) of a given node in the graph.
     *
     * @param n the Node we want to get the neighbors
     */
    @Override
    public ArrayList<INode> getNeighbors(INode n) {
        return n.getNeighbors();
    }

    /**
     * Returns the successors of a given node in the graph.
     *
     * @param n the node we want to get the successors
     * @return the successors of the node
     */
    @Override
    public ArrayList<INode> getSuccessors(INode n) {
        return n.getSuccessors();
    }

    /**
     * Returns the predecessors of a given node in the graph.
     *
     * @param n the node we want to get the predecessors
     * @return the predecessors of the node
     */
    @Override
    public ArrayList<INode> getPredecessors(INode n) {
        return n.getPredecessors();
    }

    /**
     * Returns the indicent edges of a given node.
     *
     * @param n the node we want to get incident edges
     * @return the incident edges of the node
     */
    @Override
    public ArrayList<IEdge> getInOutEdges(INode n) {
        return n.getInOutEdges();
    }

    /**
     * Returns the in-going edges of a given node in the graph.
     *
     * @param n the node whose in-going are queried
     * @return the in-going edges of the node
     */
    @Override
    public ArrayList<IEdge> getInEdges(INode n) {
        return n.getInEdges();
    }

    /**
     * Returns the out-going edges of a given node in the graph.
     *
     * @param n the node whose out-going are queried
     * @return the out-going edges of the node
     */
    @Override
    public ArrayList<IEdge> getOutEdges(INode n) {
        return n.getOutEdges();
    }

    /**
     * Returns the nodes of the graph.
     *
     * @return the nodes of the graph
     */
    @Override
    public ArrayList<INode> getNodes() {
        return this.nodes;
    }

    /**
     * Returns the edges of the graph.
     *
     * @return the edges of the graph
     */
    @Override
    public ArrayList<IEdge> getEdges() {
        return this.edges;
    }

    /**
     * Returns the source node of a given edge in the graph.
     *
     * @param e the edge whose source is queried
     * @return the source of the edge
     */
    @Override
    public INode source(IEdge e) {
        return e.getSource();
    }

    /**
     * Returns the target node of a given edge in the graph.
     *
     * @param e the edge whose target is queried
     * @return the target of the edge
     */
    @Override
    public INode target(IEdge e) {
        return e.getTarget();
    }

    /**
     * Returns the in-degree (number of predecessors) of a given node in the
     * graph.
     *
     * @param n the node whose in-degree is queried
     * @return the in-degree of the node
     */
    @Override
    public int inDegree(INode n) {
        return n.getInEdges().size();
    }

    /**
     * Returns the out-degree (number of successors) of a given node in the
     * graph.
     *
     * @param n the node whose out-degree is queried
     * @return the out-degree of the node
     */
    @Override
    public int outDegree(INode n) {
        return n.getOutEdges().size();
    }

    /**
     * Returns the degree (number of neighbors) of a given node in the graph.
     *
     * @param n the node whose degree is queried
     * @return the degree of the node
     */
    @Override
    public int degree(INode n) {
        return this.inDegree(n) + this.outDegree(n);
    }

    /**
     * Returns true if there exists an edge between the two given nodes,
     * considering the orientation of the edges or not.
     *
     * @param src the source node of the putative edge
     * @param tgt the target node of the putative edge
     * @param oriented if true, the function consider the edge as directed
     * (arc), and undirected otherwise
     * @return true if the edge exists, false otherwise
     */
    @Override
    public boolean existEdge(INode src, INode tgt, boolean oriented) {
        boolean edgeExist = false;
        int i = 0;
        while (!edgeExist && i < edges.size()) {
            if (oriented) {
                edgeExist = src == edges.get(i).getSource() && tgt == edges.get(i).getTarget();
            } else {
                edgeExist = (src == edges.get(i).getSource() && tgt == edges.get(i).getTarget())
                        || (tgt == edges.get(i).getSource() && src == edges.get(i).getTarget());
            }
            i++;
        }
        return edgeExist;
    }

    /**
     * Returns an existing edge between the source and target nodes while
     * considering the orientation of not.
     *
     * @param src the source node of the putative edge
     * @param tgt the target node of the putative edge
     * @param oriented if true, the function consider the edge as directed
     * (arc), and undirected otherwise
     * @return the edge if the edge exists, null otherwise
     */
    @Override
    public IEdge getEdge(INode src, INode tgt, boolean oriented) {
        boolean found = false;
        IEdge existingEdge = null;
        int i = 0;
        while (!found && i < edges.size()) {
            IEdge currentEdge = edges.get(i);

            if (src == currentEdge.getSource() && tgt == currentEdge.getTarget()) {
                existingEdge = currentEdge;
                found = true;
            } else if (!oriented && (src == currentEdge.getTarget() && tgt == currentEdge.getSource())) {
                existingEdge = currentEdge;
                found = true;
            }
            i++;

        }
        return existingEdge;
    }

    /**
     * Returns the SizeProperty associated to the IGraph.
     *
     * @return The SizeProperty of the IGraph
     */
    @Override
    public SizeProperty getSizes() {
        return sizeProperty;
    }

    /**
     * Returns the size of a given node in the graph.
     *
     * @param n the node of interest
     * @return the size of the node
     */
    @Override
    public Size getNodeSize(INode n) {
        return n.getSize();
    }

    /**
     * Returns the width of a given edge in the graph.
     *
     * @param e the edge of interest
     * @return the width of the edge
     */
    @Override
    public Double getEdgeWidth(IEdge e) {
        return e.getWidth();
    }

    /**
     * Set the size of a given node in the graph.
     *
     * @param n the node of interest
     * @param s new size of the node
     */
    @Override
    public void setNodeSize(INode n, Size s) {
        n.setSize(s);
    }

    /**
     * Set the width of a given edge in the graph.
     *
     * @param e the edge of interest
     * @param width new width of the edge
     */
    @Override
    public void setEdgeWidth(IEdge e, Double width) {
        e.setWidth(width);
    }

    /**
     * Set the size of all nodes in the graph.
     *
     * @param s new size of the nodes
     */
    @Override
    public void setAllNodesSizes(Size s) {
        for (INode node : nodes) {
            setNodeSize(node, s);
        }
    }

    /**
     * Set the width of all edges in the graph.
     *
     * @param width new width of the edges
     */
    @Override
    public void setAllEdgesWidths(Double width) {
        for (IEdge edge : edges) {
            setEdgeWidth(edge, width);
        }
    }

    /**
     * Returns the LayoutProperty associated to the IGraph.
     *
     * @return The LayoutProperty of the IGraph
     */
    @Override
    public LayoutProperty getLayout() {
        return layoutProperty;
    }

    /**
     * Returns the coordinates of a given node in the graph.
     *
     * @param n the node of interest
     * @return the coordinates of the node
     */
    @Override
    public Coord getNodePosition(INode n) {
        return n.getCoord();
    }

    /**
     * Returns the coordinates of a given edge (ie the coordinates of its bends)
     * in the graph.
     *
     * @param e the edge of interest
     * @return the coordinates of the bends of the edge
     */
    @Override
    public ArrayList<Coord> getEdgePosition(IEdge e) {
        return e.getCoord();
    }

    /**
     * Set the coordinates of a given node in the graph.
     *
     * @param n the node of interest
     * @param c new coordinates of the node
     */
    @Override
    public void setNodePosition(INode n, Coord c) {
        n.setCoord(c);
    }

    /**
     * Set the coordinates of a given edge (ie the coordinates of its bends) in
     * the graph.
     *
     * @param e the edge of interest
     * @param bends the bends of the edge
     */
    @Override
    public void setEdgePosition(IEdge e, ArrayList<Coord> bends) {
        e.setCoord(bends);
    }

    /**
     * Set the coordinates of all nodes in the graph
     *
     * @param c new coordinates of the nodes
     */
    @Override
    public void setAllNodesPositions(Coord c) {
        for (INode node : nodes) {
            setNodePosition(node, c);
        }
    }

    /**
     * Set the coordinates of all edges (ie the coordinates of its bends) in the
     * graph
     *
     * @param bends the bends of the edges
     */
    @Override
    public void setAllEdgesPositions(ArrayList<Coord> bends) {
        for (IEdge edge : edges) {
            setEdgePosition(edge, bends);
        }
    }

    /**
     * Returns the coordinates of the bouding box of the graph drawing. The
     * bouding box of a drawing of a graph is the minimum rectangle such that
     * all nodes and edges of the graph are inside the rectangle. Please remind
     * that the y-axis is oriented toward the bottom.
     *
     * @return an ArrayList containing the coordinates of the top-left corner of
     * the bonding box (at the first index in the ArrayList) and the coordinates
     * of the bottom-right corner of the bounding box (second index)
     *
     */
    @Override
    public ArrayList<Coord> getBoundingBox() {

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (INode node : nodes) {
            Coord nodeCoords = node.getCoord();
            minX = Math.min(nodeCoords.getX() - node.getSize().getW(), minX);
            minY = Math.min(nodeCoords.getY() - node.getSize().getH(), minY);
            maxX = Math.max(nodeCoords.getX() + node.getSize().getW(), maxX);
            maxY = Math.max(nodeCoords.getY() + node.getSize().getH(), maxY);
        }

        Coord topLeftCorner = new Coord(minX, minY);
        Coord bottomRightCorner = new Coord(maxX, maxY);
        boundingBox.add(topLeftCorner);
        boundingBox.add(bottomRightCorner);
        return boundingBox;
    }

    /**
     * Returns the ColorProperty associated to the IGraph.
     *
     * @return The ColorProperty of the IGraph
     */
    @Override
    public ColorProperty getColor() {
        return colorProperty;
    }

    /**
     * Returns the color of a given node in the graph.
     *
     * @param n the node of interest
     * @return the color of the node
     */
    @Override
    public Color getNodeColor(INode n) {
        return n.getColor();
    }

    /**
     * Returns the color of a given edge in the graph.
     *
     * @param e the edge of interest
     * @return the color of the edge
     */
    @Override
    public Color getEdgeColor(IEdge e) {
        return e.getColor();
    }

    /**
     * Set the color of a given node in the graph.
     *
     * @param n the node of interest
     * @param c new color of the node
     */
    @Override
    public void setNodeColor(INode n, Color c) {
        n.setColor(c);

    }

    /**
     * Set the color of a given edge in the graph.
     *
     * @param e the edge of interest
     * @param c the new color for the edge
     */
    @Override
    public void setEdgeColor(IEdge e, Color c) {
        e.setColor(c);
    }

    /**
     * Set the color of all nodes in the graph.
     *
     * @param c the new color of the nodes
     */
    @Override
    public void setAllNodesColors(Color c) {
        for (INode node : nodes) {
            setNodeColor(node, c);
        }
    }

    /**
     * Set the color of all edges in the graph.
     *
     * @param c the new color of the edges
     */
    @Override
    public void setAllEdgesColors(Color c) {
        for (IEdge edge : edges) {
            setEdgeColor(edge, c);
        }
    }

    /**
     * Searches for a node in the graph
     *
     * @param n the searched node
     * @return true if the node is in the graph, false else
     */
    @Override
    public boolean containsNode(INode n) {
        boolean found = false;
        int i = 0;
        while (!found && i < nodes.size()) {
            found = n == nodes.get(i);
            i++;
        }
        return found;
    }

    /**
     * Returns the ColorPropertyof the graph the method is applied on.
     * @return the ColorProperty of the Graph
     */
    @Override
    public ColorProperty getColorProperty() {
        return this.colorProperty;
    }

    /**
     * Color the nodes with Well & Powell algorithm.
     */
    @Override
    public void welshAndPowell() {
        WelshAndPowell welshAndPowell = new WelshAndPowell();
        ColorProperty newColorProperty = welshAndPowell.apply(this, null);
        for (INode node : this.getNodes()) {
            this.colorProperty.setNodeValue(node, newColorProperty.getNodeValue(node));
        }
    }

    /**
     * Color the nodes with a 6 colors algorithm (only for planar graphs).
     */
    @Override
    public void sixColors() {
        SixColorationPlanarAlgorithm sixColors = new SixColorationPlanarAlgorithm();
        ColorProperty newColorProperty = sixColors.apply(this, null);
        for (INode node : this.getNodes()) {
            this.colorProperty.setNodeValue(node, newColorProperty.getNodeValue(node));
        }
    }

    /**
     * Color the nodes with a 5 colors algorithm (only for planar graphs).
     */
    @Override
    public void fiveColors() {
        FiveColorationPlanarAlgorithm fiveColors = new FiveColorationPlanarAlgorithm();
        ColorProperty newColorProperty = fiveColors.apply(this, null);
        for (INode node : this.getNodes()) {
            this.colorProperty.setNodeValue(node, newColorProperty.getNodeValue(node));
        }
    }

    /**
     * Returns the first valid node for 5 and 6 coloration
     *
     * @return a node with a degree equal or below than 5
     */
    @Override
    public INode getFirstValidNode() {
        int i = 0;
        while (i < nodes.size() -1 && nodes.get(i).getDegree() > 5) {
            i++;
        }
        return nodes.get(i);
    }
}
