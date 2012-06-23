package com.loki2302.jsick.compiler.expressions;

import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.errors.OperationUndefinedForTypeCompilationError;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.types.JType;
import com.loki2302.jsick.vm.instructions.DoubleDivInstruction;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntDivInstruction;

public class DivExpressionCompiler extends AbstractBinaryArithmeticExpressionCompiler<DivExpression> {

	public DivExpressionCompiler(ExpressionCompiler expressionCompiler, IntType intType, DoubleType doubleType) {
		super(expressionCompiler, intType, doubleType);
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
	protected CompilationError makeOperationUndefinedForTypeError(JType type) {
		return new OperationUndefinedForTypeCompilationError(OperationUndefinedForTypeCompilationError.Operation.Div, type);
	}	

}

