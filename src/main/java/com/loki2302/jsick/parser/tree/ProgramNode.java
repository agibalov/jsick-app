package com.loki2302.jsick.parser.tree;

import java.util.ArrayList;
import java.util.List;

import org.parboiled.support.IndexRange;


public class ProgramNode extends Node {
	
	private final List<StatementNode> statements = new ArrayList<StatementNode>();
	
	public ProgramNode(IndexRange indexRange) {
		super(indexRange);
	}
	
	public boolean addStatement(StatementNode statement) {
		statements.add(statement);
		return true;
	}
	
	public Iterable<StatementNode> getStatements() {
		return statements;
	}
		
	@Override
	public String toString() {
		return String.format("Program{%d}", statements.size());
	}
	
}
