package com.loki2302.jsick.evaluator.statements;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.ExpressionCompiler;
import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.statements.DOMVariableDefinitionStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.evaluator.expressions.errors.CannotCastError;
import com.loki2302.jsick.evaluator.statements.errors.BadInitializerExpressionError;
import com.loki2302.jsick.evaluator.statements.errors.UnknownTypeError;
import com.loki2302.jsick.evaluator.statements.errors.VariableRedefinitionError;
import com.loki2302.jsick.expressions.CastExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.statements.VariableDefinitionStatement;
import com.loki2302.jsick.types.Instance;
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
	public Context<Statement> evaluate(DOMVariableDefinitionStatement input) {		
		List<AbstractError> errors = new ArrayList<AbstractError>();
		
		DOMExpression domExpression = input.getExpression();
		Context<TypedExpression> expressionContext = expressionCompiler.compile(domExpression);
		if(!expressionContext.isOk()) {
			errors.add(new BadInitializerExpressionError(this, input));
		}
		
		String variableTypeName = input.getTypeName();
		Type variableType = types.getTypeByName(variableTypeName);
		if(variableType == null) {
			errors.add(new UnknownTypeError(this, input));
		}
		
		String variableName = input.getVariableName();
		if(lexicalContext.variableExists(variableName)) {
			errors.add(new VariableRedefinitionError(this, input));
		}		
		
		// TODO: this logic is pretty common, so this needs to be implemented somewhere else
		TypedExpression expression = expressionContext.getValue();
		Type expressionType = expression.getType();
		if(!expressionType.equals(variableType)) {
			if(expressionType.canImplicitlyCastTo(variableType)) {
				expression = new CastExpression(expression, variableType);
			} else {
				errors.add(new CannotCastError(this, input));
			}
		}
		//
		
		if(!errors.isEmpty()) {
			return fail(new CompositeError(this, input, errors));
		}
		
		Instance instance = variableType.makeInstance();
		
		lexicalContext.addVariable(variableName, instance);
				
		return ok(new VariableDefinitionStatement(instance, expression));
	}		
}