package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class AddExpression extends BinaryExpression {	
	public AddExpression(TypedExpression left, TypedExpression right, Type resultType) {
		super(left, right, resultType);
	}
	
	@Override
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitAddExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Add[%s]{%s,%s}", getType(), getLeft(), getRight());
	}	
}
