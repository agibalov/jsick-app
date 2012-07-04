package com.loki2302.jsick.evaluator;

public class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {
	public Context<T3> third;
	
	public Tuple3(T1 v1, T2 v2, T3 v3) {
		super(v1, v2);
		third = Context.<T3>ok(v3);
	}
	
	@Override
	public String toString() {
		return String.format("Tuple3{%s,%s,%s}", first, second, third);
	}
}