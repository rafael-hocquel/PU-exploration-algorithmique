package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.INode;
import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author rhocquel
 */
public class WelshAndPowell implements IAlgorithm<ColorProperty> {

    /**
     * Returns a sorted ArrayList (in descending nodes degrees order) copy of
     * the given ArrayList of nodes based on their degree.
     *
     * @param nodes the ArrayList of nodes to sort
     * @return a sorted copy of the given nodes ArrayList (sorted by descending
     * order by degree)
     */
    private static ArrayList<INode> decendingSortArrayListCopy(ArrayList<INode> nodes) {
        ArrayList<INode> copy = new ArrayList<>(nodes);
        copy.sort((node1, node2) -> node2.getDegree() - node1.getDegree());

        return copy;
    }

    /**
     * Returns a HashSet containing all the colors taken by the neighbors of the
     * given node.
     *
     * @param node the node we want to get it's taken neighbors colors
     * @param integerColoration Map of each node with it's corresponding
     * integer-coloration
     * @return HashSet containing all the integer-colors used by the neighbors
     * of the given node
     */
    private static HashSet<Integer> neighborsColors(INode node, HashMap<INode, Integer> integerColoration) {
        HashSet<Integer> usedIntegerColors = new HashSet<>();

        for (INode neighbor : node.getNeighbors()) {
            Integer neighborColor = integerColoration.get(neighbor);

            if (neighborColor != null) {
                usedIntegerColors.add(neighborColor);
            }
        }
        return usedIntegerColors;
    }

    /**
     * Returns the first positive integer not in the given HashSet.
     *
     * @param usedIntegerColors HashSet of used integer-colors
     * @return the first integer (positive) not in the given hashset
     */
    private static int getFirstUnusedIntegerColor(HashSet<Integer> usedIntegerColors) {
        int newIntegerColor = 0;
        while (usedIntegerColors.contains(newIntegerColor)) {
            newIntegerColor++;
        }

        return newIntegerColor;
    }

    /**
     * Implementation of the abstract 'apply' method from IAlgorithm. Compute
     * the result of the algorithm and returns it
     *
     * @param g the graph on which the algorithm is applied
     * @param parameters map of parameters associating parameters names to
     * parameters values
     * @return the result of the algorithm
     */
    @Override
    public ColorProperty apply(IGraph g, Map<String, Object> parameters) {
        ColorProperty colorProperty = new ColorProperty();
        HashMap<INode, Integer> integerColoration = new HashMap<>();

        ArrayList<INode> orderedNodes = WelshAndPowell.decendingSortArrayListCopy(g.getNodes());

        for (INode node : orderedNodes) {

            HashSet<Integer> usedIntegerColors = WelshAndPowell.neighborsColors(node, integerColoration);

            int newIntegerColor = WelshAndPowell.getFirstUnusedIntegerColor(usedIntegerColors);
            integerColoration.put(node, newIntegerColor);

            Color newColor = Color.intToColor(newIntegerColor);
            colorProperty.setNodeValue(node, newColor);

        }

        return colorProperty;
    }
}
