package com.loki2302.jsick.compiler;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.expressions.AddExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.DivExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.DoubleLiteralExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.ExpressionCompilerBuilder;
import com.loki2302.jsick.compiler.expressions.IntLiteralExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.MulExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.SubExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.VariableReferenceExpressionCompiler;
import com.loki2302.jsick.compiler.model.Program;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.compiler.model.statements.AssignmentStatement;
import com.loki2302.jsick.compiler.model.statements.PrintStatement;
import com.loki2302.jsick.compiler.model.statements.Statement;
import com.loki2302.jsick.compiler.statements.AssignmentStatementCompiler;
import com.loki2302.jsick.compiler.statements.PrintStatementCompiler;
import com.loki2302.jsick.compiler.statements.StatementCompilationResult;
import com.loki2302.jsick.compiler.statements.StatementCompiler;
import com.loki2302.jsick.compiler.statements.StatementCompilerBuilder;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.vm.instructions.Instruction;

public class ProgramCompiler {
	
	private final static IntType intType = new IntType();		
	private final static DoubleType doubleType = new DoubleType();
	
	public static ProgramCompilationResult compile(Program program) {	
		LexicalContext lexicalContext = new LexicalContext();		
				
		ExpressionCompiler expressionCompiler = new ExpressionCompilerBuilder()
			.registerCompiler(IntLiteralExpression.class, new IntLiteralExpressionCompiler(intType))
			.registerCompiler(DoubleLiteralExpression.class, new DoubleLiteralExpressionCompiler(doubleType))
			.registerCompiler(VariableReferenceExpression.class, new VariableReferenceExpressionCompiler(lexicalContext)) 
			.registerCompiler(AddExpression.class, new AddExpressionCompiler(intType, doubleType))
			.registerCompiler(SubExpression.class, new SubExpressionCompiler(intType, doubleType))
			.registerCompiler(MulExpression.class, new MulExpressionCompiler(intType, doubleType))
			.registerCompiler(DivExpression.class, new DivExpressionCompiler(intType, doubleType))
			.build();
		
		StatementCompiler statementCompiler = new StatementCompilerBuilder()
			.registerCompiler(AssignmentStatement.class, new AssignmentStatementCompiler(lexicalContext, expressionCompiler))
			.registerCompiler(PrintStatement.class, new PrintStatementCompiler(expressionCompiler, intType, doubleType))
			.build();
		
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
