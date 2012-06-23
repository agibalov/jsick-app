package com.loki2302.jsick.compiler.errors;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.expressions.ExpressionCompilationResult;

public class DependencyHasErrorsCompilationError extends CompilationError {
	
	private final List<ExpressionCompilationResult> badResults = new ArrayList<ExpressionCompilationResult>();
	
	public DependencyHasErrorsCompilationError(List<? extends ExpressionCompilationResult> badResults) {
		this.badResults.addAll(badResults);
	}
	
	public DependencyHasErrorsCompilationError(ExpressionCompilationResult badResult) {
		this.badResults.add(badResult);
	}
	
}