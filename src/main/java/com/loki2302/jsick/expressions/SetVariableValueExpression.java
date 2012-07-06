package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Type;

public class SetVariableValueExpression implements TypedExpression {
	
	private final Instance instance;
	private final TypedExpression expression;
	
	public SetVariableValueExpression(Instance instance, TypedExpression expression) {
		this.instance = instance;
		this.expression = expression;
	}

	public Instance getInstance() {
		return instance;
	}
	
	public TypedExpression getExpression() {
		return expression;
	}
	
	@Override
	public Type getType() {
		return instance.getType();
	}

	@Override
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitSetVariableValueExpression(this);
	}

}
