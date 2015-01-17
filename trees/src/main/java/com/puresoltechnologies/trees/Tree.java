package com.puresoltechnologies.trees;

import com.puresoltechnologies.graph.Graph;
import com.puresoltechnologies.graph.GraphVerfier;

/**
 * This is the interface for a Tree {@link Graph}. This interface is to be seen
 * as a wrapper to make a tree to a graph to use graph functions.
 * 
 * A valid implementation and assignment of a tree has the condition that
 * {@link GraphVerfier#hasCycles(Graph))} returns <code>false</code> and
 * {@link GraphVerfier#hasDisconnectedSubgraph(Graph)} returns false, too.
 * Additionally, the node returned by {@link #getRootNode()} must not have a
 * parent.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <N>
 *            is the actual {@link TreeNode} implementation.
 */
public interface Tree<N extends TreeNode<N>> extends Graph<N, TreeLink<N>> {

    /**
     * This method returns the root node of the tree. The root node of the tree
     * is the one and only node which does not have a parent.
     * 
     * @return An object of N is returned containing the reference to the root
     *         node of the tree.
     */
    public N getRootNode();

}
