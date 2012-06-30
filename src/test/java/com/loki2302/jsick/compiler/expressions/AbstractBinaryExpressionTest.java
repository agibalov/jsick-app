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
import com.loki2302.jsick.types.Types;

public abstract class AbstractBinaryExpressionTest {
	
	private final static Types types = new Types();

	@Test
	public void opOnIntsShouldBeInt() {
		BinaryExpression e = makeExpression(
				new IntLiteralExpression("1"), 
				new IntLiteralExpression("2"));
		
		ExpressionCompiler c = makeExpressionCompiler();
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(types.intType, r.getType());
	}
	
	@Test
	public void opOnDoublesShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new DoubleLiteralExpression("1"), 
				new DoubleLiteralExpression("2"));
		
		ExpressionCompiler c = makeExpressionCompiler();
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(types.doubleType, r.getType());
	}
	
	@Test
	public void opOnIntAndDoubleShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new IntLiteralExpression("1"), 
				new DoubleLiteralExpression("2"));
		
		ExpressionCompiler c = makeExpressionCompiler();
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(types.doubleType, r.getType());
	}
	
	@Test
	public void opOnDoubleAndIntShouldBeDouble() {
		BinaryExpression e = makeExpression(
				new DoubleLiteralExpression("1"), 
				new IntLiteralExpression("2"));
		
		ExpressionCompiler c = makeExpressionCompiler();
		ExpressionCompilationResult r = c.compile(e);
		
		assertFalse(r.hasErrors());
		assertEquals(types.doubleType, r.getType());
	}
	
	private static ExpressionCompiler makeExpressionCompiler() {
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
		
		return expressionCompiler;
	}
	
	protected abstract BinaryExpression makeExpression(Expression left, Expression right);	
	
}
