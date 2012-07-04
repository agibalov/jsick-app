package com.loki2302.jsick.dom.statements;

import org.parboiled.support.IndexRange;

import com.loki2302.jsick.dom.expressions.DOMExpression;

public class DOMExpressionStatement extends DOMStatement {
	private final DOMExpression expression;

	public DOMExpressionStatement(DOMExpression expression, IndexRange indexRange) {
		super(indexRange);
		this.expression = expression;
	}
	
	public DOMExpression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(DOMStatementVisitor<T> visitor) {
		return visitor.visitExpressionStatement(this);
	}	
}