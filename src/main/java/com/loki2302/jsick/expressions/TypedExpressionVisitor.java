package com.loki2302.jsick.expressions;

public interface TypedExpressionVisitor<T> {
	T visitIntConstExpression(IntConstExpression expression);
	T visitDoubleConstExpression(DoubleConstExpression expression);
	T visitCastExpression(CastExpression expression);
	T visitAddExpression(AddExpression expression);
	T visitSubExpression(SubExpression expression);
	T visitMulExpression(MulExpression expression);
	T visitDivExpression(DivExpression expression);
	T visitRemExpression(RemExpression expression);
	T visitGetVariableValueExpression(GetVariableValueExpression expression);
	T visitSetVariableValueExpression(SetVariableValueExpression expression);
}