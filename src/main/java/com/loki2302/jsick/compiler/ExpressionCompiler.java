package com.loki2302.jsick.compiler;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;

public class ExpressionCompiler {
	
	private final Evaluator<DOMExpression, Expression> compilingExpressionEvaluator;
	
	public ExpressionCompiler(Evaluator<DOMExpression, Expression> compilingExpressionEvaluator) {
		this.compilingExpressionEvaluator = compilingExpressionEvaluator; 
	}
	
	public Context<Expression> compile(DOMExpression domExpression) {
		return compilingExpressionEvaluator.evaluate(domExpression);
	}
	
}