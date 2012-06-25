package com.loki2302.jsick.compiler.model.expressions;

public interface ExpressionVisitor<T> {
	
	T visitAddExpression(AddExpression expression);
	T visitSubExpression(SubExpression expression);
	T visitMulExpression(MulExpression expression);
	T visitDivExpression(DivExpression expression);
	T visitIntLiteralExpression(IntLiteralExpression expression);
	T visitDoubleLiteralExpression(DoubleLiteralExpression expression);
	T visitVariableReferenceExpression(VariableReferenceExpression expression);

}
