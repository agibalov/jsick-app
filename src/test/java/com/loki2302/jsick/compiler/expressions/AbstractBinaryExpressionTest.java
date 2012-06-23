package com.loki2302.jsick.compiler.expressions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public abstract class AbstractBinaryExpressionTest {
	
	private static final IntType intType = new IntType();
	private static final DoubleType doubleType = new DoubleType();

	@Test
	public void opOnIntsShouldBeInt() {
		BinaryExpression e = makeExpression(
				new IntLiteralExpression("1"), 
				new IntLiteralExpression("2"));
		
		ExpressionCompiler c = new ExpressionCompiler(intType, doubleType, new LexicalContext());
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(intType, r.getType());
	}
	
	@Test
	public void opOnDoublesShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new DoubleLiteralExpression("1"), 
				new DoubleLiteralExpression("2"));
		
		ExpressionCompiler c = new ExpressionCompiler(intType, doubleType, new LexicalContext());
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(doubleType, r.getType());
	}
	
	@Test
	public void opOnIntAndDoubleShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new IntLiteralExpression("1"), 
				new DoubleLiteralExpression("2"));
		
		ExpressionCompiler c = new ExpressionCompiler(intType, doubleType, new LexicalContext());
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(doubleType, r.getType());
	}
	
	@Test
	public void opOnDoubleAndIntShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new DoubleLiteralExpression("1"), 
				new IntLiteralExpression("2"));
		
		ExpressionCompiler c = new ExpressionCompiler(intType, doubleType, new LexicalContext());
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(doubleType, r.getType());
	}
	
	protected abstract BinaryExpression makeExpression(Expression left, Expression right);
	
}
