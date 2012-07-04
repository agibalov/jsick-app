package com.loki2302.jsick.evaluator;


public class Fluency {

    public static <T1, T2, T3, TInput> MakeTuple3Evaluator<T1, T2, T3, TInput> tuple3(
    		Evaluator<TInput, T1> evaluator1,
			Evaluator<TInput, T2> evaluator2,
			Evaluator<TInput, T3> evaluator3) {
    	return new MakeTuple3Evaluator<T1, T2, T3, TInput>(evaluator1, evaluator2, evaluator3);
    }
    
    public static <T, TInput extends Tuple1<T>> GetFirstEvaluator<T, TInput> first(Class<TInput> clazz) {
    	return new GetFirstEvaluator<T, TInput>();
    }
    
    public static <T, TInput extends Tuple2<?, T>> GetSecondEvaluator<T, TInput> second(Class<TInput> clazz) {
    	return new GetSecondEvaluator<T, TInput>();
    }
    
    public static <T, TInput extends Tuple3<?, ?, T>> GetThirdEvaluator<T, TInput> third(Class<TInput> clazz) {
    	return new GetThirdEvaluator<T, TInput>();
    }
    
    public static <TInput, TOutput> OrEvaluator<TInput, TOutput> or(Evaluator<TInput, TOutput>... evaluators) {
    	return new OrEvaluator<TInput, TOutput>(evaluators);
    }
}