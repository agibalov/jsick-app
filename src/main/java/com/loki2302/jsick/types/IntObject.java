package com.loki2302.jsick.types;

public class IntObject implements JObject {

	private final IntType intType;
	
	public IntObject(IntType intType) {
		this.intType = intType;
	}
	
	@Override
	public JType getType() {
		return intType;
	}

}
