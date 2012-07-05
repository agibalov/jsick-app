package com.loki2302.jsick.compiler;

import com.loki2302.jsick.LexicalContext;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.statements.DOMVariableDefinitionStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.expressions.CastExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.statements.VariableDefinitionStatement;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;

public class DOMVariableDefinitionStatementToStatementConverterEvaluator extends Evaluator<DOMVariableDefinitionStatement, Statement> {
	
	private final Types types;
	private final ExpressionCompiler expressionCompiler;
	private final LexicalContext lexicalContext;
	
	public DOMVariableDefinitionStatementToStatementConverterEvaluator(
			ExpressionCompiler expressionCompiler, 
			LexicalContext lexicalContext, 
			Types types) {
		this.expressionCompiler = expressionCompiler;
		this.lexicalContext = lexicalContext;
		this.types = types;
	}
	
	@Override
	public Context<Statement> evaluate(Context<DOMVariableDefinitionStatement> input) {
		if(!input.isOk()) {
			return fail(new BadContextError(this, input));
		}
		
		DOMVariableDefinitionStatement domStatement = input.getValue();
		DOMExpression domExpression = domStatement.getExpression();
		Context<TypedExpression> expressionContext = expressionCompiler.compile(domExpression);
		if(!expressionContext.isOk()) {
			return fail(expressionContext.getError());
		}
		
		String variableTypeName = domStatement.getTypeName();
		String variableName = domStatement.getVariableName();
		if(lexicalContext.variableExists(variableName)) {
			return fail(new BadContextError(this, input));
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
				
		return ok(new VariableDefinitionStatement(variableType, variableName, expression));
	}		
}