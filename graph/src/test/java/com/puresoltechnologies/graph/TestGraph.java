package com.puresoltechnologies.graph;

/**
 * This is an generic free interface of {@link StateModel} for testing to get
 * the tests clean of warning messages.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface TestGraph extends Graph<TestVertex, TestEdge> {

    public TestVertex createVertex();

}
