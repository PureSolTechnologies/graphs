package com.puresoltechnologies.commons.statemodel;

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
	 * This is the default constructor. It starts the model at the start state
	 * as returned in {@link #getStartState()}.
	 */
	public AbstractStateModel() {
		super();
		currentState = getStartState();
	}

	/**
	 * This constructor starts at a given initial state. This can be useful in
	 * cases were a model was persisted to restart it.
	 * 
	 * @param initialState
	 */
	public AbstractStateModel(S initialState) {
		super();
		currentState = initialState;
	}

	@Override
	public final S getState() {
		return currentState;
	}

	protected final void setState(S state) {
		currentState = state;
	}

	@Override
	public boolean canPerformTransition(T transition) {
		return currentState.getTransitions().contains(transition);
	}

	@Override
	public final void performTransition(T transition) {
		if (!canPerformTransition(transition)) {
			throw new IllegalStateException("Transition '"
					+ transition.getName() + "' is not allowed in state '"
					+ currentState.getName() + "'");
		}
		currentState = transition.getTargetState();
	}

}
