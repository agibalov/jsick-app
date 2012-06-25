package com.loki2302.jsick.compiler.model.expressions;

public class SubExpression extends BinaryExpression {

	public SubExpression(Expression left, Expression right) {
		this(left, right, null);
	}
	
	public SubExpression(Expression left, Expression right, Object sourceContext) {
		super(left, right, sourceContext);
	}

}
