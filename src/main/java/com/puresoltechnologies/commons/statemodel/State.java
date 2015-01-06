package com.puresoltechnologies.commons.statemodel;

import java.util.Set;

/**
 * This is a single state of the system within the state model.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface State<S extends State<S, T>, T extends Transition<S, T>> {

    /**
     * This method returns the localized name of the state which is needed to be
     * presented in a UI.
     * 
     * @return A {@link String} with the name is returned.
     */
    public String getName();

    /**
     * This method returns the valid, applicable transitions of the state.
     * 
     * The list of transition must not be null! An empty is list is to be
     * returned if no transition can be found!
     * 
     * @return A {@link Set} of valid transitions is returned. The return value
     *         must not be null, but an empty set is allowed.
     */
    public Set<T> getTransitions();

}
