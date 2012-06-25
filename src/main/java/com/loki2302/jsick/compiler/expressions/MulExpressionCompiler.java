package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.errors.OperationUndefinedForTypeCompilationError;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.types.JType;
import com.loki2302.jsick.vm.instructions.DoubleMulInstruction;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntMulInstruction;

public class MulExpressionCompiler extends AbstractBinaryArithmeticExpressionCompiler<MulExpression> {

	public MulExpressionCompiler(IntType intType, DoubleType doubleType) {
		super(intType, doubleType);
	}

	@Override
	protected Instruction makeIntOperationInstruction() {
		return new IntMulInstruction();
	}

	@Override
	protected Instruction makeDoubleOperationInstruction() {
		return new DoubleMulInstruction();
	}

	@Override
	protected CompilationError makeOperationUndefinedForTypeError(JType type, Object sourceContext) {
		return new OperationUndefinedForTypeCompilationError(OperationUndefinedForTypeCompilationError.Operation.Mul, type, sourceContext);
	}	

}

