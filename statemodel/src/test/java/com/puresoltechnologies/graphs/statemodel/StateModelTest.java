package com.puresoltechnologies.graphs.statemodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class StateModelTest {

    private static final TestState startState = new TestStateImpl("Start");
    private static final TestState state2 = new TestStateImpl("State2");
    private static final TestState endState = new TestStateImpl("End");
    static {
	startState.addTransition("StartState2", state2);
	state2.addTransition("State2End", endState);
    }

    private static final TestStateModel testStateModel = new TestStateModelImpl(startState, state2, endState);

    @Test
    public void test() {
	assertEquals(startState, testStateModel.getState());
	TestTransition transition = testStateModel.canGoTo(state2);
	assertNotNull(transition);
	testStateModel.goTo(state2);
	assertEquals(state2, testStateModel.getState());
	transition = testStateModel.canGoTo(endState);
	assertNotNull(transition);
	testStateModel.performTransition(transition);
	assertEquals(endState, testStateModel.getState());
    }
}
