package info.iut.sae2.algorithm;

import info.iut.sae2.graphs.IGraph;
import info.iut.sae2.graphs.INode;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author rbourqui
 */
public interface IAlgorithm<T> {

    /**
     * Compute the result of the algorithm and returns it.
     *
     * @param g the graph on which the algorithm is applied
     * @param parameters map of parameters associating parameters names to
     * parameters values
     * @return the result of the algorithm
     */
    public abstract T apply(IGraph g, Map<String, Object> parameters);
}
