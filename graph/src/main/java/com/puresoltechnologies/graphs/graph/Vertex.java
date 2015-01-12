package com.puresoltechnologies.graphs.graph;

import java.util.Set;

public interface Vertex<V extends Vertex<V, E>, E extends Edge<V, E>> {

    public Set<E> getEdges();
}
