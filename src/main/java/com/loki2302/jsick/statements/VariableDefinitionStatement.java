package com.loki2302.jsick.statements;

import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class VariableDefinitionStatement extends Statement {
	
	private final Type variableType;	
	private final String variableName;
	private final TypedExpression expression;
	
	public VariableDefinitionStatement(Type variableType, String variableName, TypedExpression expression) {
		this.variableType = variableType;
		this.variableName = variableName;
		this.expression = expression;
	}
	
	public Type getVariableType() {
		return variableType;
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	public TypedExpression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visitVariableDefinitionStatement(this);
	}

}
