package com.loki2302.jsick.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Type;

public class SetVariableValueExpression extends Expression {
	
	private final Instance instance;
	private final Expression expression;
	
	public SetVariableValueExpression(DOMExpression sourceDOMExpression, Instance instance, Expression expression) {
		super(sourceDOMExpression);
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
	public Type getType() {
		return instance.getType();
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitSetVariableValueExpression(this);
	}

}
