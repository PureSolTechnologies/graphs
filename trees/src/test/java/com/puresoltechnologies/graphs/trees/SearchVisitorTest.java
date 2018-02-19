package com.puresoltechnologies.graphs.trees;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.graphs.trees.SearchVisitor;
import com.puresoltechnologies.graphs.trees.TreeNodeImpl;
import com.puresoltechnologies.graphs.trees.TreeSearchCriterion;
import com.puresoltechnologies.graphs.trees.TreeWalker;

public class SearchVisitorTest {

    private class IntegerTree extends TreeNodeImpl<IntegerTree> {

	private final int i;

	public IntegerTree(IntegerTree parent, int i) {
	    super(parent, String.valueOf(i));
	    this.i = i;
	}

	public int getI() {
	    return i;
	}
    }

    @Test
    public void test() {
	TreeSearchCriterion<IntegerTree> oddCriterion = new TreeSearchCriterion<IntegerTree>() {

	    @Override
	    public boolean accepted(IntegerTree node) {
		if (node.getI() % 2 == 1) {
		    return true;
		} else {
		    return false;
		}
	    }
	};
	IntegerTree tree = new IntegerTree(null, 0);
	for (int i = 1; i <= 10; i++) {
	    new IntegerTree(tree, i);
	}
	SearchVisitor<IntegerTree> search = new SearchVisitor<IntegerTree>(
		oddCriterion);
	TreeWalker.walk(search, tree);
	assertEquals(5, search.getSearchResult().size());
    }

}
