package com.loki2302.jsick.compiler.statements;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.errors.CannotAssignCompilationError;
import com.loki2302.jsick.compiler.errors.DependencyHasErrorsCompilationError;
import com.loki2302.jsick.compiler.errors.UnknownSimpleTypeCompilationError;
import com.loki2302.jsick.compiler.errors.VariableRedefinitionCompilationError;
import com.loki2302.jsick.compiler.expressions.ExpressionCompilationResult;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.SimpleType;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.statements.VariableDefinitionStatement;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntToDoubleInstruction;
import com.loki2302.jsick.vm.instructions.SaveLocalInstruction;

public class VariableDefinitionStatementCompiler extends AbstractStatementCompiler<VariableDefinitionStatement> {

	private final LexicalContext lexicalContext;
	private final ExpressionCompiler expressionCompiler;
	private final Types types;
	
	public VariableDefinitionStatementCompiler(LexicalContext lexicalContext, ExpressionCompiler expressionCompiler, Types types) {
		this.lexicalContext = lexicalContext;
		this.expressionCompiler = expressionCompiler;
		this.types = types;
	}
	
	@Override
	public StatementCompilationResult compile(VariableDefinitionStatement statement) {
		String variableName = statement.getName();
		SimpleType type = (SimpleType)statement.getType();
		Expression initExpression = statement.getExpression();
		
		ExpressionCompilationResult initExpressionResult = expressionCompiler.compile(initExpression);
		if(initExpressionResult.hasErrors()) {
			return StatementCompilationResult.error(new DependencyHasErrorsCompilationError(initExpressionResult, statement));
		}
		
		if(lexicalContext.hasVariable(variableName)) {
			return StatementCompilationResult.error(new VariableRedefinitionCompilationError(variableName, statement));
		}
		
		Type variableType = null;
		if(type.getName().equals("int")) {
			variableType = types.intType;
		} else if(type.getName().equals("double")) {
			variableType = types.doubleType;
		} else {
			return StatementCompilationResult.error(new UnknownSimpleTypeCompilationError(type.getName(), statement)); 
		}
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.addAll(initExpressionResult.getInstructions());
		if(initExpressionResult.getType().equals(variableType)) {
			// types are the same, do nothing
		} else if(initExpressionResult.getType().canImplicitlyCastTo(variableType)) {
			instructions.add(new IntToDoubleInstruction());
		} else {
			return StatementCompilationResult.error(new CannotAssignCompilationError(variableType, initExpressionResult.getType(), statement));
		}
		
		lexicalContext.addVariable(variableName, variableType);
		int position = lexicalContext.getVariablePosition(variableName);
		instructions.add(new SaveLocalInstruction(position));				
		
		return StatementCompilationResult.ok(instructions);
	}

}
