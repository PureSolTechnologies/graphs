package com.puresoltechnologies.commons.statemodel;

import java.util.Set;

/**
 * This is an interface for a state model.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface StateModel<S extends State<S, T>, T extends Transition<S, T>> {

    /**
     * This method returns the start state which is not valid, yet, but the
     * starting point of the first transition to be made. The first transition
     * may be on of multiple transitions.
     * 
     * The start state must have at least one valid transition!
     * 
     * @return The start state is returned. The return value must not be null,
     *         because every state model has to have a start state.
     */
    public S getStartState();

    /**
     * This method returns the possible end states of the model which show, that
     * the model is finished and cannot be changed anymore. There are different
     * possible end states due to the normal ends, aborts and errors.
     * 
     * These are the states which must not have a transition! All other states
     * need to have at least one.
     * 
     * @return A {@link Set} of state is returned which mark the end state of
     *         the state model. The return value must not be null, but the set
     *         might be empty.
     */
    public Set<S> getEndStates();

    /**
     * This method returns the current {@link State} of the model.
     * 
     * @return The current {@link State} is returned. The return value must not
     *         be null, because the state model has to be in one state.
     */
    public S getState();

    /**
     * This method checks whether a transition can be performed with the current
     * state.
     * 
     * @param transition
     *            is the transition to be checked for validity at the current
     *            state.
     * @return <code>true</code> is returned in case the transition is valid at
     *         current state. <code>false</code> is returned otherwise.
     */
    public boolean canPerformTransition(T transition);

    /**
     * This method performs the transition provided.
     * 
     * @param transition
     *            is the transition to perform.
     * @throws IllegalStateException
     *             is throw in case the transition is not valid for the current
     *             state.
     */
    public void performTransition(T transition);

}
