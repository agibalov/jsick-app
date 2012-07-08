package com.loki2302.jsick.evaluator.statements;

import com.loki2302.jsick.compiler.ExpressionCompiler;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.statements.DOMExpressionStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.statements.ExpressionStatement;
import com.loki2302.jsick.statements.Statement;

public class ExpressionStatementEvaluator extends Evaluator<DOMExpressionStatement, Statement> {
	
	private final ExpressionCompiler expressionCompiler;
	
	public ExpressionStatementEvaluator(ExpressionCompiler expressionCompiler) {
		this.expressionCompiler = expressionCompiler;
	}
	
	@Override
	public Context<Statement> evaluate(DOMExpressionStatement input) {		
		DOMExpression domExpression = input.getExpression();
		Context<Expression> expressionContext = expressionCompiler.compile(domExpression);
		if(!expressionContext.isOk()) {
			return fail(expressionContext.getError());
		}
		
		return ok(new ExpressionStatement(expressionContext.getValue()));
	}		
}