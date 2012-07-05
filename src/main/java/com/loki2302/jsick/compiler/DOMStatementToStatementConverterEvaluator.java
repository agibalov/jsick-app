package com.loki2302.jsick.compiler;

import com.loki2302.jsick.dom.statements.DOMExpressionStatement;
import com.loki2302.jsick.dom.statements.DOMPrintStatement;
import com.loki2302.jsick.dom.statements.DOMStatement;
import com.loki2302.jsick.dom.statements.DOMStatementVisitor;
import com.loki2302.jsick.dom.statements.DOMVariableDefinitionStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.statements.Statement;

public class DOMStatementToStatementConverterEvaluator extends Evaluator<DOMStatement, Statement> {	
	
	private final Evaluator<DOMExpressionStatement, Statement> expressionStatementEvaluator;
	private final Evaluator<DOMPrintStatement, Statement> printStatementEvaluator;
	private final Evaluator<DOMVariableDefinitionStatement, Statement> variableDefinitionStatementEvaluator;
	
	public DOMStatementToStatementConverterEvaluator(
			Evaluator<DOMExpressionStatement, Statement> expressionStatementEvaluator,
			Evaluator<DOMPrintStatement, Statement> printStatementEvaluator,
			Evaluator<DOMVariableDefinitionStatement, Statement> variableDefinitionStatementEvaluator) {
		this.expressionStatementEvaluator = expressionStatementEvaluator;
		this.printStatementEvaluator = printStatementEvaluator;
		this.variableDefinitionStatementEvaluator = variableDefinitionStatementEvaluator;
	}

	@Override
	public Context<Statement> evaluate(Context<DOMStatement> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}
		
		DOMStatement statement = input.getValue();
		return statement.accept(new ConvertingDOMStatementVisitor());
	}
	
	private class ConvertingDOMStatementVisitor implements DOMStatementVisitor<Context<Statement>> {
		@Override
		public Context<Statement> visitVariableDefinitionStatement(DOMVariableDefinitionStatement statement) {
			return variableDefinitionStatementEvaluator.evaluate(Context.<DOMVariableDefinitionStatement>ok(statement));
		}

		@Override
		public Context<Statement> visitExpressionStatement(DOMExpressionStatement statement) {
			return expressionStatementEvaluator.evaluate(Context.<DOMExpressionStatement>ok(statement));
		}

		@Override
		public Context<Statement> visitPrintStatement(DOMPrintStatement statement) {
			return printStatementEvaluator.evaluate(Context.<DOMPrintStatement>ok(statement));
		}
	}

}
