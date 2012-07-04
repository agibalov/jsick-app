package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class SubExpression extends BinaryExpression {	
	public SubExpression(TypedExpression left, TypedExpression right, Type resultType) {
		super(left, right, resultType);
	}
	
	@Override
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitSubExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Sub[%s]{%s,%s}", getType(), getLeft(), getRight());
	}
}
