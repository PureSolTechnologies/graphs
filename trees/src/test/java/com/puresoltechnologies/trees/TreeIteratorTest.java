package com.puresoltechnologies.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.trees.TreeIterator;

public class TreeIteratorTest {

	@Test
	public void testInstance() {
		TestTreeNodeImpl tree = new TestTreeNodeImpl("Root");
		assertNotNull(new TreeIterator<TestTreeNodeImpl>(tree));
	}

	@Test
	public void testInitValues() {
		TestTreeNodeImpl tree = new TestTreeNodeImpl("Root");
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		assertSame(tree, iterator.getCurrentNode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullTree() {
		new TreeIterator<TestTreeNodeImpl>(null);
	}

	@Test
	public void testGotoStart() {
		TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		assertEquals("root", iterator.getCurrentNode().getName());
		iterator.gotoStart();
		assertEquals("root", iterator.getCurrentNode().getName());
	}

	@Test
	public void testGotoStartPart() {
		TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree().getChildren().get(1);
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		assertEquals("n2", iterator.getCurrentNode().getName());
		iterator.gotoStart();
		assertEquals("n2", iterator.getCurrentNode().getName());
	}

	@Test
	public void testGotoEnd() {
		TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		assertEquals("root", iterator.getCurrentNode().getName());
		iterator.gotoEnd();
		assertEquals("n33", iterator.getCurrentNode().getName());
	}

	@Test
	public void testGotoEndPart() {
		TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree().getChildren().get(1);
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		assertEquals("n2", iterator.getCurrentNode().getName());
		iterator.gotoEnd();
		assertEquals("n23", iterator.getCurrentNode().getName());
	}

	@Test
	public void testGoForward() {
		TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		assertEquals("root", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n1", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n11", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n12", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n13", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n2", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n21", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n22", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n23", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n3", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n31", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n32", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n33", iterator.getCurrentNode().getName());
		assertFalse(iterator.goForward());
	}

	@Test
	public void testGoForwardPart() {
		TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree().getChildren().get(1);
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		assertEquals("n2", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n21", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n22", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n23", iterator.getCurrentNode().getName());
		assertFalse(iterator.goForward());
	}

	@Test
	public void testGoBackward() {
		TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		iterator.gotoEnd();
		assertEquals("n33", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n32", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n31", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n3", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n23", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n22", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n21", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n2", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n13", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n12", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n11", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n1", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("root", iterator.getCurrentNode().getName());
		assertFalse(iterator.goBackward());
	}

	@Test
	public void testGoBackwardPart() {
		TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree().getChildren().get(1);
		TreeIterator<TestTreeNodeImpl> iterator = new TreeIterator<TestTreeNodeImpl>(tree);
		iterator.gotoEnd();
		assertEquals("n23", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n22", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n21", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n2", iterator.getCurrentNode().getName());
		assertFalse(iterator.goBackward());
	}
}
