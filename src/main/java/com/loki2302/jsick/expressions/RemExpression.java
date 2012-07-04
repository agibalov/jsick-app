package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class RemExpression extends BinaryExpression {	
	public RemExpression(TypedExpression left, TypedExpression right, Type resultType) {
		super(left, right, resultType);
	}
	
	@Override
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitRemExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Rem[%s]{%s,%s}", getType(), getLeft(), getRight());
	}
}
