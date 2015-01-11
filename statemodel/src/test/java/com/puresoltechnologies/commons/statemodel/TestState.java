package com.puresoltechnologies.commons.statemodel;

/**
 * This is an generic free interface of {@link State} for testing to get the
 * tests clean of warning messages.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface TestState extends State<TestState, TestTransition> {

    void addTransition(TestTransition transition);

}
