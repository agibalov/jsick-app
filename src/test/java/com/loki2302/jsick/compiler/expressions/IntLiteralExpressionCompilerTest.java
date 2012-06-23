package com.loki2302.jsick.compiler.expressions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.errors.BadIntLiteralCompilationError;
import com.loki2302.jsick.compiler.expressions.ExpressionCompilationResult;
import com.loki2302.jsick.compiler.expressions.IntLiteralExpressionCompiler;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.vm.instructions.PushIntInstruction;

public class IntLiteralExpressionCompilerTest {
	
	@Test
	public void shouldCompileIntLiteral() {
		IntType intType = new IntType();
		
		IntLiteralExpression e = new IntLiteralExpression("123");		
		IntLiteralExpressionCompiler c = new IntLiteralExpressionCompiler(intType);
		ExpressionCompilationResult r = c.compile(e);
		assertFalse(r.hasErrors());
		assertEquals(intType, r.getType());
		assertEquals(1, r.getInstructions().size());
		assertTrue(r.getInstructions().get(0) instanceof PushIntInstruction);
	}
	
	@Test
	public void shouldFailOnBadIntLiteral() {		
		IntLiteralExpression e = new IntLiteralExpression("abc");		
		IntLiteralExpressionCompiler c = new IntLiteralExpressionCompiler(new IntType());
		ExpressionCompilationResult r = c.compile(e);
		assertTrue(r.hasErrors());
		assertEquals(1, r.getErrors().size());
		assertTrue(r.getErrors().get(0) instanceof BadIntLiteralCompilationError);
	}

}
