package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.dom.expressions.DOMVariableReferenceExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.expressions.errors.UndefinedVariableError;
import com.loki2302.jsick.expressions.GetVariableValueExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Instance;

public class VariableReferenceExpressionEvaluator extends Evaluator<DOMVariableReferenceExpression, TypedExpression> {
	
	private final LexicalContext lexicalContext;
	
	public VariableReferenceExpressionEvaluator(LexicalContext lexicalContext) {
		this.lexicalContext = lexicalContext;
	}

	@Override
	public Context<TypedExpression> evaluate(DOMVariableReferenceExpression input) {		
		String variableName = input.getVariableName();
		if(!lexicalContext.variableExists(variableName)) {
			return Context.<TypedExpression>fail(new UndefinedVariableError(this, input));
		}			
		
		Instance instance = lexicalContext.getVariable(variableName);
		
		return ok(new GetVariableValueExpression(instance));
	}
}