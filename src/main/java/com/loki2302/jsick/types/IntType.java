package com.loki2302.jsick.types;

public class IntType implements Type {

	@Override
	public String getName() {		
		return "int";
	}

	@Override
	public boolean canImplicitlyCastTo(Type otherType) {
		return otherType instanceof DoubleType;
	}

	@Override
	public boolean canExplicitlyCastTo(Type otherType) {
		return canImplicitlyCastTo(otherType);
	}

}
