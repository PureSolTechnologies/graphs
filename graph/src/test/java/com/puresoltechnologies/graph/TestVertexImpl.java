package com.puresoltechnologies.graph;

import java.util.HashSet;
import java.util.Set;

public class TestVertexImpl implements TestVertex {

    private final Set<TestEdge> edges = new HashSet<>();

    TestVertexImpl() {
	super();
    }

    @Override
    public Set<TestEdge> getEdges() {
	return edges;
    }

    @Override
    public void addEdge(TestVertex targetVertex) {
	edges.add(new TestEdgeImpl(this, targetVertex));
    }

    @Override
    public void addEdge(TestEdge edge) {
	edges.add(edge);
    }
}
