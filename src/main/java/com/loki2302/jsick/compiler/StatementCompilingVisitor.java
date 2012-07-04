package com.loki2302.jsick.compiler;

import com.loki2302.jsick.LexicalContext;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.statements.DOMExpressionStatement;
import com.loki2302.jsick.dom.statements.DOMPrintStatement;
import com.loki2302.jsick.dom.statements.DOMStatementVisitor;
import com.loki2302.jsick.dom.statements.DOMVariableDefinitionStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.expressions.CastExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.statements.ExpressionStatement;
import com.loki2302.jsick.statements.PrintStatement;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.statements.VariableDefinitionStatement;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;

public class StatementCompilingVisitor implements DOMStatementVisitor<Statement> {
	private final Types types;
	private final ExpressionCompiler expressionCompiler;
	private final LexicalContext lexicalContext;

	public StatementCompilingVisitor(Types types, ExpressionCompiler expressionCompiler, LexicalContext lexicalContext) {
		this.types = types;
		this.expressionCompiler = expressionCompiler;
		this.lexicalContext = lexicalContext;
	}
	
	@Override
	public Statement visitVariableDefinitionStatement(DOMVariableDefinitionStatement domStatement) {
		DOMExpression domExpression = domStatement.getExpression();
		Context<TypedExpression> expressionContext = expressionCompiler.compile(domExpression);
		if(!expressionContext.isOk()) {
			throw new RuntimeException();
		}
		
		String variableTypeName = domStatement.getTypeName();
		String variableName = domStatement.getVariableName();
		if(lexicalContext.variableExists(variableName)) {
			throw new RuntimeException();
		}
		
		Type variableType = null;
		if(variableTypeName.equals("int")) {
			variableType = types.IntType;
		} else if(variableTypeName.equals("double")) {
			variableType = types.DoubleType;
		} else {
			throw new RuntimeException();
		}
		
		lexicalContext.addVariable(variableName, variableType);
		
		TypedExpression expression = expressionContext.getValue();
		Type expressionType = expression.getType();
		if(!expressionType.equals(variableType)) {
			if(expressionType.canCastTo(variableType)) {
				expression = new CastExpression(expression, variableType);
			} else {
				throw new RuntimeException();
			}
		}
				
		return new VariableDefinitionStatement(variableType, variableName, expression);
	}

	@Override
	public Statement visitExpressionStatement(DOMExpressionStatement domStatement) {
		DOMExpression domExpression = domStatement.getExpression();
		Context<TypedExpression> expressionContext = expressionCompiler.compile(domExpression);
		if(!expressionContext.isOk()) {
			throw new RuntimeException();
		}
		
		return new ExpressionStatement(expressionContext.getValue());
	}

	@Override
	public Statement visitPrintStatement(DOMPrintStatement domStatement) {
		DOMExpression expression = domStatement.getExpression();
		Context<TypedExpression> expressionContext = expressionCompiler.compile(expression);
		if(!expressionContext.isOk()) {
			throw new RuntimeException();
		}
		
		return new PrintStatement(expressionContext.getValue());
	}
	
}