package com.loki2302.jsick.compiler;

import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class CompilationContext {
	
	private final LexicalContext lexicalContext;
	private final IntType intType;
	private final DoubleType doubleType;
		
	public CompilationContext(LexicalContext lexicalContext, IntType intType, DoubleType doubleType) {
		this.lexicalContext = lexicalContext;
		this.intType = intType;
		this.doubleType = doubleType;
	}
	
	public LexicalContext getLexicalContext() {
		return lexicalContext;
	}
	
	public IntType getIntType() {
		return intType;
	}
	
	public DoubleType getDoubleType() {
		return doubleType;
	}

}
