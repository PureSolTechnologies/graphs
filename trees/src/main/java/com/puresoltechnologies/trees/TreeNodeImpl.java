package com.puresoltechnologies.trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This is an abstract, immutable implementation of a {@link TreeNode}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <N>
 *            is the type of the nodes. T needs to implement AbstractTreeImpl.
 */
public class TreeNodeImpl<N extends TreeNodeImpl<N>> implements TreeNode<N>,
	Iterable<N> {

    private final N parent;
    private final List<N> children = new ArrayList<N>();
    private final String name;

    /**
     * This is the initial value constructor.
     * 
     * @param parent
     *            is the parent where this node is to be added to.
     * @param name
     *            is the identifier of this node.
     */
    public TreeNodeImpl(N parent, String name) {
	super();
	if (name == null) {
	    throw new IllegalArgumentException(
		    "The name of the node must not be null to avoid ambiguities.");
	}
	this.parent = parent;
	if (parent != null) {
	    @SuppressWarnings("unchecked")
	    N t = (N) this;
	    parent.addChild(t);
	}
	this.name = name;
    }

    /**
     * This method is used to add children to a parent node in the constructor.
     * This method should never be called from outside the constructor.
     * 
     * @param child
     *            is the child to be added to the {@link #children} list.
     */
    protected void addChild(N child) {
	children.add(child);
    }

    @Override
    public N getParent() {
	return parent;
    }

    @Override
    public boolean hasChildren() {
	return children.size() > 0;
    }

    @Override
    public List<N> getChildren() {
	return children;
    }

    @Override
    public Set<TreeLink<N>> getEdges() {
	Set<TreeLink<N>> edges = new HashSet<>();
	@SuppressWarnings("unchecked")
	N thisNode = (N) this;
	edges.add(new TreeLink<>(parent, thisNode));
	for (N child : children) {
	    edges.add(new TreeLink<>(thisNode, child));
	}
	return edges;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public Iterator<N> iterator() {
	@SuppressWarnings("unchecked")
	N t = (N) this;
	return new TreeIterator<N>(t);
    }
}
