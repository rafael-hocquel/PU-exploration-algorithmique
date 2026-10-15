/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.Color;
import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import info.iut.sae2.properties.ColorProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
/**
 *
 * @author lhampegarrid
 */
public class FiveColorationPlanarAlgorithm implements IAlgorithm<ColorProperty> {
    
    /**
     * List colors which can be used
     */
    private ArrayList<Color> colors = new ArrayList<>();
    
    private HashSet<INode> usedNodes = new HashSet<>();
    
    
    /**
     * 
     */
    @Override
    public ColorProperty apply(IGraph graph, Map<String, Object> parameters){
        ColorProperty resultProperty = new ColorProperty();
        colors.clear();
        IGraph g = graph.copy();

        HashMap<INode, INode> correspondingNodes = new HashMap<>();
        for (int i = 0; i < graph.getNodes().size(); i++) {
            correspondingNodes.put(g.getNodes().get(i), graph.getNodes().get(i));
        }

        for (int i = 0; i <= 10; i++) {
            colors.add(Color.intToColor(i));
        }

        ColorProperty tempProperty = new ColorProperty();
        while (!g.getNodes().isEmpty()) {
            coloration(g, g.getFirstValidNode(), tempProperty);
        }
        
        
        for (Map.Entry<INode, INode> entry : correspondingNodes.entrySet()) {
            INode nodeCopy = entry.getKey();
            INode originalNode = entry.getValue();
            Color c = tempProperty.getNodeValue(nodeCopy);
            resultProperty.setNodeValue(originalNode, c);
        }

        return resultProperty;
    }
    
    /**
     * Recursive property which colors a given node like the 5-coloration algorithm
     * 
     * @param g the graph to color
     * @param n the node to color
     * @param colProperty the color property of the current coloration
     * @param colProperty
     */
    private void coloration(IGraph g, INode n, ColorProperty colProperty){
        ArrayList<INode> nonAdjacentNodes = new ArrayList<>();
        boolean fusion = n.getNeighbors().size() == 5;
        if (fusion) {
            nonAdjacentNodes = nonAdjacentNeighbors(n.getNeighbors());
            for (INode neighbor : nonAdjacentNodes.get(1).getNeighbors()) {
                g.addEdge(nonAdjacentNodes.get(0), neighbor);
            }
            g.delNode(nonAdjacentNodes.get(1));
            usedNodes.add(nonAdjacentNodes.get(1));
        }
        g.delNode(n);
        usedNodes.add(n);
        if (fusion) {
            coloration(g, nonAdjacentNodes.get(0), colProperty);
        }
        for (INode neighbor : n.getNeighbors()) {
            if (!usedNodes.contains(n) && n.getDegree() <= 5) {
                coloration(g, neighbor, colProperty);
            }
        }
        if (fusion) {
            colProperty.setNodeValue(nonAdjacentNodes.get(1), colProperty.getNodeValue(nonAdjacentNodes.get(0)));
        }
        colProperty.setNodeValue(n, lowestColor(n, colProperty));
    }
    
    /**
     * Returns the first two non adjacent neighbors of a given nodes
     * 
     * @param nodes the node neighbors
     * @return the first two non adjacent neighbors of a given nodes
     */
    private ArrayList<INode> nonAdjacentNeighbors(ArrayList<INode> nodes){
        boolean found = false;
        ArrayList<INode> nonAdjacentNodes = new ArrayList<>();
        int j, i = 0;
        while (!found && i < nodes.size()) { 
            j = i + 1;
            while (!found && j < nodes.size()) { 
                if (!nodes.get(i).getNeighborsHashSet().contains(nodes.get(j))) {
                    found = true;
                    nonAdjacentNodes.add(nodes.get(i));
                    nonAdjacentNodes.add(nodes.get(j));
                }
                j++;
            }
            i++;
        }
        return nonAdjacentNodes;
    }

    /**
     * Returns the used color of least value
     * 
     * @param n the node to color
     * @param colProperty the color property of the current coloration
     * @return the lowest color
     */
    private Color lowestColor(INode n, ColorProperty colProperty){
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
