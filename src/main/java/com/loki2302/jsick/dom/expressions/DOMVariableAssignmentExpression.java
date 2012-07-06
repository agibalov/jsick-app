package com.loki2302.jsick.dom.expressions;

import org.parboiled.support.IndexRange;

public class DOMVariableAssignmentExpression extends DOMExpression {
	private final DOMVariableReferenceExpression left;
	private final DOMExpression right;
	
	public DOMVariableAssignmentExpression(DOMVariableReferenceExpression left, DOMExpression right, IndexRange matchRange) {
		super(matchRange);
		this.left = left;
		this.right = right;
	}
	
	public DOMVariableReferenceExpression getLeft() {
		return left;
	}
	
	public DOMExpression getRight() {
		return right;
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitVariableAssignmentExpression(this);
	}
}
