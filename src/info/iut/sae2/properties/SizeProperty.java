package info.iut.sae2.properties;

import info.iut.sae2.graphs.IEdge;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.graphs.Size;

import java.util.Map;

/**
 *
 * @author rbourqui
 */
public class SizeProperty extends AbstractProperty<Size, Double> {

    /**
     * Default node size
     */
    final public static Size DEFAULT_NODE_SIZE = new Size(2, 2);

    /**
     * Default edge size
     */
    final public static Double DEFAULT_EDGE_WIDTH = 1.;

    /**
     * Default constructor of SizeProperty
     */
    public SizeProperty() {
    }

    /**
     * Copy constructor. Each value of the given SizeProperty are copied before
     * being associated to the nodes and edges.
     *
     * @param size the SizeProperty to be copied
     */
    public SizeProperty(SizeProperty size) {
        for (Map.Entry e : size.nodesValues.entrySet()) {
            nodesValues.put((INode) e.getKey(), new Size((Size) e.getValue()));
        }

        for (Map.Entry e : size.edgesValues.entrySet()) {
            edgesValues.put((IEdge) e.getKey(), Double.valueOf((Double) e.getValue()));

        }

    }

    /**
     * Sets the given Node Value to the given Size.
     *
     * @param n the node to change the Size of
     * @param val the Size to apply to the Node
     */
    @Override
    public void setNodeValue(INode n, Size val) {
        nodesValues.put(n, new Size(val));
    }

    /**
     * Sets the given Edge Value to the given Value.
     *
     * @param e the Edge to change the Value of
     * @param val the Value to apply to the Edge
     */
    @Override
    public void setEdgeValue(IEdge e, Double val) {
        edgesValues.put(e, val);
    }

}
