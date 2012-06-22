package com.loki2302.jsick.model.expressions;

import com.loki2302.jsick.model.ExecutionContext;

public abstract class BinaryExpression extends Expression {

	private final Expression left;
	private final Expression right;
	
	protected BinaryExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	protected Expression getLeft() {
		return left;
	}
	
	protected Expression getRight() {
		return right;
	}
	
	@Override
	public abstract int getValue(ExecutionContext context);

}
