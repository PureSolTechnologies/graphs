package com.puresoltechnologies.trees;

import com.puresoltechnologies.graph.Edge;
import com.puresoltechnologies.graph.Pair;

public class TreeLink<N extends TreeNode<N>> implements Edge<N, TreeLink<N>> {

    private final N first;
    private final N second;

    public TreeLink(N first, N second) {
	super();
	this.first = first;
	this.second = second;
    }

    @Override
    public Pair<N> getVertices() {
	return new Pair<>(first, second);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((first == null) ? 0 : first.hashCode());
	result = prime * result + ((second == null) ? 0 : second.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TreeLink<?> other = (TreeLink<?>) obj;
	if (first == null) {
	    if (other.first != null)
		return false;
	} else if (!first.equals(other.first))
	    return false;
	if (second == null) {
	    if (other.second != null)
		return false;
	} else if (!second.equals(other.second))
	    return false;
	return true;
    }

}
