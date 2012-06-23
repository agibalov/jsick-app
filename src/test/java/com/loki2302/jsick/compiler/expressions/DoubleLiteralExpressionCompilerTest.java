package com.loki2302.jsick.compiler.expressions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.errors.BadDoubleLiteralCompilationError;
import com.loki2302.jsick.compiler.expressions.DoubleLiteralExpressionCompiler;
import com.loki2302.jsick.compiler.expressions.ExpressionCompilationResult;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.vm.instructions.PushDoubleInstruction;

public class DoubleLiteralExpressionCompilerTest {
	
	@Test
	public void shouldCompileDoubleLiteral() {
		DoubleType doubleType = new DoubleType();
		
		DoubleLiteralExpression e = new DoubleLiteralExpression("123.1");		
		DoubleLiteralExpressionCompiler c = new DoubleLiteralExpressionCompiler(doubleType);
		ExpressionCompilationResult r = c.compile(e);
		assertFalse(r.hasErrors());
		assertEquals(doubleType, r.getType());
		assertEquals(1, r.getInstructions().size());
		assertTrue(r.getInstructions().get(0) instanceof PushDoubleInstruction);
	}
	
	@Test
	public void shouldFailOnBadIntLiteral() {		
		DoubleLiteralExpression e = new DoubleLiteralExpression("abc");		
		DoubleLiteralExpressionCompiler c = new DoubleLiteralExpressionCompiler(new DoubleType());
		ExpressionCompilationResult r = c.compile(e);
		assertTrue(r.hasErrors());
		assertEquals(1, r.getErrors().size());
		assertTrue(r.getErrors().get(0) instanceof BadDoubleLiteralCompilationError);
	}

}
