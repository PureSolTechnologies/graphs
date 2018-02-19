package com.puresoltechnologies.graphs.statemodel;

import java.util.HashSet;
import java.util.Set;

public class TestStateImpl implements TestState {

    private final String name;
    private final Set<TestTransition> edges = new HashSet<>();
    private final Set<TestTransition> transitions = new HashSet<>();

    public TestStateImpl(String name) {
	super();
	this.name = name;
    }

    @Override
    public Set<TestTransition> getEdges() {
	return edges;
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
    public void addEdge(TestTransition edge) {
	edges.add(edge);
    }

    @Override
    public void addTransition(String name, TestState targetState) {
	TestTransition transition = new TestTransitionImpl(name, this,
		targetState);
	targetState.addEdge(transition);
	edges.add(transition);
	transitions.add(transition);
    }

    @Override
    public String toString() {
	return "State " + name;
    }
}
