package com.puresoltechnologies.graphs.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TreeUtilsTest {

    @Test
    public void testCountNodes() {
	TestTreeNodeImpl tree = TestTreeNodeImpl.getSampleTree();
	new TreePrinter(System.out).println(tree);
	int count = TreeUtils.countNodes(tree);
	assertEquals(13, count);
    }
}
