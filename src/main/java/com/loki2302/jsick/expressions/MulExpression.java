package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.types.Type;

public class MulExpression extends BinaryExpression {	
	public MulExpression(DOMExpression sourceDOMExpression, Expression left, Expression right, Type resultType) {		
		super(sourceDOMExpression, left, right, resultType);
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitMulExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Mul[%s]{%s,%s}", getType(), getLeft(), getRight());
	}
}
