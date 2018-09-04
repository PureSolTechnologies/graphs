package com.puresoltechnologies.graphs.statemodel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StateModelVerfierTest {

    private TestStateModel createModelSkeleton() {
	TestState startState = mock(TestState.class);
	Set<TestTransition> startTransitions = new HashSet<>();
	startTransitions.add(mock(TestTransition.class));
	when(startState.getTransitions()).thenReturn(startTransitions);

	Set<TestState> endStates = new HashSet<>();
	endStates.add(mock(TestState.class));

	TestStateModel stateModel = mock(TestStateModel.class);
	when(stateModel.getStartState()).thenReturn(startState);
	when(stateModel.getEndStates()).thenReturn(endStates);
	when(stateModel.getState()).thenReturn(startState);
	return stateModel;
    }

    @Test
    public void shouldVerifyMockAsValid() throws StateModelException {
	TestStateModel stateModel = createModelSkeleton();
	StateModelVerfier.verifyModel(stateModel);
    }

    @Test
    public void shouldThrowExceptionWhenStartStateIsNull() throws StateModelException {
	Assertions.assertThrows(StateModelException.class, () -> {
	    TestStateModel stateModel = createModelSkeleton();
	    when(stateModel.getStartState()).thenReturn(null);
	    StateModelVerfier.verifyModel(stateModel);
	}, "No start state is defined.");
    }

    @Test
    public void shouldThrowExceptionWhenStartStateHasNoTransitionsWithNull() throws StateModelException {
	Assertions.assertThrows(StateModelException.class, () -> {
	    TestStateModel stateModel = createModelSkeleton();
	    when(stateModel.getStartState().getTransitions()).thenReturn(null);
	    StateModelVerfier.verifyModel(stateModel);
	}, "No transition(s) for start state is/are defined, but an invalid null is returned.");
    }

    @Test
    public void shouldThrowExceptionWhenStartStateHasNoTransitions() throws StateModelException {
	Assertions.assertThrows(StateModelException.class, () -> {
	    TestStateModel stateModel = createModelSkeleton();
	    when(stateModel.getStartState().getTransitions()).thenReturn(new HashSet<>());
	    StateModelVerfier.verifyModel(stateModel);
	}, "No transition(s) for start state is/are defined.");
    }

    @Test
    public void shouldThrowExceptionWhenCurrentStateIsNull() throws StateModelException {
	Assertions.assertThrows(StateModelException.class, () -> {
	    TestStateModel stateModel = createModelSkeleton();
	    when(stateModel.getState()).thenReturn(null);
	    StateModelVerfier.verifyModel(stateModel);
	}, "The current state is null, but a state model is expected to be in a state all the time.");
    }

    @Test
    public void shouldThrowExceptionWhenEndStatesSetIsNull() throws StateModelException {
	Assertions.assertThrows(StateModelException.class, () -> {
	    TestStateModel stateModel = createModelSkeleton();
	    when(stateModel.getEndStates()).thenReturn(null);
	    StateModelVerfier.verifyModel(stateModel);
	}, "End states set must not be null.");
    }

    @Test
    public void shouldThrowExceptionWhenEndStateHasTransition() throws StateModelException {
	Assertions.assertThrows(StateModelException.class, () -> {
	    TestStateModel stateModel = createModelSkeleton();
	    TestState endStateWithTransition = mock(TestState.class);
	    Set<TestTransition> transitions = new HashSet<>();
	    transitions.add(mock(TestTransition.class));
	    when(endStateWithTransition.getTransitions()).thenReturn(transitions);
	    stateModel.getEndStates().add(endStateWithTransition);
	    StateModelVerfier.verifyModel(stateModel);
	}, "Transitions set for an end state is not empty, but is expected to be so.");
    }

    @Test
    public void shouldThrowExceptionWhenEndStateHasTransitionsSetNull() throws StateModelException {
	Assertions.assertThrows(StateModelException.class, () -> {
	    TestStateModel stateModel = createModelSkeleton();
	    TestState endStateWithTransition = mock(TestState.class);
	    Set<TestTransition> transitions = new HashSet<>();
	    when(endStateWithTransition.getTransitions()).thenReturn(null);
	    stateModel.getEndStates().add(endStateWithTransition);
	    StateModelVerfier.verifyModel(stateModel);
	}, "Transitions set for an end state is null, but should return an empty set.");
    }

}
