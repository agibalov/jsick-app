package com.loki2302.jsick.compiler.model.expressions;

import java.util.List;

import com.loki2302.jsick.compiler.SourceContextAware;

public abstract class Expression extends SourceContextAware {
	
	protected Expression(Object sourceContext) {
		super(sourceContext);
	}
	
	public abstract List<Expression> getDependencies();
	
}
