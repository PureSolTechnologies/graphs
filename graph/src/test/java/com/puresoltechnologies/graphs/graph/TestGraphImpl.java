package com.puresoltechnologies.graphs.graph;

import java.util.HashSet;
import java.util.Set;

public class TestGraphImpl implements TestGraph {

    private final Set<TestVertex> vertices = new HashSet<>();

    public TestGraphImpl() {
    }

    @Override
    public Set<TestVertex> getVertices() {
	return vertices;
    }

    @Override
    public TestVertex createVertex() {
	TestVertexImpl vertex = new TestVertexImpl();
	vertices.add(vertex);
	return vertex;
    }
}
