package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;

public class DivExpressionCompilerTest extends AbstractBinaryExpressionTest {
	
	@Override	
	protected BinaryExpression makeExpression(Expression left, Expression right) {
		return new DivExpression(left, right);
	}
	
}
