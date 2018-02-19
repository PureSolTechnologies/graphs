package com.puresoltechnologies.graphs.trees;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.graphs.trees.TreePrinter;
import com.puresoltechnologies.graphs.trees.TreeUtils;

public class TreeUtilsTest {

    @Test
    public void testCountNodes() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	new TreePrinter(System.out).println(tree);
	int count = TreeUtils.countNodes(tree);
	assertEquals(13, count);
    }
}
