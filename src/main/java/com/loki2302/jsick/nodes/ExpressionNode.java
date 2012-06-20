package com.loki2302.jsick.nodes;

import com.loki2302.jsick.ExecutionContext;

public abstract class ExpressionNode extends Node {
	public abstract int getValue(ExecutionContext context);
}
