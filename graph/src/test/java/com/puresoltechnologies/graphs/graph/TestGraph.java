package com.puresoltechnologies.graphs.graph;

import com.puresoltechnologies.graphs.graph.Graph;

/**
 * This is an generic free interface of {@link Graph} for testing to get the
 * tests clean of warning messages.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface TestGraph extends Graph<TestVertex, TestEdge> {

	public TestVertex createVertex();

}
