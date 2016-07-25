package com.puresoltechnologies.trees;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class prints a tree to a specified PrintStream.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreePrinter {

    private final PrintStream stream;

    /**
     * Constructor to instantiate this class.
     * 
     * @param stream
     *            is the {@link PrintStream} to which trees are printed to.
     */
    public TreePrinter(PrintStream stream) {
	super();
	this.stream = stream;
    }

    /**
     * This method is used to print the tree provided into the
     * {@link PrintStream} set in the constructor
     * {@link #TreePrinter(PrintStream)}.
     * 
     * @param tree
     *            is the {@link Tree} object to be printed.
     * @param <N>
     *            is the concrete tree node interface to be used.
     */
    public <N extends TreeNode<N>> void println(Tree<N> tree) {
	List<Boolean> links = new ArrayList<>();
	println(tree.getRootNode(), links, false);
    }

    /**
     * This method is used to print the tree provided into the
     * {@link PrintStream} set in the constructor
     * {@link #TreePrinter(PrintStream)}.
     * 
     * @param tree
     *            is the {@link TreeNode} object to be printed.
     * @param <N>
     *            is the concrete tree node interface to be used.
     */
    public <N extends TreeNode<N>> void println(N tree) {
	List<Boolean> links = new ArrayList<>();
	println(tree, links, false);
    }

    private <N extends TreeNode<N>> void println(N node, List<Boolean> links, boolean addLink) {
	for (boolean link : links) {
	    if (link) {
		stream.print(" | ");
	    } else {
		stream.print("   ");
	    }
	}
	stream.print(" |_");
	stream.print("id: " + node.toString());
	stream.println();
	if (addLink) {
	    links.add(true);
	} else {
	    links.add(false);
	}
	List<N> children = node.getChildren();
	for (int childId = 0; childId < children.size(); ++childId) {
	    N childNode = children.get(childId);
	    if (childId < children.size() - 1) {
		println(childNode, links, true);
	    } else {
		println(childNode, links, false);
	    }
	}
	links.remove(links.size() - 1);
    }
}
