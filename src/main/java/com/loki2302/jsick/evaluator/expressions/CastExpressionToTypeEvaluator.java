package com.loki2302.jsick.evaluator.expressions;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.evaluator.expressions.errors.CannotCastError;
import com.loki2302.jsick.expressions.CastExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Type;

public class CastExpressionToTypeEvaluator<TInput> extends Evaluator<TInput, TypedExpression> {
	
	private final Evaluator<TInput, TypedExpression> expressionEvaluator; 
	private final Evaluator<TInput, Type> typeEvaluator;
	
	public CastExpressionToTypeEvaluator(
			Evaluator<TInput, TypedExpression> expressionEvaluator, 
			Evaluator<TInput, Type> typeEvaluator) {
		this.expressionEvaluator = expressionEvaluator;
		this.typeEvaluator = typeEvaluator;
	}

	@Override
	public Context<TypedExpression> evaluate(Context<TInput> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}
		
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
		
		if(!expressionContext.getValue().getType().canCastTo(typeContext.getValue())) {
			return fail(new CannotCastError(this, input));
		}
		
		TypedExpression castExpression = new CastExpression(expressionContext.getValue(), typeContext.getValue()); 
		
		return ok(castExpression);
	}

}
