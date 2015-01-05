package com.puresoltechnologies.commons.statemodel;

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
     * @param S
     *            is the {@link State} implementation of the model.
     * @param T
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
     * Private constructor to avoid instantiation.
     */
    private StateModelVerfier() {
    }
}
