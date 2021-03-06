package com.puresoltechnologies.graphs.trees;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.streaming.iterators.StreamIterator;

/**
 * This is a base implementation of a binary search tree.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <N>
 *            is the actual node type which needs to implement the
 *            {@link BinaryTreeNode} interface.
 * @param <Key>
 *            is the type of the key wich needs to implement {@link Comparable}
 * @param <Value>
 *            is the associated value of the node.
 */
public abstract class BinarySearchTree<N extends BinaryTreeNode<N, Key, Value>, Key extends Comparable<Key>, Value>
	implements Tree<N>, Iterable<N> {

    protected N root;

    public void clear() {
	root = null;
    }

    @Override
    public N getRootNode() {
	return root;
    }

    @Override
    public Set<N> getVertices() {
	Set<N> vertices = new HashSet<>();
	getVertices(vertices, root);
	return vertices;
    }

    private void getVertices(Set<N> vertices, N node) {
	vertices.add(node);
	for (N child : node.getChildren()) {
	    getVertices(vertices, child);
	}
    }

    /**
     * Checks whether the tree is empty.
     * 
     * @return <code>true</code> is returned in case the tree is empty.
     *         <code>false</code> is returned otherwise.
     */
    public boolean isEmpty() {
	return root == null;
    }

    @Override
    public StreamIterator<N> iterator() {
	return new BinarySearchTreeIterator<>(root);
    }

    public StreamIterator<N> iterator(Key start, Key end) {
	return new BinarySearchTreeIterator<>(root, start, end);
    }

    /**
     * Returns the value associated with the given key. The retrieval is done by
     * normal recursive binary search.
     * 
     * @param key
     *                the key
     * @return the value associated with the given key if the key is in the symbol
     *         table and <code>null</code> if the key is not in the symbol table
     * @throws NullPointerException
     *                                  if <code>key</code> is <code>null</code>
     */
    public Value get(Key key) {
	return get(root, key);
    }

    private Value get(N x, Key key) {
	while (x != null) {
	    int cmp = key.compareTo(x.getKey());
	    if (cmp < 0)
		x = x.getLeft();
	    else if (cmp > 0)
		x = x.getRight();
	    else
		return x.getValue();
	}
	return null;
    }

    /**
     * Checks whether a key exists in the tree.
     * 
     * @param key
     *                is the key to be looked for.
     * @return <code>true</code> if this symbol table contains <code>key</code> and
     *         <code>false</code> otherwise
     * @throws NullPointerException
     *                                  if <code>key</code> is <code>null</code>
     */
    public boolean contains(Key key) {
	return get(key) != null;
    }

    /**
     * This method is used to put a new node into the tree. The actual insertion is
     * done by a specialized method in an actual implementation.
     * 
     * @param key
     *                  is the key of the new node.
     * @param value
     *                  is the value of the new node.
     */
    public abstract void put(Key key, Value value);

    /**
     * Removes the specified key and its associated value from tree. The actual
     * deletion is done by a specialized method in an actual implementation.
     *
     * @param key
     *                is the key of the node to be removed.
     * @throws NullPointerException
     *                                  if <code>key</code> is <code>null</code>
     */
    public abstract void delete(Key key);

}
