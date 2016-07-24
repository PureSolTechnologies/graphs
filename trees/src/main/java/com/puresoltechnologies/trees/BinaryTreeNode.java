package com.puresoltechnologies.trees;

/**
 * This interface is an extension
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <N>
 * @param <Key>
 * @param <Value>
 */
public interface BinaryTreeNode<N extends BinaryTreeNode<N, Key, Value>, Key extends Comparable<Key>, Value>
	extends TreeNode<N> {

    /**
     * This method returns the key of the node which is used to compare and sort
     * the nodes within the tree.
     * 
     * @return The key is returned.
     */
    public Key getKey();

    /**
     * This method returns the value of the node.
     * 
     * @return The value is returned.
     */
    public Value getValue();

    /**
     * Returns the left node of the binary tree node. Is is assumed in a binary
     * tree, that the left node's key is smaller than the parent node.
     * 
     * @return A node is returned.
     */
    public N getLeft();

    /**
     * Returns the right node of the binary tree node. Is is assumed in a binary
     * tree, that the right node's key is greater than the parent node.
     * 
     * @return A node is returned.
     */
    public N getRight();

}
