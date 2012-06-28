package com.loki2302.jsick.compiler.model.expressions;

import java.util.List;

public abstract class LiteralExpression extends Expression {
	
	private final String value;
	
	public LiteralExpression(String value) {
		this(value, null);
	}
	
	public LiteralExpression(String value, Object sourceContext) {
		super(sourceContext);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public List<Expression> getDependencies() {	
		return null;
	}
}
