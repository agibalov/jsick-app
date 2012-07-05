package com.loki2302.jsick.compiler;

import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.statements.DOMExpressionStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.statements.ExpressionStatement;
import com.loki2302.jsick.statements.Statement;

public class DOMExpressionStatementToStatementConverterEvaluator extends Evaluator<DOMExpressionStatement, Statement> {
	
	private final ExpressionCompiler expressionCompiler;
	
	public DOMExpressionStatementToStatementConverterEvaluator(ExpressionCompiler expressionCompiler) {
		this.expressionCompiler = expressionCompiler;
	}
	
	@Override
	public Context<Statement> evaluate(Context<DOMExpressionStatement> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}
		
		DOMExpressionStatement domStatement = input.getValue();
		
		DOMExpression domExpression = domStatement.getExpression();
		Context<TypedExpression> expressionContext = expressionCompiler.compile(domExpression);
		if(!expressionContext.isOk()) {
			return fail(expressionContext.getError());
		}
		
		return ok(new ExpressionStatement(expressionContext.getValue()));
	}		
}