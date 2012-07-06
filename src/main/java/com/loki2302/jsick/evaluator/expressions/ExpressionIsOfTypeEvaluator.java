package com.loki2302.jsick.evaluator.expressions;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.evaluator.expressions.errors.TypesAreDifferentError;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class ExpressionIsOfTypeEvaluator<TInput> extends Evaluator<TInput, TypedExpression> {
	
	private final Evaluator<TInput, TypedExpression> expressionEvaluator; 
	private final Evaluator<TInput, Type> typeEvaluator;
	
	public ExpressionIsOfTypeEvaluator(
			Evaluator<TInput, TypedExpression> expressionEvaluator, 
			Evaluator<TInput, Type> typeEvaluator) {
		this.expressionEvaluator = expressionEvaluator;
		this.typeEvaluator = typeEvaluator;
	}

	@Override
	public Context<TypedExpression> evaluate(TInput input) {		
		List<AbstractError> errors = new ArrayList<AbstractError>();
		
		Context<TypedExpression> expressionContext = expressionEvaluator.evaluate(input);
		if(!expressionContext.isOk()) {
			errors.add(expressionContext.getError());
		}
		
		Context<Type> typeContext = typeEvaluator.evaluate(input);
		if(!typeContext.isOk()) {
			errors.add(typeContext.getError());
		}
		
		if(!errors.isEmpty()) {
			return fail(new CompositeError(this, input, errors));
		}
		
		Context<TypedExpression> expression = expressionContext;
		Context<Type> type = typeContext;
		
		if(!expression.getValue().getType().equals(type.getValue())) {
			return fail(new TypesAreDifferentError(this, input));
		}
		
		return ok(expression.getValue());
	}

}
