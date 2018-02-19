package com.puresoltechnologies.graphs.graph;

import java.util.Set;

/**
 * This interface represents a whole graph.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <V>
 *            is the actual vertex implementation.
 * @param <E>
 *            is the actual edge implementation.
 */
public interface Graph<V extends Vertex<V, E>, E extends Edge<V, E>> {

    /**
     * This method returns all vertices of the graph.
     * 
     * @return A {@link Set} of vertices is returned.
     */
    public Set<V> getVertices();

}
