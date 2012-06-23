package com.loki2302.jsick.compiler.expressions;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.errors.CannotDeduceCommonTypeCompilationError;
import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.errors.DependencyHasErrorsCompilationError;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.types.JType;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntToDoubleInstruction;
import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;

public abstract class AbstractBinaryArithmeticExpressionCompiler<E extends BinaryExpression> extends AbstractExpressionCompiler<E> {
	
	private final ExpressionCompiler expressionCompiler;
	private final IntType intType;
	private final DoubleType doubleType;
	
	public AbstractBinaryArithmeticExpressionCompiler(ExpressionCompiler expressionCompiler, IntType intType, DoubleType doubleType) {
		this.expressionCompiler = expressionCompiler;
		this.intType = intType;
		this.doubleType = doubleType;
	}

	@Override
	public ExpressionCompilationResult compile(E expression) {
		
		Expression leftExpression = expression.getLeft();
		ExpressionCompilationResult leftResult = expressionCompiler.compile(leftExpression);		
		
		Expression rightExpression = expression.getRight();
		ExpressionCompilationResult rightResult = expressionCompiler.compile(rightExpression);
		
		List<ExpressionCompilationResult> badResults = new ArrayList<ExpressionCompilationResult>();		
		if(leftResult.hasErrors()) {
			badResults.add(leftResult);
		}
		
		if(rightResult.hasErrors()) {
			badResults.add(rightResult);
		}
		
		if(!badResults.isEmpty()) {
			return ExpressionCompilationResult.error(new DependencyHasErrorsCompilationError(badResults));
		}
		
		JType leftType = leftResult.getType();
		JType rightType = rightResult.getType();	
		
		JType operationType = null;
		List<Instruction> instructions = new ArrayList<Instruction>();
		if(leftType.equals(rightType)) {
			operationType = leftType;			
			instructions.addAll(leftResult.getInstructions());
			instructions.addAll(rightResult.getInstructions());			
		}
		
		if(leftType.canImplicitlyCastTo(rightType)) {			
			operationType = rightType;
			instructions.addAll(leftResult.getInstructions());			
			instructions.add(new IntToDoubleInstruction());			
			instructions.addAll(rightResult.getInstructions());			
		}
		
		if(rightType.canImplicitlyCastTo(leftType)) {
			operationType = leftType;
			instructions.addAll(leftResult.getInstructions());					
			instructions.addAll(rightResult.getInstructions());
			instructions.add(new IntToDoubleInstruction());
		}
		
		if(operationType == null) {
			return ExpressionCompilationResult.error(new CannotDeduceCommonTypeCompilationError(leftType, rightType));
		}
		
		if(operationType.equals(intType)) {
			instructions.add(makeIntOperationInstruction());
		} else if(operationType.equals(doubleType)) {
			instructions.add(makeDoubleOperationInstruction());
		} else {
			return ExpressionCompilationResult.error(makeOperationUndefinedForTypeError(operationType));
		}
		
		return ExpressionCompilationResult.ok(instructions, operationType);		
	}
	
	protected abstract Instruction makeIntOperationInstruction(); 
	protected abstract Instruction makeDoubleOperationInstruction();
	protected abstract CompilationError makeOperationUndefinedForTypeError(JType type);
}

