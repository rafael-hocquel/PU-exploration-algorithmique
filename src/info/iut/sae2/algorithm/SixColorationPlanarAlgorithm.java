/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.Color;
import info.iut.sae2.properties.ColorProperty;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author lhampegarrid
 */
public class SixColorationPlanarAlgorithm implements IAlgorithm<ColorProperty>{
    /**
     * List colors which can be used
     */
    private ArrayList<Color> colors = new ArrayList<>();


    /**
     * Apply method for the 6 coloration algorithm.
     * 
     * @param graph the graph to color
     * @param parameters optional parameters
     * @return the ColorProperty corresponding to the coloration of the graph
     */
    @Override
    public ColorProperty apply(IGraph graph, Map<String, Object> parameters) {
        ColorProperty resultProperty = new ColorProperty();
        colors.clear();
        IGraph g = graph.copy();

        HashMap<INode, INode> correspondingNodes = new HashMap<>();
        for (int i = 0; i < graph.getNodes().size(); i++) {
            correspondingNodes.put(g.getNodes().get(i), graph.getNodes().get(i));
        }

        for (int i = 0; i <= 6; i++) {
            colors.add(Color.intToColor(i));
        }
        ColorProperty tempProperty = new ColorProperty();
        
        Stack<INode> nodes = new Stack<INode>();
        while(!g.getNodes().isEmpty()){
            INode n = g.getFirstValidNode();
            nodes.push(n);
            g.delNode(n);
        }
        while(!nodes.isEmpty()){
            INode n = nodes.pop();
            tempProperty.setNodeValue(n, lowestColor(n, tempProperty));
            resultProperty.setNodeValue(correspondingNodes.get(n), tempProperty.getNodeValue(n));
        }

        return resultProperty;
    }

    /**
     * Returns the lowest color not used by any neighbor of the given node.
     *
     * @param n the node to color
     * @param colProperty the color property containing the current coloration
     * @return the lowest color
     */
    private Color lowestColor(INode n, ColorProperty colProperty) {
        HashSet<Color> usedColors = new HashSet<>();
        for (INode neighbor : n.getNeighbors()) {
            usedColors.add(colProperty.getNodeValue(neighbor));
        }
        int i = 0;
        while (usedColors.contains(colors.get(i))) {
            i++;
        }
        return colors.get(i);
    }
}
