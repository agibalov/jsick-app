package com.loki2302.jsick.types;

public class DoubleType implements Type {

	@Override
	public String getName() {
		return "double";
	}

	@Override
	public boolean canImplicitlyCastTo(Type otherType) {		
		return false;
	}

	@Override
	public boolean canExplicitlyCastTo(Type otherType) {
		return otherType instanceof IntType;
	}

}
