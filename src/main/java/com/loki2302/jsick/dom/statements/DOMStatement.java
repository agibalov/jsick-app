package com.loki2302.jsick.dom.statements;

import org.parboiled.support.IndexRange;

import com.loki2302.jsick.dom.DOMNode;

public abstract class DOMStatement extends DOMNode {
	protected DOMStatement(IndexRange matchRange) {
		super(matchRange);
	}
	
	public abstract <T> T accept(DOMStatementVisitor<T> visitor);
}
