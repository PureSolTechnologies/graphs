package com.puresoltechnologies.trees;

import java.util.Stack;

import com.puresoltechnologies.commons.misc.PeekingIterator;

public class BinarySearchTreeIterator<N extends BinaryTreeNode<N, Key, Value>, Key extends Comparable<Key>, Value>
	implements PeekingIterator<N> {

    private final N root;
    private Stack<N> rightSideToBeVisited = new Stack<>();

    public BinarySearchTreeIterator(N root) {
	super();
	this.root = root;
	N currentNode = this.root;
	while (currentNode != null) {
	    rightSideToBeVisited.push(currentNode);
	    currentNode = currentNode.getLeft();
	}
    }

    @Override
    public boolean hasNext() {
	return !rightSideToBeVisited.isEmpty();
    }

    @Override
    public N next() {
	N result = rightSideToBeVisited.pop();
	if (result.getRight() != null) {
	    N currentNode = result.getRight();
	    while (currentNode != null) {
		rightSideToBeVisited.push(currentNode);
		currentNode = currentNode.getLeft();
	    }
	}
	return result;
    }

    @Override
    public N peek() {
	if (rightSideToBeVisited.isEmpty()) {
	    return null;
	}
	return rightSideToBeVisited.peek();
    }

}
