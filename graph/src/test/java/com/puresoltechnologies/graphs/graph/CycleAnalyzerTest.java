package com.puresoltechnologies.graphs.graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CycleAnalyzerTest {

    /**
     * Graph:
     * 
     * <pre>
     * 1 -&gt; 2 -&gt; 3
     * </pre>
     */
    @Test
    public void shouldReturnFalseForSingleVertexGraph() {
	TestGraph graph = new TestGraphImpl();
	graph.createVertex();
	assertFalse(CycleAnalyzer.hasCycles(graph, true));
	assertFalse(CycleAnalyzer.hasCycles(graph, false));
    }

    /**
     * Graph:
     * 
     * <pre>
     * 1 -&gt; 2 -&gt; 3
     * </pre>
     */
    @Test
    public void shouldReturnFalse() {
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	assertFalse(CycleAnalyzer.hasCycles(graph, true));
	assertFalse(CycleAnalyzer.hasCycles(graph, false));
    }

    /**
     * Graph:
     * 
     * <pre>
     * 1 -&gt; 2 -&gt; 3
     *  \             / 
     *   \&lt;---------/
     * </pre>
     */
    @Test
    public void shouldReturnTrue() {
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	vertex3.addEdge(vertex1);
	assertTrue(CycleAnalyzer.hasCycles(graph, false));
	assertTrue(CycleAnalyzer.hasCycles(graph, true));
    }

    /**
     * Graph:
     * 
     * <pre>
     * 1 -&gt; 2 -&gt; 3
     *  \             / 
     *   \---------&gt;/
     * </pre>
     */
    @Test
    public void shouldReturnTrueAndFalseDependingOnIsDirected() {
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	vertex1.addEdge(vertex3);
	assertTrue(CycleAnalyzer.hasCycles(graph, false));
	assertFalse(CycleAnalyzer.hasCycles(graph, true));
    }

    /**
     * Graph:
     * 
     * <pre>
     * 1 -&gt; 2 -&gt; 3
     *  \             / 
     *   \&lt;---------/
     *   
     * 4 -&gt; 5
     * </pre>
     */
    @Test
    public void shouldReturnTrueAndFalseDependingOnDisconnection() {
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	TestVertex vertex4 = graph.createVertex();
	TestVertex vertex5 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	vertex3.addEdge(vertex1);
	vertex4.addEdge(vertex5);
	assertTrue(CycleAnalyzer.hasCycles(graph, false));
	assertTrue(CycleAnalyzer.hasCycles(graph, true));

	assertTrue(CycleAnalyzer.hasCycles(graph, vertex1, false));
	assertTrue(CycleAnalyzer.hasCycles(graph, vertex1, true));

	assertFalse(CycleAnalyzer.hasCycles(graph, vertex4, false));
	assertFalse(CycleAnalyzer.hasCycles(graph, vertex4, true));
    }

}
