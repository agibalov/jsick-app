package com.loki2302.jsick.types;

import java.util.Arrays;

public class SimpleType implements Type {
	
	private final String name;
	private final Type[] canCastTo;
	
	public SimpleType(String name, Type... canCastTo) {
		this.name = name;
		this.canCastTo = canCastTo;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean canCastTo(Type other) {		
		return Arrays.asList(canCastTo).contains(other);
	}		
	
	@Override
	public String toString() {
		return String.format("Type{%s}", getName());
	}
	
	@Override
	public boolean equals(Object other) {
		return (other instanceof Type) && (((Type)other).getName().equals(getName()));
	}
	
}
