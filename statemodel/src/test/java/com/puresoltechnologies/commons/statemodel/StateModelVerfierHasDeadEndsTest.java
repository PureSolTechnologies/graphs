package com.puresoltechnologies.commons.statemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateModelVerfierHasDeadEndsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNoEndStates() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("The model has no end states.");
	TestStateModel stateModel = mock(TestStateModel.class);
	when(stateModel.getEndStates()).thenReturn(new HashSet<>());
	StateModelVerfier.hasDeadEnds(stateModel);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenEndStatesAreNull() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("The model has no end states.");
	TestStateModel stateModel = mock(TestStateModel.class);
	when(stateModel.getEndStates()).thenReturn(null);
	StateModelVerfier.hasDeadEnds(stateModel);
    }

    @Test
    public void shouldReturnFalseForSingleStateModel() {
	TestState state = mock(TestState.class);

	HashSet<TestState> endStates = new HashSet<>();
	endStates.add(state);

	TestStateModel stateModel = mock(TestStateModel.class);
	when(stateModel.getStartState()).thenReturn(state);
	when(stateModel.getState()).thenReturn(state);
	when(stateModel.getEndStates()).thenReturn(endStates);
	assertFalse(StateModelVerfier.hasDeadEnds(stateModel));
    }

    /**
     * State model:
     * 
     * <pre>
     * (1) -1-&gt; (2) -2-&gt; (3/End)
     * </pre>
     */
    @Test
    public void shouldReturnFalseForSimpleStateModel() {
	TestState state3 = new TestStateImpl("3");
	TestTransition transition2 = new TestTransitionImpl("2", state3);
	TestState state2 = new TestStateImpl("2", transition2);
	TestTransition transition1 = new TestTransitionImpl("1", state2);
	TestState state1 = new TestStateImpl("1", transition1);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertFalse(StateModelVerfier.hasDeadEnds(stateModel));
    }

    /**
     * State model:
     * 
     * <pre>
     * (1) -1-&gt; (2) -2-&gt; (3/End)
     *            \
     *             \-3-&gt; (4/End)
     * </pre>
     */
    @Test
    public void shouldReturnFalseForSimpleStateModelMultiEnd() {
	TestState state4 = new TestStateImpl("4");
	TestTransition transition3 = new TestTransitionImpl("3", state4);
	TestState state3 = new TestStateImpl("3");
	TestTransition transition2 = new TestTransitionImpl("2", state3);
	TestState state2 = new TestStateImpl("2", transition2, transition3);
	TestTransition transition1 = new TestTransitionImpl("1", state2);
	TestState state1 = new TestStateImpl("1", transition1);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3,
		state4);
	assertFalse(StateModelVerfier.hasDeadEnds(stateModel));
    }

    /**
     * State model:
     * 
     * <pre>
     * (1) -1-&gt; (2) -2-&gt; (3/End)
     *   \
     *    \-3-&gt; (4)
     * </pre>
     */
    @Test
    public void shouldReturnTrueForSimpleDeadEndStateModel() {
	TestState state3 = new TestStateImpl("3");
	TestTransition transition2 = new TestTransitionImpl("2", state3);
	TestState state2 = new TestStateImpl("2", transition2);
	TestTransition transition1 = new TestTransitionImpl("1", state2);
	TestState state4 = new TestStateImpl("4");
	TestTransition transition3 = new TestTransitionImpl("3", state4);
	TestState state1 = new TestStateImpl("1", transition1, transition3);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertTrue(StateModelVerfier.hasDeadEnds(stateModel));
    }

    /**
     * State model:
     * 
     * <pre>
     * (1) -1-&gt; (2) -2-&gt; (3/End)
     *   \     /
     *    &lt;-3-/
     * </pre>
     */
    @Test
    public void shouldReturnFalseForSimpleStateModelWithCycle() {
	TestState state3 = new TestStateImpl("3");
	TestTransition transition2 = new TestTransitionImpl("2", state3);
	TestState state2 = new TestStateImpl("2", transition2);
	TestTransition transition1 = new TestTransitionImpl("1", state2);
	TestState state1 = new TestStateImpl("1", transition1);
	TestTransition transition3 = new TestTransitionImpl("3", state1);
	state2.addTransition(transition3);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertFalse(StateModelVerfier.hasDeadEnds(stateModel));
    }

    /**
     * State model:
     * 
     * <pre>
     * (1) ----------1-&gt; (2) -2-v (3/End)
     *   \ \
     *    \ &lt;-4--\
     *     \      \
     *      \-3-&gt; (4)
     * </pre>
     */
    @Test
    public void shouldReturnFalseForStateModelWithCycle() {
	TestState state3 = new TestStateImpl("3");
	TestTransition transition2 = new TestTransitionImpl("2", state3);
	TestState state2 = new TestStateImpl("2", transition2);
	TestTransition transition1 = new TestTransitionImpl("1", state2);
	TestState state4 = new TestStateImpl("4");
	TestTransition transition3 = new TestTransitionImpl("3", state4);
	TestState state1 = new TestStateImpl("1", transition1, transition3);
	TestTransition transition4 = new TestTransitionImpl("4", state1);
	state4.addTransition(transition4);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertFalse(StateModelVerfier.hasDeadEnds(stateModel));
    }

    /**
     * State model:
     * 
     * <pre>
     * (1) ----------1-&gt; (2) -2-&gt; (3/End)
     *   \ 
     *    \          &lt;-5--\
     *     \        /       \
     *      \-3-&gt; (4) -4-&gt; (5)
     * </pre>
     */
    @Test
    public void shouldReturnTrueForStateModelWithCycle() {
	TestState state3 = new TestStateImpl("3");
	TestTransition transition2 = new TestTransitionImpl("2", state3);
	TestState state2 = new TestStateImpl("2", transition2);
	TestTransition transition1 = new TestTransitionImpl("1", state2);
	TestState state5 = new TestStateImpl("5");
	TestTransition transition4 = new TestTransitionImpl("4", state5);
	TestState state4 = new TestStateImpl("4", transition4);
	TestTransition transition3 = new TestTransitionImpl("3", state4);
	TestState state1 = new TestStateImpl("1", transition1, transition3);
	TestTransition transition5 = new TestTransitionImpl("5", state4);
	state5.addTransition(transition5);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertTrue(StateModelVerfier.hasDeadEnds(stateModel));
    }

}
