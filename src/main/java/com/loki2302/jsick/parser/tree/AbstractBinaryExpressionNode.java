package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public abstract class AbstractBinaryExpressionNode extends ExpressionNode {
	
	private final ExpressionNode a;
	private final ExpressionNode b;
	
	public AbstractBinaryExpressionNode(ExpressionNode a, ExpressionNode b, IndexRange indexRange) {
		super(indexRange);
		this.a = a;
		this.b = b;
	}
	
	public ExpressionNode getA() {
		return a;
	}
	
	public ExpressionNode getB() {
		return b;
	}
		
	public static AbstractBinaryExpressionNode expressionFromChar(char c, ExpressionNode left, ExpressionNode right, IndexRange indexRange) {
		if(c == '+') {
			return new AddExpressionNode(left, right, indexRange);
		} else if(c == '-') {
			return new SubExpressionNode(left, right, indexRange);
		} else if(c == '*') {
			return new MulExpressionNode(left, right, indexRange);
		} else if(c == '/') {
			return new DivExpressionNode(left, right, indexRange);
		} else if(c == '%') {
			return new RemExpressionNode(left, right, indexRange);
		}
		
		throw new RuntimeException();
	}
}
