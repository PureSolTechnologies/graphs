package com.puresoltechnologies.trees;

/**
 * This is a simple tree walker for all trees implementing the tree interface.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <N>
 *            is the actual tree type.
 */
public class TreeWalker<N extends TreeNode<N>> {

    public static <N extends TreeNode<N>> void walk(TreeVisitor<N> visitor,
	    N tree) {
	new TreeWalker<N>(tree).walk(visitor);
    }

    public static <N extends TreeNode<N>> void walkBackward(
	    TreeVisitor<N> visitor, N tree) {
	new TreeWalker<N>(tree).walkBackward(visitor);
    }

    private final N tree;

    public TreeWalker(N tree) {
	super();
	if (tree == null) {
	    throw new IllegalArgumentException(
		    "Tree object must not be null. There is nothing to walk on otherwise.");
	}
	this.tree = tree;
    }

    /**
     * @return the tree
     */
    public N getTree() {
	return tree;
    }

    /**
     * This method is used to start the walking process. The visitor is
     * specified which is called every time a new node is reached.
     * 
     * @param treeVisitor
     *            is the visitor to trigger for each node visiting.
     */
    public void walk(TreeVisitor<N> treeVisitor) {
	if (tree != null) {
	    walk(tree, treeVisitor);
	}
    }

    /**
     * This is the recursive part of the walk method.
     * 
     * @param tree
     * @param walkerClient
     * @return
     */
    private WalkingAction walk(N tree, TreeVisitor<N> walkerClient) {
	WalkingAction action = walkerClient.visit(tree);
	if (action == WalkingAction.ABORT) {
	    return WalkingAction.ABORT;
	} else if (action == WalkingAction.LEAVE_BRANCH) {
	    return WalkingAction.PROCEED;
	}
	for (N child : tree.getChildren()) {
	    if (walk(child, walkerClient) == WalkingAction.ABORT) {
		return WalkingAction.ABORT;
	    }
	}
	return WalkingAction.PROCEED;
    }

    /**
     * This method is used to start the walking process in an reverse order. The
     * visitor is specified which is called every time a new node is reached.
     * 
     * @param treeVisitor
     *            is the visitor to trigger for each node visiting.
     */
    public void walkBackward(TreeVisitor<N> treeVisitor) {
	if (tree != null) {
	    walkBackward(tree, treeVisitor);
	}
    }

    private WalkingAction walkBackward(N tree, TreeVisitor<N> walkerClient) {
	for (int id = tree.getChildren().size() - 1; id >= 0; id--) {
	    N child = tree.getChildren().get(id);
	    WalkingAction action = walkBackward(child, walkerClient);
	    if (action == WalkingAction.ABORT) {
		return WalkingAction.ABORT;
	    } else if (action == WalkingAction.LEAVE_BRANCH) {
		return WalkingAction.PROCEED;
	    }
	}
	return walkerClient.visit(tree);
    }
}
