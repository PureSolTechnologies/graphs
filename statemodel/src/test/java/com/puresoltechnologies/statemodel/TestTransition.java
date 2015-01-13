package com.puresoltechnologies.statemodel;

import com.puresoltechnologies.statemodel.Transition;

/**
 * This is an generic free interface of {@link Transition} for testing to get
 * the tests clean of warning messages.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface TestTransition extends Transition<TestState, TestTransition> {
}
