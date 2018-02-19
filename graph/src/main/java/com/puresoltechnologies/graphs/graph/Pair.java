package com.puresoltechnologies.graphs.graph;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is used to model a pair of values. Whether the order first
 * &lt;-&gt; second is of importance is a matter of interpretation of the using
 * classes.
 * 
 * For directed graphs the first value is the start element and the second the
 * target element for instance. If the graph is not directed, the pair should
 * not be of any matter.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <T>
 *            is the type of the values to be paired.
 */
public class Pair<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final T first;
    private final T second;
    private final int hashCode;

    public Pair(T first, T second) {
	super();
	this.first = first;
	this.second = second;
	hashCode = Objects.hash(first, second);
    }

    public T getFirst() {
	return first;
    }

    public T getSecond() {
	return second;
    }

    @Override
    public int hashCode() {
	return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Pair<?> other = (Pair<?>) obj;
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
