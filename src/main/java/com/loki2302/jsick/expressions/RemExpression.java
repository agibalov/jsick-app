package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class RemExpression extends BinaryExpression {	
	public RemExpression(Expression left, Expression right, Type resultType) {
		super(left, right, resultType);
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitRemExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Rem[%s]{%s,%s}", getType(), getLeft(), getRight());
	}
}
