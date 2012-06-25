package com.loki2302.jsick.compiler.model.expressions;

public class IntLiteralExpression extends LiteralExpression {

	public IntLiteralExpression(String value) {
		this(value, null);
	}
	
	public IntLiteralExpression(String value, Object sourceContext) {
		super(value, sourceContext);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitIntLiteralExpression(this);
	}

}
