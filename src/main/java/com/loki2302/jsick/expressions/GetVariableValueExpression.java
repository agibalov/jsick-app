package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Type;

public class GetVariableValueExpression implements TypedExpression {
	
	private final Instance instance;
	
	public GetVariableValueExpression(Instance instance) {
		this.instance = instance;
	}

	public Instance getInstance() {
		return instance;
	}
	
	@Override
	public Type getType() {
		return instance.getType();
	}

	@Override
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitGetVariableValueExpression(this);
	}

}
