package com.loki2302.jsick.parser.tree;

public class VariableReferenceNode extends ExpressionNode {
	
	private final String name;
	
	public VariableReferenceNode(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
