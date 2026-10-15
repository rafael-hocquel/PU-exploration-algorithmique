package info.iut.sae2.properties;

import info.iut.sae2.graphs.Coord;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.graphs.IEdge;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 *
 * @author rbourqui
 */
public class LayoutProperty extends AbstractProperty<Coord, ArrayList<Coord>> {

    final public static Coord DEFAULT_NODE_POS = new Coord();
    final public static ArrayList<Coord> DEFAULT_EDGE_POS = new ArrayList<>();

    /**
     * Default constructor of LayoutProperty
     */
    public LayoutProperty() {
    }

    /**
     * Copy constructor. Each value of the given LayoutProperty are copied
     * before being associated to the nodes and edges.
     *
     * @param layout the LayoutProperty to be copied
     */
    public LayoutProperty(LayoutProperty layout) {
        for (Entry e : layout.nodesValues.entrySet()) {
            nodesValues.put((INode) e.getKey(), new Coord((Coord) e.getValue()));
        }

        for (Entry e : layout.edgesValues.entrySet()) {
            IEdge edge = (IEdge) e.getKey();
            Object edgeValues = e.getValue();
            ArrayList eBends = (ArrayList) edgeValues;

            ArrayList<Coord> bends = new ArrayList<>(eBends.size());
            for (var c : eBends) {
                bends.add(new Coord((Coord) c));
            }
            edgesValues.put(edge, bends);
        }

    }

    /**
     * Sets the given Node Coord to the given Coord.
     *
     * @param n the node to change the Coord of
     * @param val the Coord to apply to the Node
     */
    @Override
    public void setNodeValue(INode n, Coord val) {
        nodesValues.put(n, new Coord(val));
    }

    /**
     * Sets the given Edge Coords to the given Coords.
     *
     * @param e the Edge to change the Coords of
     * @param val the Coords to apply to the Edge
     */
    @Override
    public void setEdgeValue(IEdge e, ArrayList<Coord> val) {
        ArrayList<Coord> bends = new ArrayList<>(val.size());
        for (Coord c : val) {
            bends.add(new Coord(c));
        }
        edgesValues.put(e, bends);
    }
}
