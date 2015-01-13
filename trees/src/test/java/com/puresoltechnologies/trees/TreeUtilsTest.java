package com.puresoltechnologies.trees;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.trees.TreePrinter;
import com.puresoltechnologies.trees.TreeUtils;

public class TreeUtilsTest {

    @Test
    public void testCountNodes() {
	TreeImpl tree = TreeImpl.getSampleTree();
	new TreePrinter(System.out).println(tree);
	int count = TreeUtils.countNodes(tree);
	assertEquals(13, count);
    }
}
