package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.errors.OperationUndefinedForTypeCompilationError;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;
import com.loki2302.jsick.vm.instructions.DoubleMulInstruction;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntMulInstruction;

public class MulExpressionCompiler extends AbstractBinaryArithmeticExpressionCompiler<MulExpression> {

	public MulExpressionCompiler(Types types) {
		super(types);
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
	protected CompilationError makeOperationUndefinedForTypeError(Type type, Object sourceContext) {
		return new OperationUndefinedForTypeCompilationError(OperationUndefinedForTypeCompilationError.Operation.Mul, type, sourceContext);
	}	

}

