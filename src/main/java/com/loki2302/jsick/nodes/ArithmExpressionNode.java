package com.loki2302.jsick.nodes;

import com.loki2302.jsick.ExecutionContext;

public class ArithmExpressionNode extends ExpressionNode {
	
	private final ExpressionNode a;
	private final ExpressionNode b;
	private final Operation op;
	
	public ArithmExpressionNode(ExpressionNode a, ExpressionNode b, Operation op) {
		this.a = a;
		this.b = b;
		this.op = op;
	}

	@Override
	public int getValue(ExecutionContext context) {
		if(op == Operation.Add) {
			return a.getValue(context) + b.getValue(context);
		} else if(op == Operation.Sub) {
			return a.getValue(context) - b.getValue(context);
		} else if(op == Operation.Mul) {
			return a.getValue(context) * b.getValue(context);
		} else if(op == Operation.Div) {
			return a.getValue(context) / b.getValue(context);
		}
		
		throw new RuntimeException();		
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
