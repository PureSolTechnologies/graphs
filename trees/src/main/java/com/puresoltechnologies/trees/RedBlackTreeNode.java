package com.puresoltechnologies.trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedBlackTreeNode<Key extends Comparable<Key>, Value>
	implements BinaryTreeNode<RedBlackTreeNode<Key, Value>, Key, Value> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private Key key;
    private Value value;
    private RedBlackTreeNode<Key, Value> parent;
    private RedBlackTreeNode<Key, Value> left;
    private RedBlackTreeNode<Key, Value> right;
    private boolean color;
    private int size;

    public RedBlackTreeNode(Key key, Value value, boolean color, int size) {
	super();
	this.key = key;
	this.value = value;
	this.color = color;
	this.size = size;
    }

    public void setKey(Key key) {
	this.key = key;
    }

    @Override
    public Key getKey() {
	return key;
    }

    @Override
    public Value getValue() {
	return value;
    }

    public void setValue(Value value) {
	this.value = value;
    }

    @Override
    public RedBlackTreeNode<Key, Value> getLeft() {
	return left;
    }

    public void setLeft(RedBlackTreeNode<Key, Value> left) {
	this.left = left;
    }

    @Override
    public RedBlackTreeNode<Key, Value> getRight() {
	return right;
    }

    public void setRight(RedBlackTreeNode<Key, Value> right) {
	this.right = right;
    }

    @Override
    public Set<TreeLink<RedBlackTreeNode<Key, Value>>> getEdges() {
	Set<TreeLink<RedBlackTreeNode<Key, Value>>> edges = new HashSet<>();
	if (parent != null) {
	    edges.add(new TreeLink<>(parent, this));
	}
	if (left != null) {
	    edges.add(new TreeLink<>(this, left));
	}
	if (right != null) {
	    edges.add(new TreeLink<>(this, right));
	}
	return edges;
    }

    @Override
    public RedBlackTreeNode<Key, Value> getParent() {
	return parent;
    }

    public void setParent(RedBlackTreeNode<Key, Value> parent) {
	this.parent = parent;
    }

    @Override
    public boolean hasChildren() {
	return (left != null) || (right != null);
    }

    @Override
    public List<RedBlackTreeNode<Key, Value>> getChildren() {
	List<RedBlackTreeNode<Key, Value>> list = new ArrayList<>();
	if (left != null) {
	    list.add(left);
	}
	if (right != null) {
	    list.add(right);
	}
	return list;
    }

    @Override
    public String getName() {
	return key.toString();
    }

    public boolean getColor() {
	return color;
    }

    public void setColor(boolean color) {
	this.color = color;
    }

    public int getSize() {
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }

    @Override
    public String toString() {
	return key.toString() + "=" + value.toString();
    }
}
