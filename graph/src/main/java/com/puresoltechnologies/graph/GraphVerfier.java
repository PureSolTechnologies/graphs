package com.puresoltechnologies.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains method to check and evaluate {@link StateModel} objects.
 * 
 * @author Rick-Rainer Ludwig
 */
public class GraphVerfier {

    /**
     * This method checks whether a graph has dead ends or not assuming the
     * provided graph is directed and the {@link Pair}s in the {@link Edge}s are
     * interpreted with first and second {@link Vertex} as start and target
     * state. The dead ends are checked against a set of end states which are
     * provided.
     * 
     * For graph with these end states it is checked that from all vertices of
     * the {@link Graph} an end state can be reached. If there is at least one
     * state from which it is not possible, the graph has a dead end.
     * 
     * Beware: The check is done recursively (but in linear time). So, for large
     * models the stack needs to be large enough.
     * 
     * 
     * @param stateModel
     *            is the model to be checked. If the model has no end states, a
     *            {@link IllegalArgumentException} is thrown.
     * @param startVertex
     *            is the {@link Vertex} to start the dead end search.
     * @param endVertices
     *            is a {@link Set} of {@link Vertex} which define which vertices
     *            are to be treated as final states.
     * @param <V>
     *            is the {@link Vertex} implementation to be used.
     * @param <E>
     *            is the {@link Edge} implementation to be used.
     * @return <code>true</code> is returned of dead ends exist.
     *         <code>false</code> is returned otherwise.
     * @throws IllegalArgumentException
     *             is thrown in case the model has no end states and cannot be
     *             checked for end states (because there are only dead ends).
     */
    public static <V extends Vertex<V, E>, E extends Edge<V, E>> boolean hasDeadEnds(
	    Graph<V, E> stateModel, V startVertex, Set<V> endVertices)
	    throws IllegalArgumentException {
	if ((endVertices == null) || (endVertices.isEmpty())) {
	    throw new IllegalArgumentException("The model has no end states.");
	}
	Set<V> checkedStates = new HashSet<>();
	checkedStates.addAll(endVertices);
	for (V endState : endVertices) {
	    markStatesWhichCanReachEndStates(checkedStates, endState);
	}
	return checkedStates.size() != stateModel.getVertices().size();
    }

    private static <V extends Vertex<V, E>, E extends Edge<V, E>> void markStatesWhichCanReachEndStates(
	    Set<V> checkedStates, V state) {
	Set<V> fromStates = new HashSet<>();
	for (E edge : state.getEdges()) {
	    Pair<V> vertices = edge.getVertices();
	    if (vertices.getSecond().equals(state)) {
		fromStates.add(vertices.getFirst());
	    }
	}
	if ((fromStates == null) || (fromStates.isEmpty())) {
	    return;
	}
	for (V fromState : fromStates) {
	    if (checkedStates.contains(fromState)) {
		continue;
	    }
	    checkedStates.add(fromState);
	    markStatesWhichCanReachEndStates(checkedStates, fromState);
	}
    }

    /**
     * Private constructor to avoid instantiation.
     */
    private GraphVerfier() {
    }
}
