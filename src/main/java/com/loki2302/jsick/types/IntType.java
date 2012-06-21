package com.loki2302.jsick.types;

public class IntType implements JType {

	@Override
	public String getName() {		
		return "int";
	}

	@Override
	public JObject makeInstance() {
		return new IntObject(this);
	}

	@Override
	public boolean canImplicitlyCastTo(JType otherType) {
		return otherType instanceof DoubleType;
	}

	@Override
	public boolean canExplicitlyCastTo(JType otherType) {
		return canImplicitlyCastTo(otherType);
	}

}
