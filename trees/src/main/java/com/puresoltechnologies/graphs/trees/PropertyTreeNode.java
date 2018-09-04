package com.puresoltechnologies.graphs.trees;

import java.util.Map;

/**
 * This interface is a specialization of {@link TreeNode} and provides the
 * feature to add properties to nodes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <N>
 *            is the actual class of the {@link PropertyTreeNode}
 *            implementation.
 */
public interface PropertyTreeNode<N extends LabeledTreeNode<N>> extends
	TreeNode<N> {

    /**
     * This method adds sets a property to the {@link TreeNode}.
     * 
     * @param key
     *            is the key of the property.
     * @param value
     *            is the actual value to set.
     * @return The previously set value is returned. <code>null</code> is
     *         returned if the property is set the first time.
     */
    public Object setProperty(String key, Object value);

    /**
     * Removes a label from the {@link TreeNode}.
     * 
     * @param key
     *            is the key of the property.
     * @return The previously set value is returned. <code>null</code> is
     *         returned if the property was not set and therefore not deleted.
     */
    public Object removeProperty(String key);

    /**
     * This method checks whether a certain property is present on this
     * {@link TreeNode} or not.
     * 
     * @param key
     *            is the key of the property.
     * @return <code>true</code> is returned in case the property is present.
     *         <code>false</code> is returned otherwise.
     */
    public boolean hasProperty(String key);

    /**
     * This method returns a {@link Map} of properties which were added to this
     * {@link TreeNode}.
     * 
     * @return A {@link Map} of {@link String}s is returned containing the
     *         properties currently present.
     */
    public Map<String, Object> getProperties();

}
