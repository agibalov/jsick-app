package com.loki2302.jsick.dom;

import org.parboiled.support.IndexRange;

public abstract class DOMNode {
	private final IndexRange matchRange;
	
	protected DOMNode(IndexRange matchRange) {
		this.matchRange = matchRange;
	}
	
	public IndexRange getMatchRange() {
		return matchRange;
	}
	
}