package com.puresoltechnologies.trees;

import static com.puresoltechnologies.trees.RedBlackTreeNode.BLACK;
import static com.puresoltechnologies.trees.RedBlackTreeNode.RED;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * This is a RedBlackTree implementation based on {@link BinarySearchTree}.
 * 
 * @see http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> extends
	BinarySearchTree<RedBlackTreeNode<Key, Value>, Key, Value> implements Tree<RedBlackTreeNode<Key, Value>> {

    public RedBlackTree() {
	super();
    }

    /***************************************************************************
     * Node helper methods.
     ***************************************************************************/

    public int size() {
	return size(root);
    }

    // number of RedBlackTreeNode in subtree rooted at x; 0 if x is null
    private int size(RedBlackTreeNode<Key, Value> x) {
	if (x == null)
	    return 0;
	return x.getSize();
    }

    private boolean isRed(RedBlackTreeNode<Key, Value> x) {
	if (x == null) {
	    return false;
	}
	return x.getColor();
    }

    /***************************************************************************
     * Red-black tree insertion.
     ***************************************************************************/

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting
     * the old value with the new value if the symbol table already contains the
     * specified key. Deletes the specified key (and its associated value) from
     * this symbol table if the specified value is <tt>null</tt>.
     *
     * @param key
     *            the key
     * @param value
     *            the value
     */
    @Override
    public void put(Key key, Value value) {
	if (value == null) {
	    delete(key);
	    return;
	}
	root = put(root, key, value);
	root.setColor(BLACK);
    }

    // insert the key-value pair in the subtree rooted at h
    private RedBlackTreeNode<Key, Value> put(RedBlackTreeNode<Key, Value> h, Key key, Value value) {
	if (h == null)
	    return new RedBlackTreeNode<>(key, value, RED, 1);

	int cmp = key.compareTo(h.getKey());
	if (cmp < 0)
	    h.setLeft(put(h.getLeft(), key, value));
	else if (cmp > 0)
	    h.setRight(put(h.getRight(), key, value));
	else
	    h.setValue(value);

	// fix-up any right-leaning links
	if (isRed(h.getRight()) && !isRed(h.getLeft()))
	    h = rotateLeft(h);
	if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft()))
	    h = rotateRight(h);
	if (isRed(h.getLeft()) && isRed(h.getRight()))
	    flipColors(h);
	h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);

	return h;
    }

    /***************************************************************************
     * Red-black tree deletion.
     ***************************************************************************/

    /**
     * Removes the smallest key and associated value from the symbol table.
     * 
     * @throws NoSuchElementException
     *             if the symbol table is empty
     */
    public void deleteMin() {
	if (isEmpty())
	    throw new NoSuchElementException("BST underflow");

	// if both children of root are black, set root to red
	if (!isRed(root.getLeft()) && !isRed(root.getRight()))
	    root.setColor(RED);

	root = deleteMin(root);
	if (!isEmpty())
	    root.setColor(BLACK);
	// assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private RedBlackTreeNode<Key, Value> deleteMin(RedBlackTreeNode<Key, Value> h) {
	if (h.getLeft() == null)
	    return null;

	if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
	    h = moveRedLeft(h);

	h.setLeft(deleteMin(h.getLeft()));
	return balance(h);
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     * 
     * @throws NoSuchElementException
     *             if the symbol table is empty
     */
    public void deleteMax() {
	if (isEmpty())
	    throw new NoSuchElementException("BST underflow");

	// if both children of root are black, set root to red
	if (!isRed(root.getLeft()) && !isRed(root.getRight()))
	    root.setColor(RED);

	root = deleteMax(root);
	if (!isEmpty()) {
	    root.setColor(BLACK);
	}
	// assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private RedBlackTreeNode<Key, Value> deleteMax(RedBlackTreeNode<Key, Value> h) {
	if (isRed(h.getLeft()))
	    h = rotateRight(h);

	if (h.getRight() == null)
	    return null;

	if (!isRed(h.getRight()) && !isRed(h.getRight().getLeft()))
	    h = moveRedRight(h);

	h.setRight(deleteMax(h.getRight()));

	return balance(h);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key
     *            the key
     */
    @Override
    public void delete(Key key) {
	// TODO check whether this needs to be checked!!
	if (!contains(key)) {
	    return;
	}

	// if both children of root are black, set root to red
	if (!isRed(root.getLeft()) && !isRed(root.getRight())) {
	    root.setColor(RED);
	}

	root = delete(root, key);
	if (!isEmpty())
	    root.setColor(BLACK);
	// assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private RedBlackTreeNode<Key, Value> delete(RedBlackTreeNode<Key, Value> h, Key key) {
	// assert get(h, key) != null;

	if (key.compareTo(h.getKey()) < 0) {
	    if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
		h = moveRedLeft(h);
	    h.setLeft(delete(h.getLeft(), key));
	} else {
	    if (isRed(h.getLeft()))
		h = rotateRight(h);
	    if (key.compareTo(h.getKey()) == 0 && (h.getRight() == null))
		return null;
	    if (!isRed(h.getRight()) && !isRed(h.getRight().getLeft()))
		h = moveRedRight(h);
	    if (key.compareTo(h.getKey()) == 0) {
		RedBlackTreeNode<Key, Value> x = min(h.getRight());
		h.setKey(x.getKey());
		h.setValue(x.getValue());
		// h.val = get(h.right, min(h.right).key);
		// h.key = min(h.right).key;
		h.setRight(deleteMin(h.getRight()));
	    } else
		h.setRight(delete(h.getRight(), key));
	}
	return balance(h);
    }

    /***************************************************************************
     * Red-black tree helper functions.
     ***************************************************************************/

    // make a left-leaning link lean to the right
    private RedBlackTreeNode<Key, Value> rotateRight(RedBlackTreeNode<Key, Value> h) {
	// assert (h != null) && h.getLeft().isRed();
	RedBlackTreeNode<Key, Value> x = h.getLeft();
	h.setLeft(x.getRight());
	x.setRight(h);
	x.setColor(x.getRight().getColor());
	x.getRight().setColor(RED);
	x.setSize(h.getSize());
	h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);
	return x;
    }

    // make a right-leaning link lean to the left
    private RedBlackTreeNode<Key, Value> rotateLeft(RedBlackTreeNode<Key, Value> h) {
	// assert (h != null) && h.getRight().isRed();
	RedBlackTreeNode<Key, Value> x = h.getRight();
	h.setRight(x.getLeft());
	x.setLeft(h);
	x.setColor(x.getLeft().getColor());
	x.getLeft().setColor(RED);
	x.setSize(h.getSize());
	h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);
	return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(RedBlackTreeNode<Key, Value> h) {
	// h must have opposite color of its two children
	// assert (h != null) && (h.left != null) && (h.right != null);
	// assert (!isRed(h) && h.getLeft().isRed() && h.getRight().isRed())
	// || (isRed(h) && !h.getLeft().isRed() && !h.getRight().isRed());
	h.setColor(!h.getColor());
	h.getLeft().setColor(!h.getLeft().getColor());
	h.getRight().setColor(!h.getRight().getColor());
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private RedBlackTreeNode<Key, Value> moveRedLeft(RedBlackTreeNode<Key, Value> h) {
	// assert (h != null);
	// assert isRed(h) && !h.getLeft().isRed() && !isRed(h.left.left);

	flipColors(h);
	if (isRed(h.getRight().getLeft())) {
	    h.setRight(rotateRight(h.getRight()));
	    h = rotateLeft(h);
	    flipColors(h);
	}
	return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private RedBlackTreeNode<Key, Value> moveRedRight(RedBlackTreeNode<Key, Value> h) {
	// assert (h != null);
	// assert isRed(h) && !h.getRight().isRed() && !isRed(h.right.left);
	flipColors(h);
	if (isRed(h.getLeft().getLeft())) {
	    h = rotateRight(h);
	    flipColors(h);
	}
	return h;
    }

    // restore red-black tree invariant
    private RedBlackTreeNode<Key, Value> balance(RedBlackTreeNode<Key, Value> h) {
	// assert (h != null);

	if (isRed(h.getRight()))
	    h = rotateLeft(h);
	if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft()))
	    h = rotateRight(h);
	if (isRed(h.getLeft()) && isRed(h.getRight()))
	    flipColors(h);

	h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);
	return h;
    }

    /***************************************************************************
     * Utility functions.
     ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     * 
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
	return height(root);
    }

    private int height(RedBlackTreeNode<Key, Value> x) {
	if (x == null)
	    return -1;
	return 1 + Math.max(height(x.getLeft()), height(x.getRight()));
    }

    /***************************************************************************
     * Ordered symbol table methods.
     ***************************************************************************/

    /**
     * Returns the smallest key in the symbol table.
     * 
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException
     *             if the symbol table is empty
     */
    public Key min() {
	if (isEmpty())
	    throw new NoSuchElementException("called min() with empty symbol table");
	return min(root).getKey();
    }

    // the smallest key in subtree rooted at x; null if no such key
    private RedBlackTreeNode<Key, Value> min(RedBlackTreeNode<Key, Value> x) {
	// assert x != null;
	if (x.getLeft() == null)
	    return x;
	else
	    return min(x.getLeft());
    }

    /**
     * Returns the largest key in the symbol table.
     * 
     * @return the largest key in the symbol table
     * @throws NoSuchElementException
     *             if the symbol table is empty
     */
    public Key max() {
	if (isEmpty())
	    throw new NoSuchElementException("called max() with empty symbol table");
	return max(root).getKey();
    }

    // the largest key in the subtree rooted at x; null if no such key
    private RedBlackTreeNode<Key, Value> max(RedBlackTreeNode<Key, Value> x) {
	// assert x != null;
	if (x.getRight() == null)
	    return x;
	else
	    return max(x.getRight());
    }

    /**
     * Returns the largest key in the symbol table less than or equal to
     * <tt>key</tt>.
     * 
     * @param key
     *            the key
     * @return the largest key in the symbol table less than or equal to
     *         <tt>key</tt>
     * @throws NoSuchElementException
     *             if there is no such key
     */
    public Key floor(Key key) {
	if (isEmpty())
	    throw new NoSuchElementException("called floor() with empty symbol table");
	RedBlackTreeNode<Key, Value> x = floor(root, key);
	if (x == null)
	    return null;
	else
	    return x.getKey();
    }

    // the largest key in the subtree rooted at x less than or equal to the
    // given key
    private RedBlackTreeNode<Key, Value> floor(RedBlackTreeNode<Key, Value> x, Key key) {
	if (x == null)
	    return null;
	int cmp = key.compareTo(x.getKey());
	if (cmp == 0)
	    return x;
	if (cmp < 0)
	    return floor(x.getLeft(), key);
	RedBlackTreeNode<Key, Value> t = floor(x.getRight(), key);
	if (t != null)
	    return t;
	else
	    return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to
     * <tt>key</tt>.
     * 
     * @param key
     *            the key
     * @return the smallest key in the symbol table greater than or equal to
     *         <tt>key</tt>
     * @throws NoSuchElementException
     *             if there is no such key
     */
    public Key ceiling(Key key) {
	if (isEmpty())
	    throw new NoSuchElementException("called ceiling() with empty symbol table");
	RedBlackTreeNode<Key, Value> x = ceiling(root, key);
	if (x == null)
	    return null;
	else
	    return x.getKey();
    }

    // the smallest key in the subtree rooted at x greater than or equal to the
    // given key
    private RedBlackTreeNode<Key, Value> ceiling(RedBlackTreeNode<Key, Value> x, Key key) {
	if (x == null)
	    return null;
	int cmp = key.compareTo(x.getKey());
	if (cmp == 0)
	    return x;
	if (cmp > 0)
	    return ceiling(x.getRight(), key);
	RedBlackTreeNode<Key, Value> t = ceiling(x.getLeft(), key);
	if (t != null)
	    return t;
	else
	    return x;
    }

    /**
     * Return the kth smallest key in the symbol table.
     * 
     * @param k
     *            the order statistic
     * @return the kth smallest key in the symbol table
     * @throws IllegalArgumentException
     *             unless <tt>k</tt> is between 0 and <em>N</em> &minus; 1
     */
    public Key select(int k) {
	if (k < 0 || k >= size(root))
	    throw new IllegalArgumentException();
	RedBlackTreeNode<Key, Value> x = select(root, k);
	return x.getKey();
    }

    // the key of rank k in the subtree rooted at x
    private RedBlackTreeNode<Key, Value> select(RedBlackTreeNode<Key, Value> x, int k) {
	// assert x != null;
	// assert k >= 0 && k < size(x);
	int t = size(x.getLeft());
	if (t > k)
	    return select(x.getLeft(), k);
	else if (t < k)
	    return select(x.getRight(), k - t - 1);
	else
	    return x;
    }

    /**
     * Return the number of keys in the symbol table strictly less than
     * <tt>key</tt>.
     * 
     * @param key
     *            the key
     * @return the number of keys in the symbol table strictly less than
     *         <tt>key</tt>
     */
    public int rank(Key key) {
	return rank(key, root);
    }

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, RedBlackTreeNode<Key, Value> x) {
	if (x == null)
	    return 0;
	int cmp = key.compareTo(x.getKey());
	if (cmp < 0)
	    return rank(key, x.getLeft());
	else if (cmp > 0)
	    return 1 + size(x.getLeft()) + rank(key, x.getRight());
	else
	    return size(x.getLeft());
    }

    /***************************************************************************
     * Range count and range search.
     ***************************************************************************/

    /**
     * Returns all keys in the symbol table as an <tt>Iterable</tt>. To iterate
     * over all of the keys in the symbol table named <tt>st</tt>, use the
     * foreach notation: <tt>for (Key key : st.keys())</tt>.
     * 
     * @return all keys in the sybol table as an <tt>Iterable</tt>
     */
    public Iterable<Key> keys() {
	if (isEmpty())
	    return new LinkedList<>();
	return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range, as an
     * <tt>Iterable</tt>.
     * 
     * @return all keys in the sybol table between <tt>lo</tt> (inclusive) and
     *         <tt>hi</tt> (exclusive) as an <tt>Iterable</tt>
     */
    public Iterable<Key> keys(Key lo, Key hi) {
	Queue<Key> queue = new LinkedList<>();
	// if (isEmpty() || lo.compareTo(hi) > 0) return queue;
	keys(root, queue, lo, hi);
	return queue;
    }

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(RedBlackTreeNode<Key, Value> x, Queue<Key> queue, Key lo, Key hi) {
	if (x == null)
	    return;
	int cmplo = lo.compareTo(x.getKey());
	int cmphi = hi.compareTo(x.getKey());
	if (cmplo < 0)
	    keys(x.getLeft(), queue, lo, hi);
	if (cmplo <= 0 && cmphi >= 0)
	    queue.offer(x.getKey());
	if (cmphi > 0)
	    keys(x.getRight(), queue, lo, hi);
    }

    /**
     * Returns the number of keys in the symbol table in the given range.
     * 
     * @return the number of keys in the symbol table between <tt>low</tt>
     *         (inclusive) and <tt>high</tt> (exclusive)
     */
    public int size(Key low, Key high) {
	if (low.compareTo(high) > 0)
	    return 0;
	if (contains(high))
	    return rank(high) - rank(low) + 1;
	else
	    return rank(high) - rank(low);
    }

    /***************************************************************************
     * Check integrity of red-black tree data structure.
     ***************************************************************************/
    public boolean check() {
	if (!isBST()) {
	    System.err.println("Not in symmetric order");
	    return false;
	}
	if (!isSizeConsistent()) {
	    System.err.println("Subtree counts not consistent");
	    return false;
	}
	if (!isRankConsistent()) {
	    System.err.println("Ranks not consistent");
	    return false;
	}
	if (!is23()) {
	    System.err.println("Not a 2-3 tree");
	    return false;
	}
	if (!isBalanced()) {
	    System.out.println("Not balanced");
	    return false;
	}
	return true;
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since
    // order is strict
    private boolean isBST() {
	return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(RedBlackTreeNode<Key, Value> x, Key min, Key max) {
	if (x == null)
	    return true;
	if (min != null && x.getKey().compareTo(min) <= 0)
	    return false;
	if (max != null && x.getKey().compareTo(max) >= 0)
	    return false;
	return isBST(x.getLeft(), min, x.getKey()) && isBST(x.getRight(), x.getKey(), max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
	return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(RedBlackTreeNode<Key, Value> x) {
	if (x == null)
	    return true;
	if (x.getSize() != size(x.getLeft()) + size(x.getRight()) + 1)
	    return false;
	return isSizeConsistent(x.getLeft()) && isSizeConsistent(x.getRight());
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
	for (int i = 0; i < size(); i++)
	    if (i != rank(select(i)))
		return false;
	for (Key key : keys())
	    if (key.compareTo(select(rank(key))) != 0)
		return false;
	return true;
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() {
	return is23(root);
    }

    private boolean is23(RedBlackTreeNode<Key, Value> x) {
	if (x == null)
	    return true;
	if (isRed(x.getRight()))
	    return false;
	if (x != root && isRed(x) && isRed(x.getLeft()))
	    return false;
	return is23(x.getLeft()) && is23(x.getRight());
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
	int black = 0; // number of black links on path from root to min
	RedBlackTreeNode<Key, Value> x = root;
	while (x != null) {
	    if (!isRed(x))
		black++;
	    x = x.getLeft();
	}
	return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black
    // links?
    private boolean isBalanced(RedBlackTreeNode<Key, Value> x, int black) {
	if (x == null)
	    return black == 0;
	if (!isRed(x))
	    black--;
	return isBalanced(x.getLeft(), black) && isBalanced(x.getRight(), black);
    }
}