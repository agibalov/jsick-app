package com.loki2302.jsick.compiler.model;

public class SimpleType extends Type {

	private final String name;
	
	public SimpleType(String name, Object sourceContext) {
		super(sourceContext);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
