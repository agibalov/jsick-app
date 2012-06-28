package com.loki2302.jsick.compiler.model.expressions;

import java.util.Arrays;
import java.util.List;

public abstract class BinaryExpression extends Expression {

	private final Expression left;
	private final Expression right;
	
	protected BinaryExpression(Expression left, Expression right) {
		this(left, right, null);
	}
	
	protected BinaryExpression(Expression left, Expression right, Object sourceContext) {
		super(sourceContext);
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}

	@Override
	public List<Expression> getDependencies() {
		return Arrays.asList(left, right);
	}
	
}
