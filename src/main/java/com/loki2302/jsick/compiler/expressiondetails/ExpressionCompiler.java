package com.loki2302.jsick.compiler.expressiondetails;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.LiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.types.JType;

public class ExpressionCompiler {
	
	private final LexicalContext lexicalContext;
	private final IntType intType;
	private final DoubleType doubleType;	
	
	public ExpressionCompiler(LexicalContext lexicalContext, IntType intType, DoubleType doubleType) {
		this.lexicalContext = lexicalContext;
		this.intType = intType;
		this.doubleType = doubleType;
	}
	
	public ExpressionCompilationDetails analyze(Expression expression) {
		if(expression instanceof IntLiteralExpression) {
			LiteralExpression litExpression = (LiteralExpression)expression;
			String stringValue = litExpression.getValue();
				
			try {
				Integer.parseInt(stringValue);
				return LiteralExpressionCompilationDetails.ok(intType);
			} catch(NumberFormatException e) {
				return LiteralExpressionCompilationDetails.cantUnderstandLiteral();
			}
		} else if(expression instanceof DoubleLiteralExpression) {
			LiteralExpression litExpression = (LiteralExpression)expression;
			String stringValue = litExpression.getValue();
				
			try {
				Double.parseDouble(stringValue);
				return LiteralExpressionCompilationDetails.ok(doubleType);
			} catch(NumberFormatException e) {
				return LiteralExpressionCompilationDetails.cantUnderstandLiteral();
			}			
		} else if(expression instanceof VariableReferenceExpression) {
			VariableReferenceExpression refExpression = (VariableReferenceExpression)expression;
			if(lexicalContext.hasVariable(refExpression.getName())) {
				JType variableType = lexicalContext.getVariableType(refExpression.getName());
				return VariableReferenceCompilationDetails.ok(variableType);
			}
			
			return VariableReferenceCompilationDetails.noSuchVariable();
		} else if(expression instanceof BinaryExpression) {		
			BinaryExpression binExpression = (BinaryExpression)expression;
			ExpressionCompilationDetails left = analyze(binExpression.getLeft());
			ExpressionCompilationDetails right = analyze(binExpression.getRight());
			
			if(left.hasErrors() || right.hasErrors()) {
				return BinaryExpressionCompilationDetails.passThrough(left, right);
			}
			
			JType leftType = left.getType();
			JType rightType = right.getType();
			if(leftType.equals(rightType)) {
				return BinaryExpressionCompilationDetails.directly(left, right, leftType);
			}
			
			if(leftType.canImplicitlyCastTo(rightType)) {
				return BinaryExpressionCompilationDetails.implicitlyCastLeftTo(left, right, rightType);
			}
			
			if(rightType.canImplicitlyCastTo(leftType)) {
				return BinaryExpressionCompilationDetails.implicitlyCastRightTo(left, right, leftType);
			}
			
			return BinaryExpressionCompilationDetails.noCommonType(left, right);
		} else {
			throw new RuntimeException();
		}
	}

}
