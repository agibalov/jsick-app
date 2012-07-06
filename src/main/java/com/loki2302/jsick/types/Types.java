package com.loki2302.jsick.types;

public class Types {
	
	public final Type DoubleType = new SimpleType("double");
	public final Type IntType = new SimpleType("int", DoubleType);
	public final Type VoidType = new SimpleType("void");
	
	public Type getTypeByName(String typeName) {
		if(typeName.equals("double")) {
			return DoubleType;
		} 
		
		if(typeName.equals("int")) {
			return IntType;
		}
		
		if(typeName.equals("void")) {
			return VoidType;
		}
		
		return null;
	}

}
