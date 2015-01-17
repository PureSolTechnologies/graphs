package com.puresoltechnologies.trees;

/**
 * This is a simple interface to specify a tree search criterion.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <N>
 *            is the actual tree node implementation.
 */
public interface TreeSearchCriterion<N extends TreeNode<N>> {

    /**
     * This method returns whether a node is accepted as a search result or not.
     * 
     * @param node
     *            is the current node visited.
     * @return True is returned if the current node is to be added to the search
     *         result or not. False is returned otherwise.
     */
    public boolean accepted(N node);

}
