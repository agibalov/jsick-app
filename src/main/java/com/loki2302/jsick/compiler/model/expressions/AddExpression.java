package com.loki2302.jsick.compiler.model.expressions;

public class AddExpression extends BinaryExpression {

	public AddExpression(Expression left, Expression right) {
		this(left, right, null);
	}
	
	public AddExpression(Expression left, Expression right, Object sourceContext) {
		super(left, right, sourceContext);
	}

}
