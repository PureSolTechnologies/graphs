package com.puresoltechnologies.statemodel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestStateModelImpl extends
	AbstractStateModel<TestState, TestTransition> implements TestStateModel {

    private final TestState startState;
    private final Set<TestState> endStates;
    private final Set<TestState> vertices = new HashSet<>();

    public TestStateModelImpl(TestState startState, TestState... endStates) {
	this.startState = startState;
	this.endStates = new HashSet<>(Arrays.asList(endStates));
	addVertices();
    }

    private void addVertices() {
	vertices.add(startState);
	vertices.addAll(endStates);
	addVertices(startState);
    }

    private void addVertices(TestState state) {
	for (TestTransition transition : state.getTransitions()) {
	    TestState targetState = transition.getTargetState();
	    if (vertices.add(targetState)) {
		addVertices(targetState);
	    }
	}
    }

    @Override
    public Set<TestState> getVertices() {
	return vertices;
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
