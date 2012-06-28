package com.loki2302.jsick.compiler.expressions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loki2302.jsick.compiler.errors.DependencyHasErrorsCompilationError;
import com.loki2302.jsick.compiler.model.expressions.Expression;

public abstract class AbstractExpressionCompiler<E extends Expression> implements RequiresExpressionCompiler {
	
	private ExpressionCompiler expressionCompiler;
	
	@Override
	public void setExpressionCompiler(ExpressionCompiler expressionCompiler) {
		this.expressionCompiler = expressionCompiler;
	}

	public ExpressionCompilationResult compile(E expression) {		
		List<Expression> dependencies = expression.getDependencies();
		Map<Expression, ExpressionCompilationResult> results = new HashMap<Expression, ExpressionCompilationResult>();
		
		if(dependencies != null) {
			for(Expression dependency : dependencies) {
				ExpressionCompilationResult result = expressionCompiler.compile(dependency);
				results.put(dependency, result);
			}
		}
		
		List<ExpressionCompilationResult> badResults = new ArrayList<ExpressionCompilationResult>();
		for(ExpressionCompilationResult result : results.values()) {
			if(result.hasErrors()) {
				badResults.add(result);
			}
		}
		
		if(!badResults.isEmpty()) {
			return ExpressionCompilationResult.error(new DependencyHasErrorsCompilationError(badResults, expression)); 
		}
		
		PrecompilationResults precompilationResults = new PrecompilationResults(results);
	
		return compileImpl(expression, precompilationResults);
	}
	
	protected abstract ExpressionCompilationResult compileImpl(E expression, PrecompilationResults precompilationResults);
}