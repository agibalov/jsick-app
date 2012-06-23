package com.loki2302.jsick.compiler.expressions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.IntType;

public class VariableReferenceExpressionCompilerTest {

	@Test
	public void shouldBeAbleToCompileVariableReferenceExpression() {
		IntType intType = new IntType();
		
		VariableReferenceExpression e = new VariableReferenceExpression("a");	
		LexicalContext lc = new LexicalContext();
		lc.addVariable("a", intType);
		VariableReferenceExpressionCompiler c = new VariableReferenceExpressionCompiler(lc); 
		ExpressionCompilationResult r = c.compile(e);
		assertFalse(r.hasErrors());
		assertEquals(intType, r.getType());
	}
	
	@Test
	public void shouldFailWhenThereIsNoSuchVariable() {		
		VariableReferenceExpression e = new VariableReferenceExpression("a");	
		LexicalContext lc = new LexicalContext();
		VariableReferenceExpressionCompiler c = new VariableReferenceExpressionCompiler(lc); 
		ExpressionCompilationResult r = c.compile(e);
		assertTrue(r.hasErrors());
	}
	
}
