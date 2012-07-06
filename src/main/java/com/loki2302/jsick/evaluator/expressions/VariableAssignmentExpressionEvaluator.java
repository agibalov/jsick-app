package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMVariableAssignmentExpression;
import com.loki2302.jsick.dom.expressions.DOMVariableReferenceExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.SetVariableValueExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Instance;

public class VariableAssignmentExpressionEvaluator extends Evaluator<DOMVariableAssignmentExpression, TypedExpression> {
	
	private final LexicalContext lexicalContext;
	
	public VariableAssignmentExpressionEvaluator(LexicalContext lexicalContext) {
		this.lexicalContext = lexicalContext;
	}

	@Override
	public Context<TypedExpression> evaluate(DOMVariableAssignmentExpression input) {
		DOMVariableReferenceExpression domVariableReferenceExpression = input.getLeft();
		String variableName = domVariableReferenceExpression.getVariableName();
		Instance instance = lexicalContext.getVariable(variableName);
		
		DOMExpression domExpression = input.getRight();
		TypedExpression expression = null; // TODO
		if(true) {
			throw new RuntimeException();
		}
		
		return ok(new SetVariableValueExpression(instance, expression));
	}

}
