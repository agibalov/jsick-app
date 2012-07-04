package com.loki2302.jsick.types;

public class Types {
	
	public final Type DoubleType = new SimpleType("double");
	public final Type IntType = new SimpleType("int", DoubleType);
	public final Type VoidType = new SimpleType("void");

}
