package com.puresoltechnologies.commons.statemodel;

/**
 * This interface represents a single transition within the state model.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Transition<S extends State<S, T>, T extends Transition<S, T>> {

    /**
     * This method returns the localized name of the transition which is needed
     * to be presented in a UI.
     * 
     * @return A {@link String} is returned with the name.
     */
    public String getName();

    /**
     * This is the final state of the transition.
     * 
     * @return The current state object is returned.
     */
    public S getTargetState();
}
