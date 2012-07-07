package com.loki2302.jsick.types;

import java.util.Arrays;

public class SimpleType implements Type {
	
	private final String name;
	private final Type[] canCastTo;
	
	SimpleType(String name, Type... canCastTo) {
		this.name = name;
		this.canCastTo = canCastTo;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean canImplicitlyCastTo(Type other) {		
		return Arrays.asList(canCastTo).contains(other);
	}
	
	@Override
	public Instance makeInstance(String name) {
		return new Instance(this, name);
	}
	
	@Override
	public String toString() {
		return String.format("Type{%s}", getName());
	}
	
	@Override
	public boolean equals(Object other) {
		return other == this;
	}
	
}
