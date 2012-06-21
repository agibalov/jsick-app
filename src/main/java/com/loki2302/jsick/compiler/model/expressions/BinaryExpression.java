package com.loki2302.jsick.compiler.model.expressions;

public abstract class BinaryExpression extends Expression {

	private final Expression left;
	private final Expression right;
	
	protected BinaryExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}
	
}
