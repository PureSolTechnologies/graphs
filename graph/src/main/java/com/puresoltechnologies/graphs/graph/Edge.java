package com.puresoltechnologies.graphs.graph;

import java.util.Set;

/**
 * This interface represents a single edge which connects two vertices.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Edge<V extends Vertex<V, E>, E extends Edge<V, E>> {

    public Set<V> getVertices();

}
