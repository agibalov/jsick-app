package com.loki2302.jsick.evaluator;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.evaluator.errors.NoViableAlternativeError;

public class OrEvaluator<TInput, TOutput> extends Evaluator<TInput, TOutput> {
	
	private final Evaluator<TInput, TOutput>[] evaluators;
	
	public OrEvaluator(Evaluator<TInput, TOutput>[] evaluators) {
		this.evaluators = evaluators;
	}

	@Override
	public Context<TOutput> evaluate(Context<TInput> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}
		
		List<AbstractError> errors = new ArrayList<AbstractError>();
		for(Evaluator<TInput, TOutput> evaluator : evaluators) {
			Context<TOutput> outputContext = evaluator.evaluate(input);
			if(outputContext.isOk()) {
				return outputContext;
			}
			
			errors.add(outputContext.getError());
		}	
		
		return fail(new NoViableAlternativeError(this, input, errors));
	}
}
