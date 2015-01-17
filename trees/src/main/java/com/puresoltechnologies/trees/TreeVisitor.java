package com.puresoltechnologies.trees;

/**
 * This is the simple interface for a tree visitor. The walker walks the tree
 * and used this interface to tell the visitor the position.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <N>
 *            is the actual tree node type.
 */
public interface TreeVisitor<N extends TreeNode<N>> {

    /**
     * This method is called for every node in the tree.
     * 
     * @param tree
     *            is the {@link TreeNode} object of the current visiting node.
     * @return A {@link WalkingAction} is to be returned to tell the walker how
     *         to proceed. For details of the different options, have a look to
     *         the {@link WalkingAction} API documentation.
     */
    public WalkingAction visit(N tree);

}
