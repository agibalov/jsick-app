package com.loki2302.jsick.evaluator;

public class Tuple1<T1> {
	public T1 first;
	
	public Tuple1(T1 v1) {
		first = v1;
	}
		
	@Override
	public String toString() {
		return String.format("Tuple1{%s}", first);
	}
}