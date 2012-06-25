package com.loki2302.jsick.compiler.model.expressions;

public class DivExpression extends BinaryExpression {

	public DivExpression(Expression left, Expression right) {
		this(left, right, null);
	}
	
	public DivExpression(Expression left, Expression right, Object sourceContext) {
		super(left, right, sourceContext);
	}
		
}
