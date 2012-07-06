package com.loki2302.jsick.statements;

import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Instance;

public class VariableDefinitionStatement extends Statement {
	
	private final Instance instance;
	private final TypedExpression expression;
	
	public VariableDefinitionStatement(Instance instance, TypedExpression expression) {
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
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visitVariableDefinitionStatement(this);
	}

}
