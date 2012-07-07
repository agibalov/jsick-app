package com.loki2302.jsick.types;

public class Instance {
	
	private final Type type;
	private final String name;
	
	Instance(Type type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

}
