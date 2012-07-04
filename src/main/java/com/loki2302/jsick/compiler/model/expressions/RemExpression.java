package com.loki2302.jsick.compiler.model.expressions;

public class RemExpression extends BinaryExpression {

	public RemExpression(Expression left, Expression right) {
		this(left, right, null);
	}
	
	public RemExpression(Expression left, Expression right, Object sourceContext) {
		super(left, right, sourceContext);
	}
		
}
