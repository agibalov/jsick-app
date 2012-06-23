package com.loki2302.jsick.parser.tree;

import org.parboiled.support.IndexRange;

public class ArithmExpressionNode extends ExpressionNode {
	
	private final ExpressionNode a;
	private final ExpressionNode b;
	private final Operation op;
	
	public ArithmExpressionNode(ExpressionNode a, ExpressionNode b, Operation op, IndexRange indexRange) {
		super(indexRange);
		this.a = a;
		this.b = b;
		this.op = op;
	}
	
	public ExpressionNode getA() {
		return a;
	}
	
	public ExpressionNode getB() {
		return b;
	}
	
	public Operation getOperation() {
		return op;
	}
	
	public static Operation operationFromChar(char c) {
		if(c == '+') {
			return Operation.Add;
		} else if(c == '-') {
			return Operation.Sub;
		} else if(c == '*') {
			return Operation.Mul;
		} else if(c == '/') {
			return Operation.Div;
		}
		
		throw new RuntimeException();
	}
	
	public static enum Operation {
		Add,
		Sub,
		Mul,
		Div	
	}

}
