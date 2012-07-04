package com.loki2302.jsick.evaluator;

public class Tuple1<T1> {
	public Context<T1> first;
	
	public Tuple1(T1 v1) {
		first = Context.<T1>ok(v1);
	}
	
	public Tuple1(Context<T1> firstContext) {
		first = firstContext;
	}
	
	@Override
	public String toString() {
		return String.format("Tuple1{%s}", first);
	}
}