package com.puresoltechnologies.graphs.graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.graphs.graph.SubgraphAnalyzer;

public class SubgraphAnalyzerTest {

    @Test
    public void shouldReturnFalseForEmptyGraph() {
	TestGraph graph = new TestGraphImpl();
	assertFalse(SubgraphAnalyzer.hasDisconnectedSubgraph(graph));
    }

    @Test
    public void shouldReturnFalseForSingleVertexGraph() {
	TestGraph graph = new TestGraphImpl();
	graph.createVertex();
	assertFalse(SubgraphAnalyzer.hasDisconnectedSubgraph(graph));
    }

    /**
     * <pre>
     * 1 -&gt; 2 - &gt; 3
     * </pre>
     */
    @Test
    public void shouldReturnFalseForSequence() {
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	assertFalse(SubgraphAnalyzer.hasDisconnectedSubgraph(graph));
    }

    /**
     * <pre>
     * 1 -&gt; 2 - &gt; 3
     * 4 -&gt; 5
     * </pre>
     */
    @Test
    public void shouldReturntrueFor2DisconnectedSequences() {
	TestGraph graph = new TestGraphImpl();
	TestVertex vertex1 = graph.createVertex();
	TestVertex vertex2 = graph.createVertex();
	TestVertex vertex3 = graph.createVertex();
	TestVertex vertex4 = graph.createVertex();
	TestVertex vertex5 = graph.createVertex();
	vertex1.addEdge(vertex2);
	vertex2.addEdge(vertex3);
	vertex4.addEdge(vertex5);
	assertTrue(SubgraphAnalyzer.hasDisconnectedSubgraph(graph));
    }
}
