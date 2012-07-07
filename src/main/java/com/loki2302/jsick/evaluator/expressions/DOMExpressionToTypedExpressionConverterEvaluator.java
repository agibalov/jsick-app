package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMExpressionVisitor;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.TypedExpression;

public class DOMExpressionToTypedExpressionConverterEvaluator extends Evaluator<DOMExpression, TypedExpression> {

	private final DOMExpressionVisitor<Context<TypedExpression>> compilingDOMExpressionVisitor;

	public DOMExpressionToTypedExpressionConverterEvaluator(
			DOMExpressionVisitor<Context<TypedExpression>> compilingDOMExpressionVisitor) {
		
		this.compilingDOMExpressionVisitor = compilingDOMExpressionVisitor;
	}

	@Override
	public Context<TypedExpression> evaluate(DOMExpression input) {
		return input.accept(compilingDOMExpressionVisitor);
	}
}