package com.loki2302.jsick.compiler.statements;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.expressions.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.AssignmentStatement;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class AssignmentStatementCompilerTest {

	@Test
	public void shouldBeAbleToCompileAssignmentStatement() {		
		LexicalContext lc = new LexicalContext();
		IntType intType = new IntType();
		ExpressionCompiler ec = new ExpressionCompiler(intType, new DoubleType(), lc);		
		AssignmentStatementCompiler c = new AssignmentStatementCompiler(lc, ec);
		
		AssignmentStatement s = new AssignmentStatement("a", new IntLiteralExpression("1"));
		
		assertFalse(lc.hasVariable("a"));
		StatementCompilationResult r = c.compile(s);
		
		assertFalse(r.hasErrors());
		assertTrue(lc.hasVariable("a"));
		assertEquals(intType, lc.getVariableType("a"));
	}
	
}
