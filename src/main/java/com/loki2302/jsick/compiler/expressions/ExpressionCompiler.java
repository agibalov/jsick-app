package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.ExpressionVisitor;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class ExpressionCompiler extends AbstractExpressionCompiler<Expression> implements ExpressionVisitor<ExpressionCompilationResult> {
	private final IntLiteralExpressionCompiler intLiteralExpressionCompiler;
	private final DoubleLiteralExpressionCompiler doubleLiteralExpressionCompiler;
	private final VariableReferenceExpressionCompiler variableReferenceExpressionCompiler; 
	private final AddExpressionCompiler addExpressionCompiler;
	private final SubExpressionCompiler subExpressionCompiler;
	private final MulExpressionCompiler mulExpressionCompiler;
	private final DivExpressionCompiler divExpressionCompiler;
	
	public ExpressionCompiler(IntType intType, DoubleType doubleType, LexicalContext lexicalContext) {
		this.intLiteralExpressionCompiler = new IntLiteralExpressionCompiler(intType);
		this.doubleLiteralExpressionCompiler = new DoubleLiteralExpressionCompiler(doubleType);
		this.variableReferenceExpressionCompiler = new VariableReferenceExpressionCompiler(lexicalContext); 
		this.addExpressionCompiler = new AddExpressionCompiler(this, intType, doubleType);
		this.subExpressionCompiler = new SubExpressionCompiler(this, intType, doubleType);
		this.mulExpressionCompiler = new MulExpressionCompiler(this, intType, doubleType);
		this.divExpressionCompiler = new DivExpressionCompiler(this, intType, doubleType);
	}
	
	public ExpressionCompilationResult compile(Expression expression) {
		return expression.accept(this);
	}

	@Override
	public ExpressionCompilationResult visitAddExpression(AddExpression expression) {
		return addExpressionCompiler.compile(expression);
	}

	@Override
	public ExpressionCompilationResult visitSubExpression(SubExpression expression) {
		return subExpressionCompiler.compile(expression);
	}

	@Override
	public ExpressionCompilationResult visitMulExpression(MulExpression expression) {
		return mulExpressionCompiler.compile(expression);
	}

	@Override
	public ExpressionCompilationResult visitDivExpression(DivExpression expression) {
		return divExpressionCompiler.compile(expression);
	}

	@Override
	public ExpressionCompilationResult visitIntLiteralExpression(IntLiteralExpression expression) {
		return intLiteralExpressionCompiler.compile(expression);
	}

	@Override
	public ExpressionCompilationResult visitDoubleLiteralExpression(DoubleLiteralExpression expression) {
		return doubleLiteralExpressionCompiler.compile(expression);
	}

	@Override
	public ExpressionCompilationResult visitVariableReferenceExpression(VariableReferenceExpression expression) {
		return variableReferenceExpressionCompiler.compile(expression);
	}

}
