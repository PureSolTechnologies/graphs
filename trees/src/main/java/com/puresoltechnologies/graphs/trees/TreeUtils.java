package com.puresoltechnologies.graphs.trees;

import java.util.List;

/**
 * This class contains helper method to work with trees.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreeUtils {

    /**
     * This method counts all nodes within a tree.
     * 
     * @param tree
     *            is a {@link TreeNode} object which nodes are to be calculated.
     * @param <N>
     *            is the actual tree implementation.
     * @return An integer is returned containing the number of nodes. If tree is
     *         <code>null</code> 0 is returned.
     */
    public static <N extends TreeNode<N>> int countNodes(N tree) {
	int result = 0;
	if (tree == null) {
	    return result;
	}
	List<N> children = tree.getChildren();
	for (N node : children) {
	    result += countNodes(node);
	}
	return result + 1; // + 1 for self!
    }

    /**
     * This method checks to trees for equality. Equality is only checked on
     * name basis and the order of the children is neglected.
     * 
     * @param tree1
     *            is the first tree to be compared to the second.
     * @param tree2
     *            is the second tree to be compared to the first.
     * @param <N>
     *            is the actual tree implementation.
     * @return <code>true</code> is returned if the trees are equals.
     *         <code>false</code> is returned otherwise.
     */
    public static <N extends TreeNode<N>> boolean equalsWithoutOrder(N tree1,
	    N tree2) {
	if (!tree1.getName().equals(tree2.getName())) {
	    return false;
	}
	List<N> children1 = tree1.getChildren();
	List<N> children2 = tree2.getChildren();
	if (children1.size() != children2.size()) {
	    return false;
	}
	for (N child1 : children1) {
	    for (N child2 : children2) {
		if (child1.equals(child2)) {
		    boolean equals = equalsWithoutOrder(child1, child2);
		    if (!equals) {
			return false;
		    }
		}
	    }
	}
	return true;
    }

    /**
     * Private constructor to avoid instantiation.
     */
    private TreeUtils() {
    }
}
