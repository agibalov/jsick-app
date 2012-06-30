package com.loki2302.jsick.compiler.expressions;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.errors.CannotDeduceCommonTypeCompilationError;
import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntToDoubleInstruction;
import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;

import com.loki2302.jsick.compiler.typeevaluation.BinaryEvaluationContext;
import com.loki2302.jsick.compiler.typeevaluation.TypeEvaluationResult;
import com.loki2302.jsick.compiler.typeevaluation.TypeEvaluator;
import static com.loki2302.jsick.compiler.typeevaluation.Fluency.*;

public abstract class AbstractBinaryArithmeticExpressionCompiler<E extends BinaryExpression> extends AbstractExpressionCompiler<E> {	
	
	private final Types types;
	private final TypeEvaluator typeEvaluator;
	
	public AbstractBinaryArithmeticExpressionCompiler(Types types) {		
		this.types = types;
		this.typeEvaluator = makeTypeEvaluator(types);
	}
	
	@Override
	public ExpressionCompilationResult compileImpl(E expression, PrecompilationResults precompilationResults) {
		
		Expression leftExpression = expression.getLeft();
		Expression rightExpression = expression.getRight();
		
		Type leftType = precompilationResults.getType(leftExpression);
		Type rightType = precompilationResults.getType(rightExpression);
		
		TypeEvaluationResult evaluationResult = typeEvaluator.evaluate(new BinaryEvaluationContext(leftType, rightType));
		if(!evaluationResult.isOk()) {
			return ExpressionCompilationResult.error(new CannotDeduceCommonTypeCompilationError(leftType, rightType, expression));
		}
		
		Type operationType = evaluationResult.getType();
								
		List<Instruction> instructions = new ArrayList<Instruction>();		
		instructions.addAll(precompilationResults.getInstructions(leftExpression));
		if(!leftType.equals(operationType)) {
			instructions.add(new IntToDoubleInstruction());
		}
		
		instructions.addAll(precompilationResults.getInstructions(rightExpression));
		if(!rightType.equals(operationType)) {
			instructions.add(new IntToDoubleInstruction());
		}
				
		if(operationType.equals(types.intType)) {
			instructions.add(makeIntOperationInstruction());
		} else if(operationType.equals(types.doubleType)) {
			instructions.add(makeDoubleOperationInstruction());
		} else {
			return ExpressionCompilationResult.error(makeOperationUndefinedForTypeError(operationType, expression));
		}
		
		return ExpressionCompilationResult.ok(instructions, operationType);		
	}
	
	protected abstract Instruction makeIntOperationInstruction(); 
	protected abstract Instruction makeDoubleOperationInstruction();
	protected abstract CompilationError makeOperationUndefinedForTypeError(Type type, Object sourceContext);
	
	private static TypeEvaluator makeTypeEvaluator(Types types) {
		
		TypeEvaluator left = or(
    			same(first(), fixed(types.intType)), 
    			same(first(), fixed(types.doubleType)));
	    	
    	TypeEvaluator right = or(
    			same(second(), fixed(types.intType)), 
    			same(second(), fixed(types.doubleType)));	    	
	    	
    	TypeEvaluator op = or(
    			same(left, right), 
    			castable(left, right), 
    			castable(right, left));
    	
    	return op;
	}
}

