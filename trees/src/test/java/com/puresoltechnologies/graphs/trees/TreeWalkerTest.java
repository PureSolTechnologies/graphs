package com.puresoltechnologies.graphs.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TreeWalkerTest {

    @Test
    public void testInstance() {
	assertNotNull(new TreeWalker<TestTreeNodeImpl>(TestTreeNodeImpl.getSampleTree()));
    }

    @Test
    public void testInitValues() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	TreeWalker<TestTreeNodeImpl> walker = new TreeWalker<TestTreeNodeImpl>(tree);
	assertSame(tree, walker.getTree());
    }

    @Test
    public void testCompleteWalk() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	TreeWalker<TestTreeNodeImpl> walker = new TreeWalker<TestTreeNodeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	walker.walk(visitor);
	assertEquals("rootn1n11n12n13n2n21n22n23n3n31n32n33", visitor.getNodeString());
    }

    @Test
    public void testAbortWalk() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	TreeWalker<TestTreeNodeImpl> walker = new TreeWalker<TestTreeNodeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	visitor.addAction("n2", WalkingAction.ABORT);
	walker.walk(visitor);
	assertEquals("rootn1n11n12n13n2", visitor.getNodeString());
    }

    @Test
    public void testLeaveBranchWalk() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	TreeWalker<TestTreeNodeImpl> walker = new TreeWalker<TestTreeNodeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	visitor.addAction("n2", WalkingAction.LEAVE_BRANCH);
	walker.walk(visitor);
	assertEquals("rootn1n11n12n13n2n3n31n32n33", visitor.getNodeString());
    }

    @Test
    public void testCompleteWalkBackward() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	TreeWalker<TestTreeNodeImpl> walker = new TreeWalker<TestTreeNodeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	walker.walkBackward(visitor);
	assertEquals("n33n32n31n3n23n22n21n2n13n12n11n1root", visitor.getNodeString());
    }

    @Test
    public void testAbortWalkBackward() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	TreeWalker<TestTreeNodeImpl> walker = new TreeWalker<TestTreeNodeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	visitor.addAction("n2", WalkingAction.ABORT);
	walker.walkBackward(visitor);
	assertEquals("n33n32n31n3n23n22n21n2", visitor.getNodeString());
    }

    @Test
    public void testLeaveBranchWalkBackward() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	TreeWalker<TestTreeNodeImpl> walker = new TreeWalker<TestTreeNodeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	visitor.addAction("n23", WalkingAction.LEAVE_BRANCH);
	walker.walkBackward(visitor);
	assertEquals("n33n32n31n3n23n13n12n11n1root", visitor.getNodeString());
    }
}
