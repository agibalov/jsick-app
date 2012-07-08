package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.types.Type;

public class CastExpression extends Expression {
	private Expression expression;
	private Type targetType;
	
	public CastExpression(DOMExpression sourceDOMExpression, Expression expression, Type targetType) {
		super(sourceDOMExpression);
		this.expression = expression;
		this.targetType = targetType;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Type getTargetType() {
		return targetType;
	}
	
	@Override
	public Type getType() {			
		return targetType;
	}    	
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitCastExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Cast{%s->%s}", expression.getType(), targetType);
	}	
}