package com.loki2302.jsick.compiler.typeevaluation;

import com.loki2302.jsick.types.Type;

public class Fluency {
	
	public static TypeEvaluator first() {
    	return new FirstTypeEvaluationContextAccessorTypeEvaluator();
    }
    
    public static TypeEvaluator second() {
    	return new SecondTypeEvaluationContextAccessorTypeEvaluator();
    }
    
    public static TypeEvaluator or(TypeEvaluator... constrainedTypes) {
    	return new OrTypeEvaluator(constrainedTypes);
    }
        
    public static TypeEvaluator fixed(Type type) {
    	return new FixedTypeEvaluator(type);
    }
    
    public static TypeEvaluator same(TypeEvaluator first, TypeEvaluator second) {
    	return new SameTypeEvaluator(first, second);
    }
    
    public static TypeEvaluator castable(TypeEvaluator from, TypeEvaluator to) {
    	return new CastTypeEvaluator(from, to);
    }
    
}
