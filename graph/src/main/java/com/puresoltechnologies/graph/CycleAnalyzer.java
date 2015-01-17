package com.puresoltechnologies.graph;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CycleAnalyzer {

    /**
     * This method searches a given {@link Graph} for cycles. This method looks
     * into all vertices. If a disconnected subgraph exists, this graph is
     * analyzed, too.
     * 
     * @param graph
     *            is the {@link Graph} to be searched for cycles.
     * @param directed
     *            is to be set to <code>true</code> is the graph is to be
     *            handled as an directed graph (The {@link Pair} result in
     *            {@link Edge#getVertices() is interpreted as startVertex and
     *            targetVertex}.). <code>false</code> is to be set otherwise.
     * @return <code>true</code> is returned if a cycle was found.
     *         <code>false</code> is returned otherwise.
     */
    public static <V extends Vertex<V, E>, E extends Edge<V, E>> boolean hasCycles(
	    Graph<V, E> graph, boolean directed) {
	requireNonNull(graph, "The given start vertex is null");
	Set<V> notVisited = new HashSet<>(graph.getVertices());
	while (!notVisited.isEmpty()) {
	    if (hasCycle(notVisited.iterator().next(), new Stack<>(),
		    new Stack<>(), notVisited, directed)) {
		return true;
	    }
	}
	return false;
    }

    private static <V extends Vertex<V, E>, E extends Edge<V, E>> boolean hasCycle(
	    V vertex, Stack<V> vertexStack, Stack<E> edgeStack,
	    Set<V> notVisited, boolean directed) {
	if (vertexStack.contains(vertex)) {
	    return true;
	}
	vertexStack.add(vertex);
	notVisited.remove(vertex);
	for (E edge : vertex.getEdges()) {
	    if (edgeStack.contains(edge)) {
		continue;
	    }
	    Pair<V> edgeVertices = edge.getVertices();
	    edgeStack.add(edge);
	    if (directed) {
		if (edgeVertices.getFirst().equals(vertex)) {
		    if (hasCycle(edgeVertices.getSecond(), vertexStack,
			    edgeStack, notVisited, directed)) {
			return true;
		    }
		}
	    } else {
		V next = edgeVertices.getFirst().equals(vertex) ? edgeVertices
			.getSecond() : edgeVertices.getFirst();
		if (hasCycle(next, vertexStack, edgeStack, notVisited, directed)) {
		    return true;
		}
	    }
	    edgeStack.remove(edge);
	}
	vertexStack.remove(vertex);
	return false;
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
    public static <V extends Vertex<V, E>, E extends Edge<V, E>> boolean hasCycles(
	    Graph<V, E> graph, V startVertex, boolean directed)
	    throws IllegalArgumentException {
	requireNonNull(graph, "The given start vertex is null");
	requireNonNull(startVertex, "The given start vertex is null");
	if (!graph.getVertices().contains(startVertex)) {
	    throw new IllegalArgumentException("The given start vertex '"
		    + startVertex + "' is not part of the given graph '"
		    + graph + "'.");
	}
	return hasCycle(startVertex, new Stack<>(), new Stack<>(),
		new HashSet<V>(graph.getVertices()), directed);
    }
}
