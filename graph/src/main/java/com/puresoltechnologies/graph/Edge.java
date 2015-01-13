package com.puresoltechnologies.graph;

/**
 * This interface represents a single edge which connects two vertices. This
 * interface does not specify whether the edge is directed or not. It is up to
 * the implementing class whether the order of the given {@link Pair} of
 * vertices is of importance or not.
 * 
 * @param <V>
 *            is the actual implementing class for the vertices to be used.
 * @param <E>
 *            is the actual implementation for the edges.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Edge<V extends Vertex<V, E>, E extends Edge<V, E>> {

    /**
     * This method returns the vertices which are connected by this edge.
     * Whether the order of the vertices is of importance or not is up to the
     * implementing class.
     * 
     * @return A {@link Pair} of vertices is returned.
     */
    public Pair<V> getVertices();

}
