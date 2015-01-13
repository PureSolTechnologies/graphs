package com.puresoltechnologies.statemodel;

import com.puresoltechnologies.graph.Pair;

class TestTransitionImpl implements TestTransition {

    private final String name;
    private final TestState initialState;
    private final TestState targetState;

    public TestTransitionImpl(String name, TestState initialState,
	    TestState targetState) {
	super();
	this.name = name;
	this.initialState = initialState;
	this.targetState = targetState;
    }

    @Override
    public Pair<TestState> getVertices() {
	return new Pair<TestState>(initialState, targetState);
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public TestState getTargetState() {
	return targetState;
    }

    @Override
    public String toString() {
	return "Transition " + name;
    }
}
