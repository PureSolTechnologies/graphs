package com.puresoltechnologies.graphs.statemodel;

/**
 * This is the abstract implementation of a state model.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractStateModel<S extends State<S, T>, T extends Transition<S, T>>
	implements StateModel<S, T> {

    private S currentState;

    /**
     * This is the default constructor. It starts the model at the start state as
     * returned in {@link #getStartState()}.
     */
    public AbstractStateModel() {
	super();
    }

    /**
     * This constructor starts at a given initial state. This can be useful in cases
     * were a model was persisted to restart it.
     * 
     * @param initialState
     *            is the state to be set as initial state if not
     *            {@link #getStartState()} is to be set in
     *            {@link #AbstractStateModel()}.
     */
    public AbstractStateModel(S initialState) {
	super();
	currentState = initialState;
    }

    @Override
    public final S getState() {
	if (currentState == null) {
	    currentState = getStartState();
	}
	return currentState;
    }

    protected void setState(S state) {
	currentState = state;
    }

    @Override
    public boolean canPerformTransition(T transition) {
	return currentState.getTransitions().contains(transition);
    }

    @Override
    public final void performTransition(T transition) {
	if (!canPerformTransition(transition)) {
	    throw new IllegalStateException("Transition '" + transition.getName() + "' is not allowed in state '"
		    + currentState.getName() + "'");
	}
	currentState = transition.getTargetState();
    }

    @Override
    public T canGoTo(S state) {
	for (T transition : currentState.getTransitions()) {
	    if (transition.getTargetState() == state) {
		return transition;
	    }
	}
	return null;
    }

    @Override
    public void goTo(S state) {
	for (T transition : currentState.getTransitions()) {
	    if (transition.getTargetState() == state) {
		performTransition(transition);
		return;
	    }
	}
	throw new IllegalStateException(
		"No transition is allowed from state '" + currentState.getName() + "' to state '" + state + "'.");
    }
}
