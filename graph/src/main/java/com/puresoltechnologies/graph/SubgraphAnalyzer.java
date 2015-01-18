package com.puresoltechnologies.graph;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
	requireNonNull(graph, "The given graph is null");
	Set<V> vertices = graph.getVertices();
	if ((vertices == null) || (vertices.isEmpty())) {
	    /*
	     * An empty graph has no disconnected sub graphs...
	     */
	    return false;
	}
	Set<V> notVisited = new HashSet<>(vertices);
	visitReachableGraph(notVisited.iterator().next(), new Stack<>(),
		notVisited);
	return !notVisited.isEmpty();
    }

    private static <V extends Vertex<V, E>, E extends Edge<V, E>> void visitReachableGraph(
	    V vertex, Stack<V> vertexStack, Set<V> notVisited) {
	if (vertexStack.contains(vertex)) {
	    return;
	}
	vertexStack.add(vertex);
	notVisited.remove(vertex);
	for (E edge : vertex.getEdges()) {
	    Pair<V> edgeVertices = edge.getVertices();
	    V next = edgeVertices.getFirst().equals(vertex) ? edgeVertices
		    .getSecond() : edgeVertices.getFirst();
	    visitReachableGraph(next, vertexStack, notVisited);
	}
	vertexStack.remove(vertex);
    }

    /**
     * This method searches a given {@link Graph} for cycles. This method is
     * different to {@link #hasCycles(Graph)}, because here it is started at a
     * dedicated vertex and only vertices are checked for cycles which are
     * connected to this start vertex. If disconnected subgraphs exist, these
     * are not checked.
     * 
     * @param graph
     *            is the {@link Graph} to be searched for cycles.
     * @param startVertex
     *            is the {@link Vertex} to start from. This vertex has to be
     *            part of the given graph.
     * @param directed
     *            is to be set to <code>true</code> is the graph is to be
     *            handled as an directed graph (The {@link Pair} result in
     *            {@link Edge#getVertices() is interpreted as startVertex and
     *            targetVertex}.). <code>false</code> is to be set otherwise.
     * @return <code>true</code> is returned if a cycle was found.
     *         <code>false</code> is returned otherwise.
     * @throws IllegalArgumentException
     *             is thrown in case the startVertex is not part of the graph or
     *             the graph of vertex are <code>null</code>.
     */
    public static <V extends Vertex<V, E>, E extends Edge<V, E>> boolean hasDisconnectedSubgraph(
	    Graph<V, E> graph, V startVertex) {
	requireNonNull(graph, "The given graph is null");
	requireNonNull(startVertex, "The given start vertex is null");
	if (!graph.getVertices().contains(startVertex)) {
	    throw new IllegalArgumentException("The given start vertex '"
		    + startVertex + "' is not part of the given graph '"
		    + graph + "'.");
	}
	HashSet<V> notVisited = new HashSet<V>(graph.getVertices());
	visitReachableGraph(startVertex, new Stack<>(), notVisited);
	return !notVisited.isEmpty();
    }

}
