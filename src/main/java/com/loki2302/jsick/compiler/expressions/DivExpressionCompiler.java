package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.errors.OperationUndefinedForTypeCompilationError;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;
import com.loki2302.jsick.vm.instructions.DoubleDivInstruction;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntDivInstruction;

public class DivExpressionCompiler extends AbstractBinaryArithmeticExpressionCompiler<DivExpression> {

	public DivExpressionCompiler(Types types) {
		super(types);
	}

	@Override
	protected Instruction makeIntOperationInstruction() {
		return new IntDivInstruction();
	}

	@Override
	protected Instruction makeDoubleOperationInstruction() {
		return new DoubleDivInstruction();
	}

	@Override
	protected CompilationError makeOperationUndefinedForTypeError(Type type, Object sourceContext) {
		return new OperationUndefinedForTypeCompilationError(OperationUndefinedForTypeCompilationError.Operation.Div, type, sourceContext);
	}	

}

