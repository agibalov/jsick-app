package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public interface Expression {
	Type getType();
	<T> T accept(ExpressionVisitor<T> visitor);
}