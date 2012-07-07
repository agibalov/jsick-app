package com.loki2302.jsick.expressions;

public interface LvalueExpression extends Expression {	
	Expression asSetter(Expression expression);
}