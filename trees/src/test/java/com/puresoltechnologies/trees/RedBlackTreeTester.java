package com.puresoltechnologies.trees;

public class RedBlackTreeTester<Key extends Comparable<Key>, Value> {

    private final RedBlackTree<Key, Value> tree;
    private final RedBlackTreeNode<Key, Value> root;

    public RedBlackTreeTester(RedBlackTree<Key, Value> tree) {
	super();
	this.tree = tree;
	this.root = tree.getRootNode();
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
	if (!isParentConsistent()) {
	    System.out.println("Parents are inconsistent.");
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
	if (x.getSize() != tree.size(x.getLeft()) + tree.size(x.getRight()) + 1)
	    return false;
	return isSizeConsistent(x.getLeft()) && isSizeConsistent(x.getRight());
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
	for (int i = 0; i < root.getSize(); i++)
	    if (i != tree.rank(tree.select(i)))
		return false;
	for (Key key : tree.keys())
	    if (key.compareTo(tree.select(tree.rank(key))) != 0)
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
	if (tree.isRed(x.getRight()))
	    return false;
	if (x != root && tree.isRed(x) && tree.isRed(x.getLeft()))
	    return false;
	return is23(x.getLeft()) && is23(x.getRight());
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
	int black = 0; // number of black links on path from root to min
	RedBlackTreeNode<Key, Value> x = root;
	while (x != null) {
	    if (!tree.isRed(x))
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
	if (!tree.isRed(x))
	    black--;
	return isBalanced(x.getLeft(), black) && isBalanced(x.getRight(), black);
    }

    private boolean isParentConsistent() {
	return isParentConsistent(root);
    }

    private boolean isParentConsistent(RedBlackTreeNode<Key, Value> x) {
	if (x == null)
	    return true;
	RedBlackTreeNode<Key, Value> left = x.getLeft();
	if ((left != null) && (!left.getParent().equals(x))) {
	    return false;
	}
	RedBlackTreeNode<Key, Value> right = x.getRight();
	if ((right != null) && (!right.getParent().equals(x))) {
	    return false;
	}
	return isParentConsistent(x.getLeft()) && isParentConsistent(x.getRight());
    }
}
