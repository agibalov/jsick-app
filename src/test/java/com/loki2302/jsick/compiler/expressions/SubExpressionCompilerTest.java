package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;

public class SubExpressionCompilerTest extends AbstractBinaryExpressionTest {
	
	@Override	
	protected BinaryExpression makeExpression(Expression left, Expression right) {
		return new SubExpression(left, right);
	}
	
}
