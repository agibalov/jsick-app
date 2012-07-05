package com.loki2302.jsick.compiler;

import com.loki2302.jsick.dom.statements.DOMStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.statements.Statement;

public class StatementCompiler {
	private final Evaluator<DOMStatement, Statement> statementEvaluator; 

	public StatementCompiler(Evaluator<DOMStatement, Statement> statementEvaluator) {
		this.statementEvaluator = statementEvaluator;
	}
	
	public Context<Statement> compile(DOMStatement domStatement) {
		return statementEvaluator.evaluate(Context.<DOMStatement>ok(domStatement));
	}
}
