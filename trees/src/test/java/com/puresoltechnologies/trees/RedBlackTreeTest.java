package com.puresoltechnologies.trees;

import org.junit.Test;

public class RedBlackTreeTest {

    @Test
    public void test() {
	RedBlackTree<String, Integer> tree = new RedBlackTree<>();
	tree.put("root", 1);
	tree.put("a", 1);
	tree.put("b", 1);
	tree.put("c", 1);
	tree.put("d", 1);
	tree.put("e", 1);
	tree.put("f", 1);
	tree.put("g", 1);
	TreePrinter printer = new TreePrinter(System.out);
	printer.println(tree.getRootNode());
    }

}
