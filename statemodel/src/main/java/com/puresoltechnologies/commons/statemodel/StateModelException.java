package com.puresoltechnologies.commons.statemodel;

/**
 * This exception is thrown in cases of issues with the state model.
 * 
 * @author Rick-Rainer Ludwig
 */
public class StateModelException extends Exception {

    private static final long serialVersionUID = -1903585625689378700L;

    public StateModelException(String message, Throwable cause) {
	super(message, cause);
    }

    public StateModelException(String message) {
	super(message);
    }

}
