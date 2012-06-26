package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class SimpleTypeNode extends TypeNode {

	private final String typeName;
	
	public SimpleTypeNode(String typeName, IndexRange indexRange) {
		super(indexRange);
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}

}
