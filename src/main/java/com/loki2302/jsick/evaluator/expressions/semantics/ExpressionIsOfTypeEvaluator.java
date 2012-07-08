package com.loki2302.jsick.evaluator.expressions.semantics;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.evaluator.expressions.errors.ExpressionIsNotOfType;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.types.Type;

public class ExpressionIsOfTypeEvaluator<TInput> extends Evaluator<TInput, Expression> {
	
	private final Evaluator<TInput, Expression> expressionEvaluator; 
	private final Evaluator<TInput, Type> typeEvaluator;
	
	public ExpressionIsOfTypeEvaluator(
			Evaluator<TInput, Expression> expressionEvaluator, 
			Evaluator<TInput, Type> typeEvaluator) {
		this.expressionEvaluator = expressionEvaluator;
		this.typeEvaluator = typeEvaluator;
	}

	@Override
	public Context<Expression> evaluate(TInput input) {		
		List<AbstractError> errors = new ArrayList<AbstractError>();
		
		Context<Expression> expressionContext = expressionEvaluator.evaluate(input);
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
		
		Expression expression = expressionContext.getValue();
		Type type = typeContext.getValue();
		
		if(!expression.getType().equals(type)) {
			return fail(new ExpressionIsNotOfType(this, input, expression, type));
		}
		
		return ok(expression);
	}

}
