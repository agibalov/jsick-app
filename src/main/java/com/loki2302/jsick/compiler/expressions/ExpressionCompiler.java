package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.errors.UnknownExpressionClassCompilationError;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class ExpressionCompiler extends AbstractExpressionCompiler<Expression> {
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
		if(expression instanceof IntLiteralExpression) {
			return intLiteralExpressionCompiler.compile((IntLiteralExpression)expression);
		} else if(expression instanceof DoubleLiteralExpression) {
			return doubleLiteralExpressionCompiler.compile((DoubleLiteralExpression)expression);
		} else if(expression instanceof VariableReferenceExpression) {
			return variableReferenceExpressionCompiler.compile((VariableReferenceExpression)expression);
		} else if(expression instanceof AddExpression) {
			return addExpressionCompiler.compile((AddExpression)expression);
		} else if(expression instanceof SubExpression) {
			return subExpressionCompiler.compile((SubExpression)expression);
		} else if(expression instanceof MulExpression) {
			return mulExpressionCompiler.compile((MulExpression)expression);
		} else if(expression instanceof DivExpression) {
			return divExpressionCompiler.compile((DivExpression)expression);
		}
		
		return ExpressionCompilationResult.error(new UnknownExpressionClassCompilationError(expression));
	}

}
