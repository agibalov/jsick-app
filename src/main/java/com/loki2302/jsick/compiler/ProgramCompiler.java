package com.loki2302.jsick.compiler;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.Program;
import com.loki2302.jsick.compiler.model.statements.Statement;
import com.loki2302.jsick.compiler.statements.StatementCompilationResult;
import com.loki2302.jsick.compiler.statements.StatementCompiler;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.vm.instructions.Instruction;

public class ProgramCompiler {
	
	private final static IntType intType = new IntType();		
	private final static DoubleType doubleType = new DoubleType();
	
	public static ProgramCompilationResult compile(Program program) {	
		LexicalContext lexicalContext = new LexicalContext();		
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(intType, doubleType, lexicalContext);
		StatementCompiler statementCompiler = new StatementCompiler(lexicalContext, expressionCompiler, intType, doubleType);
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		List<CompilationError> errors = new ArrayList<CompilationError>();
		for(Statement statement : program.getStatements()) {
			StatementCompilationResult statementCompilationResult = statementCompiler.compile(statement);
			if(statementCompilationResult.hasErrors()) {
				errors.addAll(statementCompilationResult.getErrors());
			} else {
				instructions.addAll(statementCompilationResult.getInstructions());
			}
		}
		
		if(!errors.isEmpty()) {
			return ProgramCompilationResult.error(errors);
		}
		
		return ProgramCompilationResult.ok(instructions);
	}
	
}
