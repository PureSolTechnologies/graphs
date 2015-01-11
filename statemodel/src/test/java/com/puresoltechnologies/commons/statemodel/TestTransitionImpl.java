package com.puresoltechnologies.commons.statemodel;

public class TestTransitionImpl implements TestTransition {

    private final String name;
    private final TestState targetState;

    public TestTransitionImpl(String name, TestState targetState) {
	super();
	this.name = name;
	this.targetState = targetState;
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
