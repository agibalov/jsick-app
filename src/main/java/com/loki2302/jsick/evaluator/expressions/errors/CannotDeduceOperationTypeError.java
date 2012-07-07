package com.loki2302.jsick.evaluator.expressions.errors;

import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.expressions.Expression;

public class CannotDeduceOperationTypeError extends AbstractError {
	private final Expression left;
	private final Expression right;
	 
	public CannotDeduceOperationTypeError(Evaluator<?, ?> evaluator, Object input, Expression left, Expression right) {
		super(evaluator, input);
		this.left = left;
		this.right = right;
	}		
	
	@Override
	public String toString() {
		return String.format("CannotDeduceOperationType{%s,%s}", left.getType(), right.getType());
	}
}