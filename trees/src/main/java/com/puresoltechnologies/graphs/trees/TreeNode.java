package com.puresoltechnologies.graphs.trees;

import java.util.List;

import com.puresoltechnologies.graphs.graph.Vertex;

/**
 * This is the most simple interface to a single tree node. The parent can be
 * retrieved and also all children. It's designed as generic to be useful in all
 * kinds of trees.
 * 
 * The interface just provides methods for an immutable tree to get a
 * thread-safe variant of trees. The tree creation should be finished as soon as
 * this interface is exposed. All other threads using this interface can access
 * in a thread-safe way.
 * 
 * The {@link TreeNode} interface extends the {@link Vertex} interface for a
 * generic access in a "graph way". Due to the tree links (edges in a tree) are
 * not of significants, the implementation of the links is implemented in
 * {@link TreeLink} and fixed. Implementations of {@link TreeNode} do not need
 * to implement a link on their own.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <N>
 *            is the actual class of the {@link TreeNode} implementation.
 */
public interface TreeNode<N extends TreeNode<N>> extends Vertex<N, TreeLink<N>> {

    /**
     * This method returns the parent node.
     * 
     * @return A {@link TreeNode} of T is returned. <code>null</code> is returned if
     *         no parent is present and the current node is the root node.
     */
    public N getParent();

    /**
     * This method returns whether or not the current node contains children.
     * 
     * @return <code>true</code> is returned in case the node contains children.
     *         <code>false</code> is returned otherwise.
     */
    public boolean hasChildren();

    /**
     * This method returns the children which are contained in the node.
     * 
     * @return A {@link List} of T is returned. If not child is found this list is
     *         empty. The return value <b>must not</b> be <code>null</code>.
     */
    public List<N> getChildren();

    /**
     * This method returns the node identifier. Each node in a tree should have an
     * identifying name. This is the most basic information. Implementation of this
     * interface {@link TreeNode} may have other values, too.
     * 
     * @return A {@link String} is returned containing the name.
     */
    public String getName();
}
