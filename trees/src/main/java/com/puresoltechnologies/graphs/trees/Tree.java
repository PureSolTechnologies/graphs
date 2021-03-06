package com.puresoltechnologies.graphs.trees;

import com.puresoltechnologies.graphs.graph.CycleAnalyzer;
import com.puresoltechnologies.graphs.graph.Graph;
import com.puresoltechnologies.graphs.graph.SubgraphAnalyzer;

/**
 * This is the interface for a Tree {@link Graph}. This interface is to be seen
 * as a wrapper to make a tree to a graph to use graph functions.
 * 
 * A valid implementation and assignment of a tree has the condition that
 * {@link CycleAnalyzer#hasCycles(Graph, boolean)} returns <code>false</code>
 * and {@link SubgraphAnalyzer#hasDisconnectedSubgraph(Graph)} returns false,
 * too. Additionally, the node returned by {@link #getRootNode()} must not have
 * a parent.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <N>
 *            is the actual {@link TreeNode} implementation.
 */
public interface Tree<N extends TreeNode<N>> extends Graph<N, TreeLink<N>> {

    /**
     * This method returns the root node of the tree. The root node of the tree is
     * the one and only node which does not have a parent.
     * 
     * @return An object of N is returned containing the reference to the root node
     *         of the tree.
     */
    public N getRootNode();

}
