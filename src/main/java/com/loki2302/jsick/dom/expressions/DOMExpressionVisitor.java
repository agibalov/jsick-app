package com.loki2302.jsick.dom.expressions;

public interface DOMExpressionVisitor<T> {
	T visitAddExpression(DOMAddExpression expression);
	T visitSubExpression(DOMSubExpression expression);
	T visitMulExpression(DOMMulExpression expression);
	T visitDivExpression(DOMDivExpression expression);
	T visitRemExpression(DOMRemExpression expression);
	T visitIntConstExpression(DOMIntConstExpression expression);
	T visitDoubleConstExpression(DOMDoubleConstExpression expression);
	T visitVariableReferenceExpression(DOMVariableReferenceExpression expression);
	T visitVariableAssignmentExpression(DOMVariableAssignmentExpression expression);
}