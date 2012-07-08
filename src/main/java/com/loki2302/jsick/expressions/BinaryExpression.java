package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.types.Type;

public abstract class BinaryExpression extends Expression {
	private final Expression left;
	private final Expression right;
	private final Type resultType;
	
	protected BinaryExpression(DOMExpression sourceDOMExpression, Expression left, Expression right, Type resultType) {
		super(sourceDOMExpression);
		this.left = left;
		this.right = right;
		this.resultType = resultType;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}
	
	@Override
	public Type getType() {
		return resultType;
	}
}
