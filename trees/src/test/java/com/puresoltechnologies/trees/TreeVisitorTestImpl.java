package com.puresoltechnologies.trees;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.WalkingAction;

/**
 * This class is a simple implementation for a tree visitor to check the
 * TreeWalker class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreeVisitorTestImpl implements TreeVisitor<TestTreeNodeImpl> {

	private String nodeString = "";

	private Map<String, WalkingAction> actions = new HashMap<String, WalkingAction>();

	@Override
	public WalkingAction visit(TestTreeNodeImpl syntaxTree) {
		nodeString += syntaxTree.getName();
		if (actions.containsKey(syntaxTree.getName())) {
			return actions.get(syntaxTree.getName());
		}
		return WalkingAction.PROCEED;
	}

	public void addAction(String name, WalkingAction action) {
		actions.put(name, action);
	}

	public String getNodeString() {
		return nodeString;
	}

}
