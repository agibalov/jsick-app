package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class CastExpression implements TypedExpression {
	private TypedExpression expression;
	private Type targetType;
	
	public CastExpression(TypedExpression expression, Type targetType) {
		this.expression = expression;
		this.targetType = targetType;
	}
	
	public TypedExpression getExpression() {
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
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitCastExpression(this);
	}
	
	@Override
	public String toString() {
		return String.format("Cast{%s->%s}", expression.getType(), targetType);
	}	
}