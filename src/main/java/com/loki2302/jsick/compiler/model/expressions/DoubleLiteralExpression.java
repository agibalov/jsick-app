package com.loki2302.jsick.compiler.model.expressions;

public class DoubleLiteralExpression extends LiteralExpression {
	
	public DoubleLiteralExpression(String value) {
		this(value, null);
	}
	
	public DoubleLiteralExpression(String value, Object sourceContext) {
		super(value, sourceContext);
	}

}