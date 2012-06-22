package com.loki2302.jsick.model;

import java.util.List;

public class Program {
	
	private List<Statement> statements;
	
	public Program(List<Statement> statements) {
		this.statements = statements;
	}
		
	public List<Statement> getStatements() {
		return statements;
	}

}
