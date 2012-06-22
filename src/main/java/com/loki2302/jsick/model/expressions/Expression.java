package com.loki2302.jsick.model.expressions;

import com.loki2302.jsick.model.ExecutionContext;

public abstract class Expression {
	public abstract int getValue(ExecutionContext context);
}
