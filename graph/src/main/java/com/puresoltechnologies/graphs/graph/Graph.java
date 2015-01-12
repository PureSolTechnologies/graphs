package com.puresoltechnologies.graphs.graph;

import java.util.Set;

public interface Graph<V extends Vertex<V, E>, E extends Edge<V, E>> {

    public Set<V> getVertices();

    public Set<E> getEdges();

}
