package com.loki2302.jsick.compiler.statements;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.statements.PrintStatement;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class PrintStatementCompilerTest {
	
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
		LexicalContext lc = new LexicalContext();
		IntType intType = new IntType();
		DoubleType doubleType = new DoubleType();
		ExpressionCompiler ec = new ExpressionCompiler(intType, doubleType, lc);
		return new StatementCompiler(lc, ec, intType, doubleType); 
	}
	
}
