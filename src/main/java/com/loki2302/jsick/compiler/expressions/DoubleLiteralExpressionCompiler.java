package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.errors.BadDoubleLiteralCompilationError;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.vm.instructions.PushDoubleInstruction;

public class DoubleLiteralExpressionCompiler extends AbstractExpressionCompiler<DoubleLiteralExpression> {
	
	private final DoubleType doubleType;
	
	public DoubleLiteralExpressionCompiler(DoubleType doubleType) {
		this.doubleType = doubleType;
	}
	
	@Override
	public ExpressionCompilationResult compile(DoubleLiteralExpression expression) {			
		String stringValue = expression.getValue();
		try {
			double value = Double.parseDouble(stringValue);
			return ExpressionCompilationResult.ok(new PushDoubleInstruction(value), doubleType);
		} catch(NumberFormatException e) {
			return ExpressionCompilationResult.error(new BadDoubleLiteralCompilationError());
		}			
	}		
}