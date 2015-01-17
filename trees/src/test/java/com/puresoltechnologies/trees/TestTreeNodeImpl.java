package com.puresoltechnologies.trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is a simple Tree implementation for testing tree utility classes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestTreeNodeImpl implements TreeNode<TestTreeNodeImpl> {

    public static TestTreeNodeImpl getSampleTree() {
	TestTreeNodeImpl root = new TestTreeNodeImpl("root");

	TestTreeNodeImpl n1 = new TestTreeNodeImpl(root, "n1");
	root.addChild(n1);

	TestTreeNodeImpl n2 = new TestTreeNodeImpl(root, "n2");
	root.addChild(n2);

	TestTreeNodeImpl n3 = new TestTreeNodeImpl(root, "n3");
	root.addChild(n3);

	TestTreeNodeImpl n11 = new TestTreeNodeImpl(n1, "n11");
	n1.addChild(n11);

	TestTreeNodeImpl n12 = new TestTreeNodeImpl(n1, "n12");
	n1.addChild(n12);

	TestTreeNodeImpl n13 = new TestTreeNodeImpl(n1, "n13");
	n1.addChild(n13);

	TestTreeNodeImpl n21 = new TestTreeNodeImpl(n2, "n21");
	n2.addChild(n21);

	TestTreeNodeImpl n22 = new TestTreeNodeImpl(n2, "n22");
	n2.addChild(n22);

	TestTreeNodeImpl n23 = new TestTreeNodeImpl(n2, "n23");
	n2.addChild(n23);

	TestTreeNodeImpl n31 = new TestTreeNodeImpl(n3, "n31");
	n3.addChild(n31);

	TestTreeNodeImpl n32 = new TestTreeNodeImpl(n3, "n32");
	n3.addChild(n32);

	TestTreeNodeImpl n33 = new TestTreeNodeImpl(n3, "n33");
	n3.addChild(n33);

	return root;
    }

    private final String name;
    private final TestTreeNodeImpl parent;
    private final List<TestTreeNodeImpl> children = new ArrayList<TestTreeNodeImpl>();

    public TestTreeNodeImpl(String name) {
	super();
	this.name = name;
	this.parent = null;
    }

    public TestTreeNodeImpl(TestTreeNodeImpl parent, String name) {
	super();
	this.name = name;
	this.parent = parent;
    }

    @Override
    public Set<TreeLink<TestTreeNodeImpl>> getEdges() {
	Set<TreeLink<TestTreeNodeImpl>> edges = new HashSet<>();
	edges.add(new TreeLink<>(parent, this));
	for (TestTreeNodeImpl child : children) {
	    edges.add(new TreeLink<>(this, child));
	}
	return edges;
    }

    @Override
    public TestTreeNodeImpl getParent() {
	return parent;
    }

    public void addChild(TestTreeNodeImpl tree) {
	children.add(tree);
    }

    @Override
    public boolean hasChildren() {
	return !children.isEmpty();
    }

    @Override
    public List<TestTreeNodeImpl> getChildren() {
	return children;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public String toString() {
	return name;
    }
}
