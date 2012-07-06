package com.loki2302.jsick.evaluator;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;

public class MakeTuple3Evaluator<T1, T2, T3, TInput> extends Evaluator<TInput, Tuple3<T1, T2, T3>> {
	
	private final Evaluator<TInput, T1> evaluator1;
	private final Evaluator<TInput, T2> evaluator2;
	private final Evaluator<TInput, T3> evaluator3;
	
	public MakeTuple3Evaluator(
			Evaluator<TInput, T1> evaluator1,
			Evaluator<TInput, T2> evaluator2,
			Evaluator<TInput, T3> evaluator3) {
		this.evaluator1 = evaluator1;
		this.evaluator2 = evaluator2;
		this.evaluator3 = evaluator3;
	}

	@Override
	public Context<Tuple3<T1, T2, T3>> evaluate(TInput input) {				
		List<AbstractError> errors = new ArrayList<AbstractError>();
		
		Context<T1> outputContext1 = evaluator1.evaluate(input);
		if(!outputContext1.isOk()) {
			errors.add(outputContext1.getError());
		}
		
		Context<T2> outputContext2 = evaluator2.evaluate(input);
		if(!outputContext2.isOk()) {
			errors.add(outputContext2.getError());
		}
		
		Context<T3> outputContext3 = evaluator3.evaluate(input);
		if(!outputContext3.isOk()) {
			errors.add(outputContext3.getError());
		}
		
		if(!errors.isEmpty()) {
			return fail(new CompositeError(this, input, errors));
		}
				
		return ok(new Tuple3<T1, T2, T3>(
				outputContext1.getValue(), 
				outputContext2.getValue(), 
				outputContext3.getValue()));
	}
	
}