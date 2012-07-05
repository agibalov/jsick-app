package com.loki2302.jsick.compiler;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.TypedExpression;

public class ExpressionCompiler {
	
	private final Evaluator<DOMExpression, TypedExpression> compilingExpressionEvaluator;
	
	public ExpressionCompiler(Evaluator<DOMExpression, TypedExpression> compilingExpressionEvaluator) {
		this.compilingExpressionEvaluator = compilingExpressionEvaluator; 
	}
	
	public Context<TypedExpression> compile(DOMExpression domExpression) {
		return compilingExpressionEvaluator.evaluate(Context.<DOMExpression>ok(domExpression));
	}
	
}