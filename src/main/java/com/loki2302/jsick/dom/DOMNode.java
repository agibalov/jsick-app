package com.loki2302.jsick.dom;

import org.parboiled.support.IndexRange;

public abstract class DOMNode {
	private final IndexRange indexRange;
	
	protected DOMNode(IndexRange indexRange) {
		this.indexRange = indexRange;
	}
	
	public IndexRange getIndexRange() {
		return indexRange;
	}
	
}