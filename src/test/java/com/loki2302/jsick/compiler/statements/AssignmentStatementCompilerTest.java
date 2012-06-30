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
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.compiler.model.statements.AssignmentStatement;
import com.loki2302.jsick.types.Types;

public class AssignmentStatementCompilerTest {
	
	private final static Types types = new Types();

	@Test
	public void shouldBeAbleToCompileAssignmentStatement() {		
		LexicalContext lc = new LexicalContext();
		ExpressionCompiler ec = makeExpressionCompiler(lc);		
		AssignmentStatementCompiler c = new AssignmentStatementCompiler(lc, ec);
		
		AssignmentStatement s = new AssignmentStatement("a", new IntLiteralExpression("1"));
		
		lc.addVariable("a", types.intType);
		StatementCompilationResult r = c.compile(s);
		
		assertFalse(r.hasErrors());
		assertTrue(lc.hasVariable("a"));
		assertEquals(types.intType, lc.getVariableType("a"));
	}
	
	private static ExpressionCompiler makeExpressionCompiler(LexicalContext lexicalContext) {		
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
	
}
