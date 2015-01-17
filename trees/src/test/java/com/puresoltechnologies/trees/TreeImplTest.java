package com.puresoltechnologies.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.trees.TreeNode;

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
