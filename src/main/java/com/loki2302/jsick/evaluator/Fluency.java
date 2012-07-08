package com.loki2302.jsick.evaluator;

public class Fluency { 
    
    public static <TInput, TOutput> OrEvaluator<TInput, TOutput> or(Evaluator<TInput, TOutput>... evaluators) {
    	return new OrEvaluator<TInput, TOutput>(evaluators);
    }
    
}