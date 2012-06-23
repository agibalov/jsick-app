package com.loki2302.jsick.compiler.model.expressions;

public class MulExpression extends BinaryExpression {

	public MulExpression(Expression left, Expression right) {
		this(left, right, null);
	}
	
	public MulExpression(Expression left, Expression right, Object sourceContext) {
		super(left, right, sourceContext);
	}
	
}
