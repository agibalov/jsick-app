package com.loki2302.jsick.compiler.expressiondetails;

import com.loki2302.jsick.types.JType;

public class LiteralExpressionCompilationDetails extends ExpressionCompilationDetails {

	private LiteralExpressionCompilationDetails(JType type) {
		super(type);
	}

	@Override
	public boolean hasErrors() {
		return getType() == null;
	}
	
	public static LiteralExpressionCompilationDetails ok(JType type) {
		return new LiteralExpressionCompilationDetails(type);
	}
	
	public static LiteralExpressionCompilationDetails cantUnderstandLiteral() {
		return new LiteralExpressionCompilationDetails(null);
	}

}