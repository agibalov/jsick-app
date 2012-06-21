package com.loki2302.jsick.compiler.expressiondetails;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.expressiondetails.BinaryExpressionCompilationDetails;
import com.loki2302.jsick.compiler.expressiondetails.ExpressionCompilationDetails;
import com.loki2302.jsick.compiler.expressiondetails.ExpressionCompiler;
import com.loki2302.jsick.compiler.expressiondetails.BinaryExpressionCompilationDetails.CommonnessKind;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.LiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;

public class ExpressionCompilerTest {

	private final static IntType intType = new IntType();
	private final static DoubleType doubleType = new DoubleType();
	
	@Test
	public void goodIntShouldBeTreatedAsInt() {
		ExpressionCompiler expressionCompiler = makeExpressionCompiler();		
		Expression e = new LiteralExpression("1");
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertFalse(d.hasErrors());
		assertEquals(intType, d.getType());		
	}
	
	@Test
	public void goodDoubleShouldBeTreatedAsDouble() {
		ExpressionCompiler expressionCompiler = makeExpressionCompiler();		
		Expression e = new LiteralExpression("3.14");
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertFalse(d.hasErrors());
		assertEquals(doubleType, d.getType());		
	}
	
	@Test
	public void badLiteralShouldBeTreatedAsError() {
		ExpressionCompiler expressionCompiler = makeExpressionCompiler();		
		Expression e = new LiteralExpression("abc");
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertTrue(d.hasErrors());
	}
	
	@Test
	public void sumOfIntsShouldBeInt() {
		ExpressionCompiler expressionCompiler = makeExpressionCompiler();		
		Expression left = new LiteralExpression("1");
		Expression right = new LiteralExpression("2");
		Expression e = new AddExpression(left, right);
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertFalse(d.hasErrors());
		assertEquals(intType, d.getType());
		assertEquals(CommonnessKind.Directly, ((BinaryExpressionCompilationDetails)d).getCommonnessKind());
	}
	
	@Test
	public void sumOfDoublesShouldBeDouble() {
		ExpressionCompiler expressionCompiler = makeExpressionCompiler();		
		Expression left = new LiteralExpression("1.1");
		Expression right = new LiteralExpression("2.1");
		Expression e = new AddExpression(left, right);
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertFalse(d.hasErrors());
		assertEquals(doubleType, d.getType());
		assertEquals(CommonnessKind.Directly, ((BinaryExpressionCompilationDetails)d).getCommonnessKind());
	}
	
	@Test
	public void sumOfDoubleAndIntShouldBeDouble() {
		ExpressionCompiler expressionCompiler = makeExpressionCompiler();		
		Expression left = new LiteralExpression("1.1");
		Expression right = new LiteralExpression("2");
		Expression e = new AddExpression(left, right);
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertFalse(d.hasErrors());
		assertEquals(doubleType, d.getType());
		assertEquals(CommonnessKind.CastRight, ((BinaryExpressionCompilationDetails)d).getCommonnessKind());
	}
	
	@Test
	public void sumOfIntAndDoubleShouldBeDouble() {
		ExpressionCompiler expressionCompiler = makeExpressionCompiler();		
		Expression left = new LiteralExpression("1");
		Expression right = new LiteralExpression("2.1");
		Expression e = new AddExpression(left, right);
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertFalse(d.hasErrors());
		assertEquals(doubleType, d.getType());
		assertEquals(CommonnessKind.CastLeft, ((BinaryExpressionCompilationDetails)d).getCommonnessKind());
	}
	
	@Test
	public void referencedIntShouldBeInt() {
		LexicalContext lexicalContext = new LexicalContext();
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(lexicalContext, intType, doubleType);
		lexicalContext.addVariable("x", intType);
		Expression e = new VariableReferenceExpression("x");
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertFalse(d.hasErrors());
		assertEquals(intType, d.getType());
	}
	
	@Test
	public void referencedDoubleShouldBeDouble() {
		LexicalContext lexicalContext = new LexicalContext();
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(lexicalContext, intType, doubleType);
		lexicalContext.addVariable("x", doubleType);
		Expression e = new VariableReferenceExpression("x");
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertFalse(d.hasErrors());
		assertEquals(doubleType, d.getType());
	}	
	
	@Test
	public void referencingUnexistentVariableShouldFail() {
		ExpressionCompiler expressionCompiler = makeExpressionCompiler();
		Expression e = new VariableReferenceExpression("x");
		ExpressionCompilationDetails d = expressionCompiler.analyze(e);
		assertTrue(d.hasErrors());
	}	
	
	private static ExpressionCompiler makeExpressionCompiler() {
		LexicalContext lexicalContext = new LexicalContext();
		return new ExpressionCompiler(lexicalContext, intType, doubleType); 
	}
	
}
