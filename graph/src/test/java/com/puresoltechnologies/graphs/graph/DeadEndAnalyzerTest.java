package com.puresoltechnologies.graphs.graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.puresoltechnologies.graphs.graph.DeadEndAnalyzer;

public class DeadEndAnalyzerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNoEndStates() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("The model has no end states.");
	TestGraph graph = mock(TestGraph.class);
	DeadEndAnalyzer
		.hasDeadEnds(graph, mock(TestVertex.class), new HashSet<>());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenEndStatesAreNull() {
	expectedException.expect(IllegalArgumentException.class);
	expectedException.expectMessage("The model has no end states.");
	TestGraph graph = mock(TestGraph.class);
	DeadEndAnalyzer.hasDeadEnds(graph, mock(TestVertex.class), null);
    }

    @Test
    public void shouldReturnFalseForSingleStateModel() {
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();

	assertFalse(DeadEndAnalyzer.hasDeadEnds(graph, vertex1, new HashSet<>(
		Arrays.asList(vertex1))));
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
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);

	assertFalse(DeadEndAnalyzer.hasDeadEnds(graph, vertex1, new HashSet<>(
		Arrays.asList(vertex3))));
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
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	TestVertex vertex4 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	vertex2.addEdge(vertex4);

	assertFalse(DeadEndAnalyzer.hasDeadEnds(graph, vertex1, new HashSet<>(
		Arrays.asList(vertex3, vertex4))));
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
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	TestVertex vertex4 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	vertex1.addEdge(vertex4);

	assertTrue(DeadEndAnalyzer.hasDeadEnds(graph, vertex1, new HashSet<>(
		Arrays.asList(vertex3))));
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
	TestGraph graph = new TestGraphImpl();
	TestVertex state1 = graph.createVertex();
	TestVertex state2 = graph.createVertex();
	TestVertex state3 = graph.createVertex();
	state1.addEdge(state2);
	state2.addEdge(state3);
	state2.addEdge(state1);

	assertFalse(DeadEndAnalyzer.hasDeadEnds(graph, state1, new HashSet<>(
		Arrays.asList(state3))));
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
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	TestVertex vertex4 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	vertex1.addEdge(vertex4);
	vertex4.addEdge(vertex1);

	assertFalse(DeadEndAnalyzer.hasDeadEnds(graph, vertex1, new HashSet<>(
		Arrays.asList(vertex3))));
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
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	TestVertex vertex4 = graph.createVertex();
	TestVertex vertex5 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	vertex1.addEdge(vertex4);
	vertex4.addEdge(vertex5);
	vertex5.addEdge(vertex4);

	assertTrue(DeadEndAnalyzer.hasDeadEnds(graph, vertex1, new HashSet<>(
		Arrays.asList(vertex3))));
    }

}
