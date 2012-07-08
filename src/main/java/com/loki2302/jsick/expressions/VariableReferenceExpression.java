package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Type;

public class VariableReferenceExpression extends LvalueExpression {
	
	private final Instance instance;
	
	public VariableReferenceExpression(DOMExpression sourceDOMExpression, Instance instance) {
		super(sourceDOMExpression);
		this.instance = instance;
	}

	public Instance getInstance() {
		return instance;
	}
		
	@Override
	public Expression asSetter(DOMExpression sourceDOMExpression, Expression expression) {
		return new SetVariableValueExpression(sourceDOMExpression, instance, expression);
	}
	
	@Override
	public Type getType() {
		return instance.getType();
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitVariableReferenceExpression(this);
	}

}
