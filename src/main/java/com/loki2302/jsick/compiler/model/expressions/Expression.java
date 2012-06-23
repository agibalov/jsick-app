package com.loki2302.jsick.compiler.model.expressions;

import com.loki2302.jsick.compiler.SourceContextAware;

public abstract class Expression extends SourceContextAware {
	
	protected Expression(Object sourceContext) {
		super(sourceContext);
	}
		
}
