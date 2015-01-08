package com.puresoltechnologies.commons.statemodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class contains method to check and evaluate {@link StateModel} objects.
 * 
 * @author Rick-Rainer Ludwig
 */
public class StateModelVerfier {

    /**
     * This method checks for the basic contracts of the state model:
     * <ol>
     * <li>There is exact one, non-null start state.</li>
     * <li>The start state has at least one transition.</li>
     * <li>The end state set is not null.</li>
     * <li>The end states do not have transitions.</li>
     * <li>The current state is not null.</li>
     * </ol>
     * 
     * @param <S>
     *            is the {@link State} implementation of the model.
     * @param <T>
     *            is the {@link Transition} implementation of the model.
     * @param stateModel
     *            is the {@link StateModel} to be checked.
     */
    public static <S extends State<S, T>, T extends Transition<S, T>> void verifyModel(
	    StateModel<S, T> stateModel) throws StateModelException {
	if (stateModel.getStartState() == null) {
	    throw new StateModelException("No start state is defined.");
	}
	if (stateModel.getStartState().getTransitions() == null) {
	    throw new StateModelException(
		    "No transition(s) for start state is/are defined, but an invalid null is returned.");
	}
	if (stateModel.getStartState().getTransitions().isEmpty()) {
	    throw new StateModelException(
		    "No transition(s) for start state is/are defined.");
	}
	if (stateModel.getEndStates() == null) {
	    throw new StateModelException("End states set must not be null.");
	}
	for (S state : stateModel.getEndStates()) {
	    if (state.getTransitions() == null) {
		throw new StateModelException(
			"Transitions set for an end state is null, but should return an empty set.");
	    }
	    if (!state.getTransitions().isEmpty()) {
		throw new StateModelException(
			"Transitions set for an end state is not empty, but is expected to be so.");
	    }
	}
	if (stateModel.getState() == null) {
	    throw new StateModelException(
		    "The current state is null, but a state model is expected to be in a state all the time.");
	}
    }

    /**
     * This method checks whether a model has dead ends or not. For models with
     * end states it is checked that from all states these end states can be
     * reached. If there is at least one state from which it is not possible,
     * the model has a dead end.
     * 
     * Beware: The check is done recursively (but in linear time). So, for large
     * models the stack needs to be large enough.
     * 
     * @param stateModel
     *            is the model to be checked. If the model has no end states, a
     *            {@link IllegalArgumentException} is thrown.
     * @return <code>true</code> is returned of dead ends exist.
     *         <code>false</code> is returned otherwise.
     * @throws IllegalArgumentException
     *             is thrown in case the model has no end states and cannot be
     *             checked for end states (because there are only dead ends).
     */
    public static <S extends State<S, T>, T extends Transition<S, T>> boolean hasDeadEnds(
	    StateModel<S, T> stateModel) throws IllegalArgumentException {
	Set<S> endStates = stateModel.getEndStates();
	if ((endStates == null) || (endStates.isEmpty())) {
	    throw new IllegalArgumentException("The model has no end states.");
	}
	Map<S, Set<S>> incomingTransitions = new HashMap<>();
	incomingTransitions.put(stateModel.getStartState(), new HashSet<>());
	createIncomingTransitions(incomingTransitions,
		stateModel.getStartState());
	Set<S> checkedStates = new HashSet<>();
	checkedStates.addAll(endStates);
	for (S endState : endStates) {
	    markStatesWhichCanReachEndStates(incomingTransitions,
		    checkedStates, endState);
	}
	return checkedStates.size() != incomingTransitions.size();
    }

    private static <S extends State<S, T>, T extends Transition<S, T>> void createIncomingTransitions(
	    Map<S, Set<S>> incomingTransitions, S state) {
	for (T transition : state.getTransitions()) {
	    S targetState = transition.getTargetState();
	    Set<S> incomingSet = incomingTransitions.get(targetState);
	    if (incomingSet == null) {
		incomingSet = new HashSet<>();
		incomingTransitions.put(targetState, incomingSet);
	    } else if (incomingSet.contains(state)) {
		continue;
	    }
	    incomingSet.add(state);
	    createIncomingTransitions(incomingTransitions, targetState);
	}
    }

    private static <S extends State<S, T>, T extends Transition<S, T>> void markStatesWhichCanReachEndStates(
	    Map<S, Set<S>> incomingTransitions, Set<S> checkedStates, S state) {
	Set<S> fromStates = incomingTransitions.get(state);
	if ((fromStates == null) || (fromStates.isEmpty())) {
	    return;
	}
	for (S fromState : fromStates) {
	    if (checkedStates.contains(fromState)) {
		continue;
	    }
	    checkedStates.add(fromState);
	    markStatesWhichCanReachEndStates(incomingTransitions,
		    checkedStates, fromState);
	}
    }

    /**
     * Private constructor to avoid instantiation.
     */
    private StateModelVerfier() {
    }
}
