package com.puresoltechnologies.trees;

import java.io.PrintStream;

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
     *            is the {@link TreeNode} object to be printed.
     * @param <T>
     *            is the concrete tree interface to be used.
     */
    public <N extends TreeNode<N>> void println(N tree) {
	println(tree, 0);
    }

    private <N extends TreeNode<N>> void println(N node, int depth) {
	for (int i = 0; i < depth; i++) {
	    stream.print("  |  ");
	}
	stream.print("  |__");
	stream.print("id: " + node.toString());
	stream.println();
	for (N childNode : node.getChildren()) {
	    println(childNode, depth + 1);
	}
    }
}
