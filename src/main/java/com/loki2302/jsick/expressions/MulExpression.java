package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class MulExpression extends BinaryExpression {	
	public MulExpression(TypedExpression left, TypedExpression right, Type resultType) {
		super(left, right, resultType);
	}
	
	@Override
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitMulExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Mul[%s]{%s,%s}", getType(), getLeft(), getRight());
	}
}
