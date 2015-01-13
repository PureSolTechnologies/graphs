package com.puresoltechnologies.statemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.puresoltechnologies.graph.GraphVerfier;

public class StateModelVerfierHasDeadEndsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNoEndStates() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("The model has no end states.");
	TestStateModel stateModel = mock(TestStateModel.class);
	when(stateModel.getEndStates()).thenReturn(new HashSet<>());
	GraphVerfier.hasDeadEnds(stateModel, stateModel.getStartState(),
		stateModel.getEndStates());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenEndStatesAreNull() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("The model has no end states.");
	TestStateModel stateModel = mock(TestStateModel.class);
	when(stateModel.getEndStates()).thenReturn(null);
	GraphVerfier.hasDeadEnds(stateModel, stateModel.getStartState(),
		stateModel.getEndStates());
    }

    @Test
    public void shouldReturnFalseForSingleStateModel() {
	TestState state1 = new TestStateImpl("1");
	TestStateModel stateModel = new TestStateModelImpl(state1, state1);

	assertFalse(GraphVerfier.hasDeadEnds(stateModel,
		stateModel.getStartState(), stateModel.getEndStates()));
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
	TestState state1 = new TestStateImpl("1");
	TestState state2 = new TestStateImpl("2");
	TestState state3 = new TestStateImpl("3");
	state1.addTransition("1", state2);
	state2.addTransition("2", state3);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertFalse(GraphVerfier.hasDeadEnds(stateModel,
		stateModel.getStartState(), stateModel.getEndStates()));
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
	TestState state1 = new TestStateImpl("1");
	TestState state2 = new TestStateImpl("2");
	TestState state3 = new TestStateImpl("3");
	TestState state4 = new TestStateImpl("4");
	state1.addTransition("1", state2);
	state2.addTransition("2", state3);
	state2.addTransition("3", state4);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3,
		state4);
	assertFalse(GraphVerfier.hasDeadEnds(stateModel,
		stateModel.getStartState(), stateModel.getEndStates()));
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
	TestState state1 = new TestStateImpl("1");
	TestState state2 = new TestStateImpl("2");
	TestState state3 = new TestStateImpl("3");
	TestState state4 = new TestStateImpl("4");
	state1.addTransition("1", state2);
	state2.addTransition("2", state3);
	state1.addTransition("3", state4);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertTrue(GraphVerfier.hasDeadEnds(stateModel,
		stateModel.getStartState(), stateModel.getEndStates()));
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
	TestState state1 = new TestStateImpl("1");
	TestState state2 = new TestStateImpl("2");
	TestState state3 = new TestStateImpl("3");
	state1.addTransition("1", state2);
	state2.addTransition("2", state3);
	state2.addTransition("3", state1);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertFalse(GraphVerfier.hasDeadEnds(stateModel,
		stateModel.getStartState(), stateModel.getEndStates()));
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
	TestState state1 = new TestStateImpl("1");
	TestState state2 = new TestStateImpl("2");
	TestState state3 = new TestStateImpl("3");
	TestState state4 = new TestStateImpl("4");
	state1.addTransition("1", state2);
	state2.addTransition("2", state3);
	state1.addTransition("3", state4);
	state4.addTransition("4", state1);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertFalse(GraphVerfier.hasDeadEnds(stateModel,
		stateModel.getStartState(), stateModel.getEndStates()));
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
	TestState state1 = new TestStateImpl("1");
	TestState state2 = new TestStateImpl("2");
	TestState state3 = new TestStateImpl("3");
	TestState state4 = new TestStateImpl("4");
	TestState state5 = new TestStateImpl("5");
	state1.addTransition("1", state2);
	state2.addTransition("2", state3);
	state1.addTransition("3", state4);
	state4.addTransition("4", state5);
	state5.addTransition("5", state4);

	TestStateModel stateModel = new TestStateModelImpl(state1, state3);
	assertTrue(GraphVerfier.hasDeadEnds(stateModel,
		stateModel.getStartState(), stateModel.getEndStates()));
    }

}
