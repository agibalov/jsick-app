package com.loki2302.jsick.types;

public class DoubleObject implements JObject {

	private final DoubleType doubleType;
	
	public DoubleObject(DoubleType doubleType) {
		this.doubleType = doubleType;
	}
	
	@Override
	public JType getType() {
		return doubleType;
	}

}
