package com.loki2302.jsick.evaluator.expressions;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMAssignmentExpression;
import com.loki2302.jsick.dom.expressions.DOMVariableReferenceExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.evaluator.expressions.errors.ExpressionIsNotLvalueError;
import com.loki2302.jsick.evaluator.expressions.errors.UndefinedVariableError;
import com.loki2302.jsick.expressions.SetVariableValueExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Type;

public class AssignmentExpressionEvaluator extends Evaluator<DOMAssignmentExpression, TypedExpression> {
	
	private final Evaluator<DOMExpression, TypedExpression> expressionEvaluator;
	private final LexicalContext lexicalContext;
	private final Evaluator<Tuple2<TypedExpression, Type>, TypedExpression> makeSureExpressionIsOfTypeEvaluator;
	
	public AssignmentExpressionEvaluator(
			LexicalContext lexicalContext, 
			Evaluator<DOMExpression, TypedExpression> expressionEvaluator,
			Evaluator<Tuple2<TypedExpression, Type>, TypedExpression> makeSureExpressionIsOfTypeEvaluator) {
		this.lexicalContext = lexicalContext;
		this.expressionEvaluator = expressionEvaluator;
		this.makeSureExpressionIsOfTypeEvaluator = makeSureExpressionIsOfTypeEvaluator;
	}

	@Override
	public Context<TypedExpression> evaluate(DOMAssignmentExpression input) {
		List<AbstractError> errors = new ArrayList<AbstractError>();
		
		DOMExpression left = input.getLeft();
		Instance instance = null;
		if(!(left instanceof DOMVariableReferenceExpression)) {
			errors.add(new ExpressionIsNotLvalueError(this, input));
		} else {
			DOMVariableReferenceExpression domVariableReferenceExpression = (DOMVariableReferenceExpression)left;
			String variableName = domVariableReferenceExpression.getVariableName();
			if(!lexicalContext.variableExists(variableName)) {
				errors.add(new UndefinedVariableError(this, input));
			} else {
				instance = lexicalContext.getVariable(variableName);
			}
		}
		
		DOMExpression right = input.getRight();		
		Context<TypedExpression> expressionContext = expressionEvaluator.evaluate(right);
		TypedExpression expression = null;
		if(!expressionContext.isOk()) {
			errors.add(expressionContext.getError());
		} else if(instance != null) {
			Context<TypedExpression> castExpressionContext = makeSureExpressionIsOfTypeEvaluator.evaluate(
					new Tuple2<TypedExpression, Type>(expressionContext.getValue(), instance.getType()));
			if(!castExpressionContext.isOk()) {
				errors.add(castExpressionContext.getError());
			} else {
				expression = castExpressionContext.getValue();
			}
		}
		
		if(!errors.isEmpty()) {
			return fail(new CompositeError(this, input, errors));
		}		
		
		return ok(new SetVariableValueExpression(instance, expression));
	}

}
