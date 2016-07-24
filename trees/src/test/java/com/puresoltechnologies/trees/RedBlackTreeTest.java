package com.puresoltechnologies.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class RedBlackTreeTest {

    @Test
    public void testWebExample() {
	RedBlackTree<String, Integer> tree = new RedBlackTree<>();
	tree.put("S", 0);
	tree.put("E", 1);
	tree.put("A", 2);
	tree.put("R", 3);
	tree.put("C", 4);
	tree.put("H", 5);
	tree.put("E", 6);
	tree.put("X", 7);
	tree.put("A", 8);
	tree.put("M", 9);
	tree.put("P", 10);
	tree.put("L", 11);
	tree.put("E", 12);

	StringBuffer buffer = new StringBuffer();
	for (String key : tree.keys()) {
	    if (buffer.length() > 0) {
		buffer.append(';');
	    }
	    buffer.append(key);
	    buffer.append("=");
	    buffer.append(tree.get(key));
	}
	assertEquals("A=8;C=4;E=12;H=5;L=11;M=9;P=10;R=3;S=0;X=7", buffer.toString());
	assertTrue(tree.check());

	TreePrinter printer = new TreePrinter(System.out);
	printer.println(tree.getRootNode());
    }

    @Test
    public void test() {
	List<Integer> numbers = new ArrayList<>();
	for (int i = 0; i < 100; ++i) {
	    numbers.add(i);
	}
	Collections.shuffle(numbers, new Random(0x12345678));
	RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
	for (int number : numbers) {
	    tree.put(number, number * 10);
	}

	TreePrinter printer = new TreePrinter(System.out);
	printer.println(tree.getRootNode());

    }

}