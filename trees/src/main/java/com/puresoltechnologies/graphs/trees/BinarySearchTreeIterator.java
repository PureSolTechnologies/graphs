package com.puresoltechnologies.graphs.trees;

import java.util.Stack;

import com.puresoltechnologies.streaming.StreamIterator;

public class BinarySearchTreeIterator<N extends BinaryTreeNode<N, Key, Value>, Key extends Comparable<Key>, Value>
	implements StreamIterator<N> {

    private final N root;
    private final Key startKey;
    private final Key endKey;
    private Stack<N> stack = new Stack<>();

    public BinarySearchTreeIterator(N root) {
	super();
	this.root = root;
	this.startKey = null;
	this.endKey = null;
	N currentNode = this.root;
	while (currentNode != null) {
	    stack.push(currentNode);
	    currentNode = currentNode.getLeft();
	}
    }

    public BinarySearchTreeIterator(N root, Key startKey, Key endKey) {
	super();
	this.root = root;
	this.startKey = startKey;
	this.endKey = endKey;
	N currentNode = this.root;
	while ((currentNode != null) && (!currentNode.getKey().equals(startKey))) {
	    if ((startKey == null) || (currentNode.getKey().compareTo(startKey) > 0)) {
		stack.push(currentNode);
		currentNode = currentNode.getLeft();
	    } else {
		currentNode = currentNode.getRight();
	    }
	}
	if ((currentNode != null) && ((startKey == null) || (currentNode.getKey().compareTo(startKey) >= 0))) {
	    stack.push(currentNode);
	}
    }

    @Override
    public boolean hasNext() {
	if (stack.isEmpty()) {
	    return false;
	}
	if (endKey == null) {
	    return true;
	}
	return peek() != null;
    }

    @Override
    public N next() {
	N result = stack.pop();
	if (endKey != null) {
	    if (result.getKey().compareTo(endKey) > 0) {
		stack.clear();
		return null;
	    }
	}
	if (result.getRight() != null) {
	    N currentNode = result.getRight();
	    while (currentNode != null) {
		stack.push(currentNode);
		currentNode = currentNode.getLeft();
	    }
	}
	return result;
    }

    @Override
    public N peek() {
	if (stack.isEmpty()) {
	    return null;
	}
	N peeked = stack.peek();
	if (endKey != null) {
	    if (peeked.getKey().compareTo(endKey) > 0) {
		stack.clear();
		return null;
	    }
	}
	return peeked;
    }

}
