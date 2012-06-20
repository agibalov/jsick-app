package com.loki2302.jsick.nodes;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.ExecutionContext;

public class ProgramNode extends Node {
	
	private final List<StatementNode> statements = new ArrayList<StatementNode>();
	
	public ProgramNode() {
	}
	
	public boolean addStatement(StatementNode statement) {
		statements.add(statement);
		return true;
	}
	
	public Iterable<StatementNode> getStatements() {
		return statements;
	}
	
	public void execute() {
		ExecutionContext context = new ExecutionContext();
		for(StatementNode statement : statements) {
			statement.execute(context);
		}
	}
	
	@Override
	public String toString() {
		return String.format("Program{%d}", statements.size());
	}
	
}
