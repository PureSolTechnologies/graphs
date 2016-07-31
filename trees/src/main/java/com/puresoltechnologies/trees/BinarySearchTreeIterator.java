package com.puresoltechnologies.trees;

import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTreeIterator<N extends BinaryTreeNode<N, Key, Value>, Key extends Comparable<Key>, Value>
	implements Iterator<N> {

    private final N root;
    private N currentNode;
    private Stack<N> rightSideToBeVisited = new Stack<>();

    public BinarySearchTreeIterator(N root) {
	super();
	this.root = root;
	currentNode = this.root;
	while (true) {
	    if (currentNode.getRight() != null) {
		rightSideToBeVisited.push(currentNode);
	    }
	    if (currentNode.getLeft() != null) {
		currentNode = currentNode.getLeft();
	    } else {
		break;
	    }
	}
    }

    @Override
    public boolean hasNext() {
	return currentNode != null;
    }

    @Override
    public N next() {
	N result = currentNode;

	if ((currentNode.getRight() != null) && (!rightSideToBeVisited.isEmpty())
		&& (rightSideToBeVisited.peek().equals(currentNode))) {
	    rightSideToBeVisited.pop();
	    currentNode = currentNode.getRight();
	    while (true) {
		if (currentNode.getRight() != null) {
		    rightSideToBeVisited.push(currentNode);
		}
		if (currentNode.getLeft() != null) {
		    currentNode = currentNode.getLeft();
		} else {
		    break;
		}
	    }
	} else {
	    N lastNode = currentNode;
	    currentNode = currentNode.getParent();
	    while ((currentNode != null) && (currentNode.getRight() != null)
		    && (currentNode.getRight().equals(lastNode))) {
		lastNode = currentNode;
		currentNode = currentNode.getParent();
	    }
	}
	return result;
    }

}
