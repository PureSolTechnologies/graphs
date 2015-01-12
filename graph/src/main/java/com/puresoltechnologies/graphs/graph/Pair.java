package com.puresoltechnologies.graphs.graph;

import java.util.Objects;

public class Pair<T> {

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
