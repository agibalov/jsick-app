package com.loki2302.jsick.dom;

import java.util.ArrayList;
import java.util.List;

import org.parboiled.support.IndexRange;

import com.loki2302.jsick.dom.statements.DOMStatement;

public class DOMProgram extends DOMNode {
	
	private final List<DOMStatement> statements = new ArrayList<DOMStatement>();

	public DOMProgram(IndexRange indexRange) {
		super(indexRange);
	}
	
	public boolean addStatement(DOMStatement statement) {
		statements.add(statement);
		return true;
	}
	
	public Iterable<DOMStatement> getStatements() {
		return statements;
	}

}
