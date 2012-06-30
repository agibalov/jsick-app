package com.loki2302.jsick.compiler.expressions;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.errors.CannotDeduceCommonTypeCompilationError;
import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntToDoubleInstruction;
import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;

public abstract class AbstractBinaryArithmeticExpressionCompiler<E extends BinaryExpression> extends AbstractExpressionCompiler<E> {	
	
	private final IntType intType;
	private final DoubleType doubleType;
	
	public AbstractBinaryArithmeticExpressionCompiler(IntType intType, DoubleType doubleType) {		
		this.intType = intType;
		this.doubleType = doubleType;
	}
	
	@Override
	public ExpressionCompilationResult compileImpl(E expression, PrecompilationResults precompilationResults) {
		
		Expression leftExpression = expression.getLeft();
		Expression rightExpression = expression.getRight();
		
		Type leftType = precompilationResults.getType(leftExpression);
		Type rightType = precompilationResults.getType(rightExpression);
				
		Type operationType = null;
		if(leftType.equals(rightType)) {
			operationType = leftType;
		} else if(leftType.canImplicitlyCastTo(rightType)) {			
			operationType = rightType;
		} else if(rightType.canImplicitlyCastTo(leftType)) {
			operationType = leftType;
		} else {
			return ExpressionCompilationResult.error(new CannotDeduceCommonTypeCompilationError(leftType, rightType, expression));
		}
		
		List<Instruction> instructions = new ArrayList<Instruction>();		
		instructions.addAll(precompilationResults.getInstructions(leftExpression));
		if(!leftType.equals(operationType)) {
			instructions.add(new IntToDoubleInstruction());
		}
		
		instructions.addAll(precompilationResults.getInstructions(rightExpression));
		if(!rightType.equals(operationType)) {
			instructions.add(new IntToDoubleInstruction());
		}
				
		if(operationType.equals(intType)) {
			instructions.add(makeIntOperationInstruction());
		} else if(operationType.equals(doubleType)) {
			instructions.add(makeDoubleOperationInstruction());
		} else {
			return ExpressionCompilationResult.error(makeOperationUndefinedForTypeError(operationType, expression));
		}
		
		return ExpressionCompilationResult.ok(instructions, operationType);		
	}
	
	protected abstract Instruction makeIntOperationInstruction(); 
	protected abstract Instruction makeDoubleOperationInstruction();
	protected abstract CompilationError makeOperationUndefinedForTypeError(Type type, Object sourceContext);
}

