package com.puresoltechnologies.commons.statemodel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestStateImpl implements TestState {

    private final String name;
    private final Set<TestTransition> transitions;

    public TestStateImpl(String name, TestTransition... transitions) {
	super();
	this.name = name;
	this.transitions = new HashSet<>(Arrays.asList(transitions));
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public Set<TestTransition> getTransitions() {
	return transitions;
    }

    @Override
    public void addTransition(TestTransition transition) {
	transitions.add(transition);
    }

    @Override
    public String toString() {
	return "State " + name;
    }
}
