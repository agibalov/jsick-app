package com.loki2302.jsick.statements;

public abstract class Statement {	
	public abstract <T> T accept(StatementVisitor<T> visitor);
}
