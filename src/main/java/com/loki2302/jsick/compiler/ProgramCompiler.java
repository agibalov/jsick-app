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
import com.loki2302.jsick.compiler.model.statements.VariableDefinitionStatement;
import com.loki2302.jsick.compiler.statements.AssignmentStatementCompiler;
import com.loki2302.jsick.compiler.statements.PrintStatementCompiler;
import com.loki2302.jsick.compiler.statements.StatementCompilationResult;
import com.loki2302.jsick.compiler.statements.StatementCompiler;
import com.loki2302.jsick.compiler.statements.StatementCompilerBuilder;
import com.loki2302.jsick.compiler.statements.VariableDefinitionStatementCompiler;
import com.loki2302.jsick.types.Types;
import com.loki2302.jsick.vm.instructions.Instruction;

public class ProgramCompiler {
	
	private final static Types types = new Types();
	
	public static ProgramCompilationResult compile(Program program) {	
		LexicalContext lexicalContext = new LexicalContext();		
				
		ExpressionCompiler expressionCompiler = new ExpressionCompilerBuilder()
			.registerCompiler(IntLiteralExpression.class, new IntLiteralExpressionCompiler(types.intType))
			.registerCompiler(DoubleLiteralExpression.class, new DoubleLiteralExpressionCompiler(types.doubleType))
			.registerCompiler(VariableReferenceExpression.class, new VariableReferenceExpressionCompiler(lexicalContext)) 
			.registerCompiler(AddExpression.class, new AddExpressionCompiler(types))
			.registerCompiler(SubExpression.class, new SubExpressionCompiler(types))
			.registerCompiler(MulExpression.class, new MulExpressionCompiler(types))
			.registerCompiler(DivExpression.class, new DivExpressionCompiler(types))
			.build();
		
		StatementCompiler statementCompiler = new StatementCompilerBuilder()
			.registerCompiler(AssignmentStatement.class, new AssignmentStatementCompiler(lexicalContext, expressionCompiler))
			.registerCompiler(PrintStatement.class, new PrintStatementCompiler(expressionCompiler, types))
			.registerCompiler(VariableDefinitionStatement.class, new VariableDefinitionStatementCompiler(lexicalContext, expressionCompiler, types))
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
