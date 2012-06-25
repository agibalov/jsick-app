package com.loki2302.jsick.compiler.expressions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
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
		
		ExpressionCompiler c = makeExpressionCompiler();
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(intType, r.getType());
	}
	
	@Test
	public void opOnDoublesShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new DoubleLiteralExpression("1"), 
				new DoubleLiteralExpression("2"));
		
		ExpressionCompiler c = makeExpressionCompiler();
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(doubleType, r.getType());
	}
	
	@Test
	public void opOnIntAndDoubleShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new IntLiteralExpression("1"), 
				new DoubleLiteralExpression("2"));
		
		ExpressionCompiler c = makeExpressionCompiler();
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(doubleType, r.getType());
	}
	
	@Test
	public void opOnDoubleAndIntShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new DoubleLiteralExpression("1"), 
				new IntLiteralExpression("2"));
		
		ExpressionCompiler c = makeExpressionCompiler();
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(doubleType, r.getType());
	}
	
	private static ExpressionCompiler makeExpressionCompiler() {
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
		
		return expressionCompiler;
	}
	
	protected abstract BinaryExpression makeExpression(Expression left, Expression right);	
	
}
