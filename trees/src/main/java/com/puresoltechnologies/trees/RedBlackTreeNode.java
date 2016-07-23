package com.puresoltechnologies.trees;

import java.util.List;
import java.util.Set;

public class RedBlackTreeNode<Key extends Comparable<Key>, Value> implements TreeNode<RedBlackTreeNode<Key, Value>> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private Key key; // key
    private Value value; // associated data
    private RedBlackTreeNode<Key, Value> left, right; // links to left and right
						      // subtrees
    private boolean color; // color of parent link
    private int size; // subtree count

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

    public Key getKey() {
	return key;
    }

    public Value getValue() {
	return value;
    }

    public void setValue(Value value) {
	this.value = value;
    }

    public RedBlackTreeNode<Key, Value> getLeft() {
	return left;
    }

    public void setLeft(RedBlackTreeNode<Key, Value> left) {
	this.left = left;
    }

    public RedBlackTreeNode<Key, Value> getRight() {
	return right;
    }

    public void setRight(RedBlackTreeNode<Key, Value> right) {
	this.right = right;
    }

    @Override
    public Set<TreeLink<RedBlackTreeNode<Key, Value>>> getEdges() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public RedBlackTreeNode<Key, Value> getParent() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean hasChildren() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public List<RedBlackTreeNode<Key, Value>> getChildren() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return null;
    }

    public boolean getColor() {
	return color;
    }

    public void setColor(boolean color) {
	this.color = color;
    }

    public boolean isRed() {
	return color == RED;
    }

    public int getSize() {
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }
}
