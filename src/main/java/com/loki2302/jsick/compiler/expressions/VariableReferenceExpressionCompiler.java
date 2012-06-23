package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.LexicalContext;
import com.loki2302.jsick.compiler.errors.NoSuchVariableCompilationError;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.JType;
import com.loki2302.jsick.vm.instructions.LoadLocalInstruction;

public class VariableReferenceExpressionCompiler extends AbstractExpressionCompiler<VariableReferenceExpression> {

	private final LexicalContext lexicalContext;
	
	public VariableReferenceExpressionCompiler(LexicalContext lexicalContext) {
		this.lexicalContext = lexicalContext;
	}
	
	@Override
	public ExpressionCompilationResult compile(VariableReferenceExpression expression) {
		String name = expression.getName();
		if(!lexicalContext.hasVariable(name)) {
			return ExpressionCompilationResult.error(new NoSuchVariableCompilationError(name));
		}
		
		int position = lexicalContext.getVariablePosition(name);
		JType type = lexicalContext.getVariableType(name);
		
		return ExpressionCompilationResult.ok(new LoadLocalInstruction(position), type);
	}

}
