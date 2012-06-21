package com.loki2302.jsick.types;

public interface JType {

	String getName();
	
	JObject makeInstance();
	
	boolean canImplicitlyCastTo(JType otherType);
	boolean canExplicitlyCastTo(JType otherType);
	
}
