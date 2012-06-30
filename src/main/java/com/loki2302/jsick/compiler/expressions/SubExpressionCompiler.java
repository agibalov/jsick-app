package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.errors.OperationUndefinedForTypeCompilationError;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.vm.instructions.DoubleSubInstruction;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntSubInstruction;

public class SubExpressionCompiler extends AbstractBinaryArithmeticExpressionCompiler<SubExpression> {

	public SubExpressionCompiler(IntType intType, DoubleType doubleType) {
		super(intType, doubleType);
	}

	@Override
	protected Instruction makeIntOperationInstruction() {
		return new IntSubInstruction();
	}

	@Override
	protected Instruction makeDoubleOperationInstruction() {
		return new DoubleSubInstruction();
	}

	@Override
	protected CompilationError makeOperationUndefinedForTypeError(Type type, Object sourceContext) {
		return new OperationUndefinedForTypeCompilationError(OperationUndefinedForTypeCompilationError.Operation.Sub, type, sourceContext);
	}	

}

