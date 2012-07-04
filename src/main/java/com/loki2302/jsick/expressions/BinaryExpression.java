package com.loki2302.jsick.expressions;

import com.loki2302.jsick.types.Type;

public abstract class BinaryExpression implements TypedExpression {
	private final TypedExpression left;
	private final TypedExpression right;
	private final Type resultType;
	
	protected BinaryExpression(TypedExpression left, TypedExpression right, Type resultType) {
		this.left = left;
		this.right = right;
		this.resultType = resultType;
	}
	
	public TypedExpression getLeft() {
		return left;
	}
	
	public TypedExpression getRight() {
		return right;
	}
	
	@Override
	public Type getType() {
		return resultType;
	}
}
