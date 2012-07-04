package com.loki2302.jsick.dom.expressions;


public interface DOMExpressionVisitor<T> {
	T visitDOMAddExpression(DOMAddExpression expression);
	T visitDOMSubExpression(DOMSubExpression expression);
	T visitDOMMulExpression(DOMMulExpression expression);
	T visitDOMDivExpression(DOMDivExpression expression);
	T visitDOMRemExpression(DOMRemExpression expression);
	T visitDOMIntConstExpression(DOMIntConstExpression expression);
	T visitDOMDoubleConstExpression(DOMDoubleConstExpression expression);
	T visitDOMVariableReferenceExpression(DOMVariableReferenceExpression expression);
}