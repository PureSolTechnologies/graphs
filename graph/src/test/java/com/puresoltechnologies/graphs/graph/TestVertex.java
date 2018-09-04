package com.puresoltechnologies.graphs.graph;

import com.puresoltechnologies.graphs.graph.Vertex;

/**
 * This is an generic free interface of {@link Vertex} for testing to get the
 * tests clean of warning messages.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface TestVertex extends Vertex<TestVertex, TestEdge> {

	public void addEdge(TestVertex targetVertex);

	void addEdge(TestEdge testEdgeImpl);

}
