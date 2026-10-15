package info.iut.sae2.properties;

import info.iut.sae2.graphs.IEdge;
import info.iut.sae2.graphs.INode;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rbourqui
 */
public abstract class AbstractProperty<T, U> implements IProperty<T, U> {

    /**
     * Map use to store the mapping between nodes and values
     */
    Map<INode, T> nodesValues;

    /**
     * Map use to store the mapping between edges and values
     */
    Map<IEdge, U> edgesValues;

    /**
     * Default constructor ofg AbstractProperty
     */
    public AbstractProperty() {
        nodesValues = new HashMap<>();
        edgesValues = new HashMap<>();
    }

    /**
     * Returns the Node's Value.
     *
     * @param n the Node to get the value of
     * @return the value of the given Node
     */
    @Override
    public T getNodeValue(INode n) {
        return nodesValues.get(n);
    }

    /**
     * Returns the Edge's Value.
     *
     * @param e the Edge to get the value of
     * @return the value of the given Edge
     */
    @Override
    public U getEdgeValue(IEdge e) {
        return edgesValues.get(e);
    }

    /**
     * Sets the given Node value to the given value.
     *
     * @param n the node to change the value of
     * @param val the value to apply to the Node
     */
    @Override
    public abstract void setNodeValue(INode n, T val);

    /**
     * Sets the given Edge value to the given value.
     *
     * @param e the Edge to change the value of
     * @param val the value to apply to the Edge
     */
    @Override
    public abstract void setEdgeValue(IEdge e, U val);

    /**
     * Sets the given value to all of the Nodes of the property.
     *
     * @param val the value to apply to all Nodes
     */
    @Override
    public void setAllNodesValues(T val) {
        for (INode n : nodesValues.keySet()) {
            setNodeValue(n, val);
        }
    }

    /**
     * Sets the given value to all of the Edges of the property.
     *
     * @param val the value to apply to all Edges
     */
    @Override
    public void setAllEdgesValues(U val) {
        for (IEdge e : edgesValues.keySet()) {
            setEdgeValue(e, val);
        }
    }

    /**
     * Deletes the given Node from the property
     *
     * @param n the node to remove
     */
    @Override
    public void delNode(INode n) {
        assert nodesValues.containsKey(n);
        nodesValues.remove(n);
    }

    /**
     * Deletes the given Edge from the property
     *
     * @param e the node to remove
     */
    @Override
    public void delEdge(IEdge e) {
        assert edgesValues.containsKey(e);
        edgesValues.remove(e);
    }

}
