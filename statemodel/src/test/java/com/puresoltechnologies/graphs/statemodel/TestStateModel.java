package com.puresoltechnologies.graphs.statemodel;

import com.puresoltechnologies.graphs.statemodel.StateModel;

/**
 * This is an generic free interface of {@link StateModel} for testing to get
 * the tests clean of warning messages.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface TestStateModel extends StateModel<TestState, TestTransition> {
}
