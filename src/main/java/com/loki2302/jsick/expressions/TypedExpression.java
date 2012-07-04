package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public interface TypedExpression {
	Type getType();
	<T> T accept(TypedExpressionVisitor<T> visitor);
}