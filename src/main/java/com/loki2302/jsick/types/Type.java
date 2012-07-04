package com.loki2302.jsick.types;

public interface Type {

	String getName();
		
	boolean canImplicitlyCastTo(Type otherType);
	boolean canExplicitlyCastTo(Type otherType);
	
}