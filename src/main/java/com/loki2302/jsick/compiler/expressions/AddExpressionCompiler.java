package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.errors.OperationUndefinedForTypeCompilationError;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.types.JType;
import com.loki2302.jsick.vm.instructions.DoubleAddInstruction;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntAddInstruction;

public class AddExpressionCompiler extends AbstractBinaryArithmeticExpressionCompiler<AddExpression> {

	public AddExpressionCompiler(ExpressionCompiler expressionCompiler, IntType intType, DoubleType doubleType) {
		super(expressionCompiler, intType, doubleType);
	}

	@Override
	protected Instruction makeIntOperationInstruction() {
		return new IntAddInstruction();
	}

	@Override
	protected Instruction makeDoubleOperationInstruction() {
		return new DoubleAddInstruction();
	}

	@Override
	protected CompilationError makeOperationUndefinedForTypeError(JType type) {
		return new OperationUndefinedForTypeCompilationError(OperationUndefinedForTypeCompilationError.Operation.Add, type);
	}	

}

