package com.puresoltechnologies.graph;

public class SubgraphAnalyzer {

    /**
     * This method searches a given {@link Graph} for disconnected subgraphs.
     * 
     * @param graph
     *            is the {@link Graph} to be searched for disconnected
     *            subgraphs.
     * @return <code>true</code> is returned if a disconnected subgraph (vertex)
     *         was found. <code>false</code> is returned otherwise.
     */
    public static <V extends Vertex<V, E>, E extends Edge<V, E>> boolean hasDisconnectedSubgraph(
	    Graph<V, E> graph) {
	// FIXME
	throw new IllegalStateException("Not implemented, yet!");
    }

}
