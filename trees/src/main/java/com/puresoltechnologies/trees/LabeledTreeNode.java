package com.puresoltechnologies.trees;

import java.util.Set;

/**
 * This interface is a specialization of {@link TreeNode} and provides the
 * feature to add labels to nodes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <N>
 *            is the actual class of the {@link LabeledTreeNode} implementation.
 */
public interface LabeledTreeNode<N extends LabeledTreeNode<N>> extends
	TreeNode<N> {

    /**
     * This method adds a label to the {@link TreeNode}.
     * 
     * @param label
     *            is the label to be added.
     * @return <code>true</code> is returned in case the label was added and did
     *         not exist before on this {@link TreeNode}. <code>false</code> is
     *         returned otherwise.
     */
    public boolean addLabel(String label);

    /**
     * Removes a label from the {@link TreeNode}.
     * 
     * @param label
     *            is the label to be removed.
     * @return <code>true</code> is returned if the label was really removed and
     *         existed. <code>false</code> is returned otherwise.
     */
    public boolean removeLabel(String label);

    /**
     * This method checks whether a certain label is present on this
     * {@link TreeNode} or not.
     * 
     * @param label
     *            is the label to be checked.
     * @return <code>true</code> is returned in case the label is present.
     *         <code>false</code> is returned otherwise.
     */
    public boolean hasLabel(String label);

    /**
     * This method returns a {@link Set} of labels which were added to this
     * {@link TreeNode}.
     * 
     * @return A {@link Set} of {@link String} is returned containing the labels
     *         currently present.
     */
    public Set<String> getLabels();

}
