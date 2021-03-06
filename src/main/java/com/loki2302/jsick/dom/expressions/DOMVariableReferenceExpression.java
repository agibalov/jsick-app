package com.loki2302.jsick.dom.expressions;

import org.parboiled.support.IndexRange;

public class DOMVariableReferenceExpression extends DOMExpression {

	private final String variableName;
	
	public DOMVariableReferenceExpression(String variableName, IndexRange matchRange) {
		super(matchRange);
		this.variableName = variableName;
	}
	
	public String getVariableName() {
		return variableName;
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitVariableReferenceExpression(this);
	}

}
