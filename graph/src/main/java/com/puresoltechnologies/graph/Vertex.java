package com.puresoltechnologies.graph;

import java.util.Set;

/**
 * This interface represents a single vertex in a {@link Graph}. Whether the
 * edges are directed or not is up to the implementing class to decide.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <V>
 *            is the actual implementation of the vertex.
 * @param <E>
 *            is the actual implementation for the edges.
 */
public interface Vertex<V extends Vertex<V, E>, E extends Edge<V, E>> {

    /**
     * This method returns the edges which are bound to the vertex. All edges
     * should be returned here no matter whether they are outgoing or incoming.
     * 
     * @return A {@link Set} is returned containing the edges which are bound to
     *         this vertex.
     */
    public Set<E> getEdges();
}
