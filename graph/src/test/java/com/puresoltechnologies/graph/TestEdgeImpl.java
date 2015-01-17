package com.puresoltechnologies.graph;

class TestEdgeImpl implements TestEdge {

    private final TestVertex startVertex;
    private final TestVertex targetVertex;

    public TestEdgeImpl(TestVertex startVertex, TestVertex targetVertex) {
	super();
	this.startVertex = startVertex;
	this.targetVertex = targetVertex;
	targetVertex.addEdge(this);
    }

    @Override
    public Pair<TestVertex> getVertices() {
	return new Pair<TestVertex>(startVertex, targetVertex);
    }
}
