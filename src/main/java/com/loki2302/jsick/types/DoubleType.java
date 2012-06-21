package com.loki2302.jsick.types;

public class DoubleType implements JType {

	@Override
	public String getName() {
		return "double";
	}

	@Override
	public JObject makeInstance() {		
		return new DoubleObject(this);
	}

	@Override
	public boolean canImplicitlyCastTo(JType otherType) {		
		return false;
	}

	@Override
	public boolean canExplicitlyCastTo(JType otherType) {
		return otherType instanceof IntType;
	}

}
