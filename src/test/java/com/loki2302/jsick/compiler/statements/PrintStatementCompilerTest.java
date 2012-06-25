package com.loki2302.jsick.compiler.statements;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.expressions.AddExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.DivExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.DoubleLiteralExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.ExpressionCompilerBuilder;
import com.loki2302.jsick.compiler.expressions.IntLiteralExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.MulExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.SubExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.VariableReferenceExpressionCompiler;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.compiler.model.statements.AssignmentStatement;
import com.loki2302.jsick.compiler.model.statements.PrintStatement;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class PrintStatementCompilerTest {
	
	private static final IntType intType = new IntType();
	private static final DoubleType doubleType = new DoubleType();
	
	@Test
	public void shouldBeAbleToCompilePrintInt() {		
		Expression e = new IntLiteralExpression("1");
		PrintStatement s = new PrintStatement(e);
		
		StatementCompiler c = makeStatementCompiler();
		StatementCompilationResult r = c.compile(s);
		
		assertFalse(r.hasErrors());
	}
	
	@Test
	public void shouldBeAbleToCompilePrintDouble() {		
		Expression e = new DoubleLiteralExpression("1");
		PrintStatement s = new PrintStatement(e);
		
		StatementCompiler c = makeStatementCompiler();
		StatementCompilationResult r = c.compile(s);
		
		assertFalse(r.hasErrors());
	}
	
	private static StatementCompiler makeStatementCompiler() {
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
		
		return statementCompiler; 
	}
		
}
