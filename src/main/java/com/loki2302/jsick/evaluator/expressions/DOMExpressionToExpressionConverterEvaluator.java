package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMExpressionVisitor;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;

public class DOMExpressionToExpressionConverterEvaluator extends Evaluator<DOMExpression, Expression> {

	private final DOMExpressionVisitor<Context<Expression>> compilingDOMExpressionVisitor;

	public DOMExpressionToExpressionConverterEvaluator(
			DOMExpressionVisitor<Context<Expression>> compilingDOMExpressionVisitor) {
		
		this.compilingDOMExpressionVisitor = compilingDOMExpressionVisitor;
	}

	@Override
	public Context<Expression> evaluate(DOMExpression input) {
		return input.accept(compilingDOMExpressionVisitor);
	}
}