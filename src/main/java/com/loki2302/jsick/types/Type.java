package com.loki2302.jsick.types;

public interface Type {		
	
	String getName();
	boolean canCastTo(Type other);
	
}
