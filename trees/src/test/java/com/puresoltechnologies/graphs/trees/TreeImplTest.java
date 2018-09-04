package com.puresoltechnologies.graphs.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TreeImplTest {

    @Test
    public void testInstance() {
	assertNotNull(new TestTreeNodeImpl(null, "Name"));
	assertNotNull(new TestTreeNodeImpl("Name"));
    }

    @Test
    public void testInitValues() {
	TreeNode<TestTreeNodeImpl> tree = new TestTreeNodeImpl("Name");
	assertEquals("Name", tree.getName());
	assertNotNull(tree.getChildren());
	assertFalse(tree.hasChildren());
	assertEquals(0, tree.getChildren().size());
	assertNull(tree.getParent());
    }

    @Test
    public void testInitValues2() {
	TestTreeNodeImpl parent = new TestTreeNodeImpl("Parent");
	TestTreeNodeImpl tree = new TestTreeNodeImpl(parent, "Name");
	parent.addChild(tree);
	assertEquals("Name", tree.getName());
	assertNotNull(tree.getChildren());
	assertFalse(tree.hasChildren());
	assertEquals(0, tree.getChildren().size());
	assertSame(parent, tree.getParent());
    }

}
