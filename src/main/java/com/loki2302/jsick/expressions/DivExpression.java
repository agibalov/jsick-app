package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class DivExpression extends BinaryExpression {	
	public DivExpression(Expression left, Expression right, Type resultType) {
		super(left, right, resultType);
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitDivExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Div[%s]{%s,%s}", getType(), getLeft(), getRight());
	}	
}
