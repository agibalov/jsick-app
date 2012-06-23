package com.loki2302.jsick.compiler.expressions;


import com.loki2302.jsick.compiler.model.expressions.Expression;

public abstract class AbstractExpressionCompiler<E extends Expression> {	
	public abstract ExpressionCompilationResult compile(E expression);
}