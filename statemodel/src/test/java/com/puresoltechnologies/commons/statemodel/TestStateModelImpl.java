package com.puresoltechnologies.commons.statemodel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestStateModelImpl extends
	AbstractStateModel<TestState, TestTransition> implements TestStateModel {

    private final TestState startState;
    private final Set<TestState> endStates;

    public TestStateModelImpl(TestState startState, TestState... endStates) {
	this.startState = startState;
	this.endStates = new HashSet<>(Arrays.asList(endStates));
    }

    @Override
    public TestState getStartState() {
	return startState;
    }

    @Override
    public Set<TestState> getEndStates() {
	return endStates;
    }
}
