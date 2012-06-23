package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class VariableReferenceNode extends ExpressionNode {
	
	private final String name;
	
	public VariableReferenceNode(String name, IndexRange indexRange) {
		super(indexRange);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
