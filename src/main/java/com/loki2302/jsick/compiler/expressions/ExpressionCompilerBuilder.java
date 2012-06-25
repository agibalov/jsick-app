package com.loki2302.jsick.compiler.expressions;

import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.compiler.model.expressions.Expression;

public class ExpressionCompilerBuilder {		
	private final Map<Class<? extends Expression>, AbstractExpressionCompiler<? extends Expression>> 
		compilersByExpressionClasses = 
			new HashMap<Class<? extends Expression>, AbstractExpressionCompiler<? extends Expression>>();
	
	public <E extends Expression> ExpressionCompilerBuilder registerCompiler(
			Class<E> expressionClass, 
			AbstractExpressionCompiler<E> expressionCompiler) {
		compilersByExpressionClasses.put(expressionClass, expressionCompiler);
		return this;
	}
	
	public ExpressionCompiler build() {
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(compilersByExpressionClasses);
		for(AbstractExpressionCompiler<? extends Expression> compiler : compilersByExpressionClasses.values()) {
			if(!(compiler instanceof RequiresExpressionCompiler)) {
				continue;
			}
			
			RequiresExpressionCompiler requiresExpressionCompiler = (RequiresExpressionCompiler)compiler;
			requiresExpressionCompiler.setExpressionCompiler(expressionCompiler);
		}
		
		return expressionCompiler;
	}
}