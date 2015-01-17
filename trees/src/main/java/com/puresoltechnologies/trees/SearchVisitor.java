package com.puresoltechnologies.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@link TreeVisitor} implementation is for searching a whole tree for a
 * special {@link TreeSearchCriterion}. This implementation looks through the
 * whole tree and returns a list of all node matching the criterion.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <N>
 *            is the {@link TreeNode} type to be used.
 */
public class SearchVisitor<N extends TreeNode<N>> implements TreeVisitor<N> {

    private final TreeSearchCriterion<N> criterion;

    private final List<N> searchResult = new ArrayList<N>();

    public SearchVisitor(TreeSearchCriterion<N> criterion) {
	super();
	this.criterion = criterion;
    }

    @Override
    public WalkingAction visit(N tree) {
	if (criterion.accepted(tree)) {
	    searchResult.add(tree);
	}
	return WalkingAction.PROCEED;
    }

    /**
     * This method returns the list of results found during tree walk.
     * 
     * @return A {@link List} of T is returned.
     */
    public List<N> getSearchResult() {
	return searchResult;
    }
}
