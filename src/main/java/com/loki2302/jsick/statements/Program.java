package com.loki2302.jsick.statements;

import java.util.List;

public class Program {
	
	private final List<Statement> statements;
	
	public Program(List<Statement> statements) {
		this.statements = statements;
	}
	
	public Iterable<Statement> getStatements() {
		return statements;
	}

}
