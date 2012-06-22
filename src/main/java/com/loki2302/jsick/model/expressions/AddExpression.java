package com.loki2302.jsick.model.expressions;

import com.loki2302.jsick.model.ExecutionContext;

public class AddExpression extends BinaryExpression {

	public AddExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public int getValue(ExecutionContext context) {		
		int leftValue = getLeft().getValue(context);
		int rightValue = getRight().getValue(context);
		return leftValue + rightValue;
	}

}
