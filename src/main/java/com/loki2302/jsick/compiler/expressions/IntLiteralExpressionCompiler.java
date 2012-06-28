package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.errors.BadIntLiteralCompilationError;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.vm.instructions.PushIntInstruction;

public class IntLiteralExpressionCompiler extends AbstractExpressionCompiler<IntLiteralExpression> {
	
	private final IntType intType;
	
	public IntLiteralExpressionCompiler(IntType intType) {
		this.intType = intType;
	}
	
	@Override
	public ExpressionCompilationResult compileImpl(IntLiteralExpression expression, PrecompilationResults precompilationResults) {			
		String stringValue = expression.getValue();
		try {
			int value = Integer.parseInt(stringValue);
			return ExpressionCompilationResult.ok(new PushIntInstruction(value), intType);
		} catch(NumberFormatException e) {
			return ExpressionCompilationResult.error(new BadIntLiteralCompilationError(expression));
		}			
	}		
}