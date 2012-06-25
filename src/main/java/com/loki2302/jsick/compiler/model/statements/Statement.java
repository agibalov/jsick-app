package com.loki2302.jsick.compiler.model.statements;

import com.loki2302.jsick.compiler.SourceContextAware;

public abstract class Statement extends SourceContextAware {
	
	protected Statement(Object sourceContext) {
		super(sourceContext);
	}	
	
}
