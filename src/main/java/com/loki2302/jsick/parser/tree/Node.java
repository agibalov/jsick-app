package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public abstract class Node {
	
	private final IndexRange indexRange;
	
	protected Node(IndexRange indexRange) {
		this.indexRange = indexRange;
	}
	
	public IndexRange getMatchRange() {
		return indexRange;
	}
	
}
