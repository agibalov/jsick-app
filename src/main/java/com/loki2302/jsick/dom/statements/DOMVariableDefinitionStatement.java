package com.loki2302.jsick.dom.statements;

import org.parboiled.support.IndexRange;

import com.loki2302.jsick.dom.expressions.DOMExpression;

public class DOMVariableDefinitionStatement extends DOMStatement {
	private final String typeName;
	private final String variableName;
	private final DOMExpression expression;

	public DOMVariableDefinitionStatement(String typeName, String variableName, DOMExpression expression, IndexRange matchRange) {
		super(matchRange);
		this.typeName = typeName;
		this.variableName = variableName;
		this.expression = expression;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	public DOMExpression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(DOMStatementVisitor<T> visitor) {
		return visitor.visitVariableDefinitionStatement(this);
	}	
}