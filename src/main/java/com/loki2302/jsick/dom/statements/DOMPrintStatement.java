package com.loki2302.jsick.dom.statements;

import org.parboiled.support.IndexRange;

import com.loki2302.jsick.dom.expressions.DOMExpression;

public class DOMPrintStatement extends DOMStatement {
	private final DOMExpression expression;

	public DOMPrintStatement(DOMExpression expression, IndexRange matchRange) {
		super(matchRange);
		this.expression = expression;
	}
	
	public DOMExpression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(DOMStatementVisitor<T> visitor) {
		return visitor.visitPrintStatement(this);
	}	
}