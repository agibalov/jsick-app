package com.loki2302.jsick.compiler.expressions;

import java.util.Map;

import com.loki2302.jsick.compiler.errors.UnknownExpressionClassCompilationError;
import com.loki2302.jsick.compiler.model.expressions.Expression;

public class ExpressionCompiler extends AbstractExpressionCompiler<Expression> {
	
	private final Map<Class<? extends Expression>, AbstractExpressionCompiler<? extends Expression>> 
		compilersByExpressionClasses;
	
	public ExpressionCompiler(Map<Class<? extends Expression>, AbstractExpressionCompiler<? extends Expression>> compilersByExpressionClasses) {
		this.compilersByExpressionClasses = compilersByExpressionClasses;
	}
	
	public ExpressionCompilationResult compile(Expression expression) {
		AbstractExpressionCompiler<Expression> compiler = 
				(AbstractExpressionCompiler<Expression>)compilersByExpressionClasses.get(expression.getClass());
		if(compiler == null) {
			return ExpressionCompilationResult.error(new UnknownExpressionClassCompilationError(expression));
		}
		
		return compiler.compile(expression);
	}

}
