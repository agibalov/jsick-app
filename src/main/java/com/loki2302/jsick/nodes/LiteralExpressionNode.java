package com.loki2302.jsick.nodes;

import com.loki2302.jsick.ExecutionContext;

public class LiteralExpressionNode extends ExpressionNode {
	
	private final int value;
	
	public LiteralExpressionNode(int value) {
		this.value = value;
	}
	
	@Override
	public int getValue(ExecutionContext context) {
		return value;
	}

}
