package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public class VariableReferenceExpression implements TypedExpression {
	
	private final String variableName;
	private final Type variableType;
	
	public VariableReferenceExpression(String variableName, Type variableType) {
		this.variableName = variableName;
		this.variableType = variableType;
	}

	public String getVariableName() {
		return variableName;
	}
	
	@Override
	public Type getType() {
		return variableType;
	}

	@Override
	public <T> T accept(TypedExpressionVisitor<T> visitor) {
		return visitor.visitVariableReferenceExpression(this);
	}

}
