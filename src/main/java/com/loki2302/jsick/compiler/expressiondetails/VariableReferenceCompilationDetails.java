package com.loki2302.jsick.compiler.expressiondetails;

import com.loki2302.jsick.types.JType;

public class VariableReferenceCompilationDetails extends ExpressionCompilationDetails {

	private VariableReferenceCompilationDetails(JType type) {
		super(type);
	}

	@Override
	public boolean hasErrors() {
		return getType() == null;
	}
	
	public static VariableReferenceCompilationDetails ok(JType type) {
		return new VariableReferenceCompilationDetails(type);
	}
	
	public static VariableReferenceCompilationDetails noSuchVariable() {
		return new VariableReferenceCompilationDetails(null);
	}

}