package com.loki2302.jsick.compiler.statements;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.errors.DependencyHasErrorsCompilationError;
import com.loki2302.jsick.compiler.expressions.ExpressionCompilationResult;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.statements.AssignmentStatement;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.SaveLocalInstruction;

public class AssignmentStatementCompiler extends AbstractStatementCompiler<AssignmentStatement> {

	private final LexicalContext lexicalContext;
	private final ExpressionCompiler expressionCompiler;
	
	public AssignmentStatementCompiler(LexicalContext lexicalContext, ExpressionCompiler expressionCompiler) {
		this.lexicalContext = lexicalContext;
		this.expressionCompiler = expressionCompiler;
	}
	
	@Override
	public StatementCompilationResult compile(AssignmentStatement statement) {
		String name = statement.getName();
		Expression expression = statement.getExpression();
		
		ExpressionCompilationResult result = expressionCompiler.compile(expression);
		if(result.hasErrors()) {
			return StatementCompilationResult.error(new DependencyHasErrorsCompilationError(result, statement));
		}
				
		if(lexicalContext.hasVariable(name)) {
			lexicalContext.setVariableType(name, result.getType());
		} else {
			lexicalContext.addVariable(name, result.getType());
		}
		
		int position = lexicalContext.getVariablePosition(name);
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.addAll(result.getInstructions());
		instructions.add(new SaveLocalInstruction(position));		
		
		return StatementCompilationResult.ok(instructions);
	}

}
