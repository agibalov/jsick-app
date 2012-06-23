package com.loki2302.jsick.compiler.statements;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.errors.DependencyHasErrorsCompilationError;
import com.loki2302.jsick.compiler.errors.PrintNotDefinedForType;
import com.loki2302.jsick.compiler.expressions.ExpressionCompilationResult;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.statements.PrintStatement;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.PrintDoubleInstruction;
import com.loki2302.jsick.vm.instructions.PrintIntInstruction;

public class PrintStatementCompiler extends AbstractStatementCompiler<PrintStatement> {

	private final ExpressionCompiler expressionCompiler;
	private final IntType intType;
	private final DoubleType doubleType;
	
	public PrintStatementCompiler(ExpressionCompiler expressionCompiler, IntType intType, DoubleType doubleType) {
		this.expressionCompiler = expressionCompiler;
		this.intType = intType;
		this.doubleType = doubleType;
	}
	
	@Override
	public StatementCompilationResult compile(PrintStatement statement) {
		Expression expression = statement.getExpression();
		ExpressionCompilationResult result = expressionCompiler.compile(expression);
		if(result.hasErrors()) {
			return StatementCompilationResult.error(new DependencyHasErrorsCompilationError(result, statement));
		}
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.addAll(result.getInstructions());
		if(result.getType().equals(intType)) {
			instructions.add(new PrintIntInstruction());			
		} else if(result.getType().equals(doubleType)) {
			instructions.add(new PrintDoubleInstruction());
		} else {
			StatementCompilationResult.error(new PrintNotDefinedForType(result.getType(), statement));
		}
		
		return StatementCompilationResult.ok(instructions);
	}

}