package com.loki2302.jsick.evaluator.statements;

import com.loki2302.jsick.compiler.ExpressionCompiler;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.statements.DOMPrintStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.statements.PrintStatement;
import com.loki2302.jsick.statements.Statement;

public class DOMPrintStatementToStatementConverterEvaluator extends Evaluator<DOMPrintStatement, Statement> {
	
	private final ExpressionCompiler expressionCompiler;
	
	public DOMPrintStatementToStatementConverterEvaluator(ExpressionCompiler expressionCompiler) {
		this.expressionCompiler = expressionCompiler;
	}
	
	@Override
	public Context<Statement> evaluate(DOMPrintStatement input) {
		DOMExpression expression = input.getExpression();
		Context<Expression> expressionContext = expressionCompiler.compile(expression);
		if(!expressionContext.isOk()) {
			return fail(expressionContext.getError());
		}
		
		return ok(new PrintStatement(expressionContext.getValue()));
	}		
}