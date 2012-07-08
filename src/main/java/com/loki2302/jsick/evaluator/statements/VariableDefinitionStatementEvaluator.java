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
import com.loki2302.jsick.evaluator.expressions.semantics.ExpressionAndType;
import com.loki2302.jsick.evaluator.statements.errors.UnknownTypeError;
import com.loki2302.jsick.evaluator.statements.errors.VariableRedefinitionError;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.statements.VariableDefinitionStatement;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;

public class VariableDefinitionStatementEvaluator extends Evaluator<DOMVariableDefinitionStatement, Statement> {
	
	private final Types types;
	private final ExpressionCompiler expressionCompiler;
	private final LexicalContext lexicalContext;
	private final Evaluator<ExpressionAndType, Expression> makeSureExpressionIsOfTypeEvaluator;
	
	public VariableDefinitionStatementEvaluator(
			ExpressionCompiler expressionCompiler, 
			LexicalContext lexicalContext, 
			Types types,
			Evaluator<ExpressionAndType, Expression> makeSureExpressionIsOfTypeEvaluator) {
		this.expressionCompiler = expressionCompiler;
		this.lexicalContext = lexicalContext;
		this.types = types;
		this.makeSureExpressionIsOfTypeEvaluator = makeSureExpressionIsOfTypeEvaluator;
	}
	
	@Override
	public Context<Statement> evaluate(DOMVariableDefinitionStatement input) {		
		List<AbstractError> errors = new ArrayList<AbstractError>();
		
		DOMExpression domExpression = input.getExpression();
		Context<Expression> expressionContext = expressionCompiler.compile(domExpression);
		if(!expressionContext.isOk()) {
			errors.add(expressionContext.getError());
		}
		
		String variableTypeName = input.getTypeName();
		Type variableType = types.getTypeByName(variableTypeName);
		if(variableType == null) {
			errors.add(new UnknownTypeError(this, input, variableTypeName));
		}
		
		String variableName = input.getVariableName();
		if(lexicalContext.variableExists(variableName)) {
			errors.add(new VariableRedefinitionError(this, input, variableName));
		}		
		
		Context<Expression> castExpressionContext = makeSureExpressionIsOfTypeEvaluator.evaluate(
				new ExpressionAndType(expressionContext.getValue(), variableType));
		if(!castExpressionContext.isOk()) {
			errors.add(castExpressionContext.getError());
		}
		
		if(!errors.isEmpty()) {
			return fail(new CompositeError(this, input, errors));
		}
		
		Instance instance = variableType.makeInstance(variableName);
		Expression expression = castExpressionContext.getValue();
		
		lexicalContext.addVariable(variableName, instance);
				
		return ok(new VariableDefinitionStatement(instance, expression));
	}		
}