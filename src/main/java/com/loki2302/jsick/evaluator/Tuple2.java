package com.loki2302.jsick.evaluator;

public class Tuple2<T1, T2> extends Tuple1<T1> {
	public Context<T2> second;
	
	public Tuple2(T1 v1, T2 v2) {
		super(v1);
		second = Context.<T2>ok(v2);
	}
	
	@Override
	public String toString() {
		return String.format("Tuple2{%s,%s}", first, second);
	}
}