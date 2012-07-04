package com.loki2302.jsick.evaluator;

import com.loki2302.jsick.evaluator.errors.AbstractError;

public class Context<T> {    	
	private T value;
	private AbstractError error;
	
	public Context() {		
	}
	
	public T getValue() {
		return value;
	}
	
	public boolean isOk() {
		return error == null;
	}
	
	public AbstractError getError() {
		return error;
	}
		
	@Override
	public String toString() {
		if(isOk()) {
			return String.format("Context{%s}", value);
		}
		
		return String.format("Context{error=%s}", getError());		
	}
	
	public static <T> Context<T> ok(T value) {		
		Context<T> context = new Context<T>();
		context.value = value;
		return context;
	}
	
	public static <T> Context<T> fail(AbstractError error) {
		Context<T> context = new Context<T>();
		context.error = error;
		return context;
	}
}