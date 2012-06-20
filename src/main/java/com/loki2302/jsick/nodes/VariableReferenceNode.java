package com.loki2302.jsick.nodes;

import com.loki2302.jsick.ExecutionContext;

public class VariableReferenceNode extends ExpressionNode {
	
	private final String name;
	
	public VariableReferenceNode(String name) {
		this.name = name;
	}
	
	@Override
	public int getValue(ExecutionContext context) {
		return context.getVariableValue(name);
	}

}
