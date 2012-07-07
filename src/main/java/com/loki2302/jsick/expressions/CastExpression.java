package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class CastExpression implements Expression {
	private Expression expression;
	private Type targetType;
	
	public CastExpression(Expression expression, Type targetType) {
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