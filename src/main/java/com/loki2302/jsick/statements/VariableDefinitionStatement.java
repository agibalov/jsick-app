package com.loki2302.jsick.statements;

import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Instance;

public class VariableDefinitionStatement extends Statement {
	
	private final Instance instance;
	private final Expression expression;
	
	public VariableDefinitionStatement(Instance instance, Expression expression) {
		this.instance = instance;
		this.expression = expression;
	}
	
	public Instance getInstance() {
		return instance;
	}
		
	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visitVariableDefinitionStatement(this);
	}

}
