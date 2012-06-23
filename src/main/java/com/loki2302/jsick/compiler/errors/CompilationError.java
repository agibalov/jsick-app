package com.loki2302.jsick.compiler.errors;

import com.loki2302.jsick.compiler.SourceContextAware;

public abstract class CompilationError extends SourceContextAware {
	protected CompilationError(Object sourceContext) {
		super(sourceContext);
	}		
}