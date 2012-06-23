package com.loki2302.jsick.compiler.model;

import java.util.List;

import com.loki2302.jsick.compiler.model.statements.Statement;

public class Program {
	
	private List<Statement> statements;
	
	public Program(List<Statement> statements) {
		this.statements = statements;
	}
		
	public List<Statement> getStatements() {
		return statements;
	}

}
