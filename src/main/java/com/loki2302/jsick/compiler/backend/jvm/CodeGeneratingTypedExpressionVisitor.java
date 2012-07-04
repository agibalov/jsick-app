package com.loki2302.jsick.compiler.backend.jvm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.loki2302.jsick.LexicalContext;
import com.loki2302.jsick.expressions.AddExpression;
import com.loki2302.jsick.expressions.CastExpression;
import com.loki2302.jsick.expressions.DivExpression;
import com.loki2302.jsick.expressions.DoubleConstExpression;
import com.loki2302.jsick.expressions.IntConstExpression;
import com.loki2302.jsick.expressions.MulExpression;
import com.loki2302.jsick.expressions.RemExpression;
import com.loki2302.jsick.expressions.SubExpression;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.expressions.TypedExpressionVisitor;
import com.loki2302.jsick.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;

class CodeGeneratingTypedExpressionVisitor implements TypedExpressionVisitor<Object> {
	
	private final Types types;
	private final MethodVisitor methodVisitor;
	private final LexicalContext lexicalContext;
	
	public CodeGeneratingTypedExpressionVisitor(
			Types types, 
			MethodVisitor methodVisitor, 
			LexicalContext lexicalContext) {
		this.types = types;
		this.methodVisitor = methodVisitor;
		this.lexicalContext = lexicalContext;
	}
	
	@Override
	public Object visitIntConstExpression(IntConstExpression expression) {
		methodVisitor.visitLdcInsn(expression.getValue());
		return null;
	}

	@Override
	public Object visitDoubleConstExpression(DoubleConstExpression expression) {
		methodVisitor.visitLdcInsn(expression.getValue());
		return null;
	}

	@Override
	public Object visitCastExpression(CastExpression expression) {
		TypedExpression sourceExpression = expression.getExpression();
		sourceExpression.accept(this);
		
		Type sourceType = sourceExpression.getType();
		Type targetType = expression.getTargetType();
		if(sourceType.equals(types.IntType) && targetType.equals(types.DoubleType)) {
			methodVisitor.visitInsn(Opcodes.I2D);
		} else if(sourceType.equals(types.DoubleType) && targetType.equals(types.IntType)) {
			methodVisitor.visitInsn(Opcodes.D2I);
		} else {
			throw new RuntimeException();
		}
		
		return null;
	}

	@Override
	public Object visitAddExpression(AddExpression expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		if(expression.getType().equals(types.IntType)) {
			methodVisitor.visitInsn(Opcodes.IADD);
		} else if(expression.getType().equals(types.DoubleType)) {
			methodVisitor.visitInsn(Opcodes.DADD);
		} else {
			throw new RuntimeException();
		}			
		
		return null;
	}

	@Override
	public Object visitSubExpression(SubExpression expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		if(expression.getType().equals(types.IntType)) {
			methodVisitor.visitInsn(Opcodes.ISUB);
		} else if(expression.getType().equals(types.DoubleType)) {
			methodVisitor.visitInsn(Opcodes.DSUB);
		} else {
			throw new RuntimeException();
		}
		
		return null;
	}

	@Override
	public Object visitMulExpression(MulExpression expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		if(expression.getType().equals(types.IntType)) {
			methodVisitor.visitInsn(Opcodes.IMUL);
		} else if(expression.getType().equals(types.DoubleType)) {
			methodVisitor.visitInsn(Opcodes.DMUL);
		} else {
			throw new RuntimeException();
		}
		
		return null;
	}

	@Override
	public Object visitDivExpression(DivExpression expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		if(expression.getType().equals(types.IntType)) {
			methodVisitor.visitInsn(Opcodes.IDIV);
		} else if(expression.getType().equals(types.DoubleType)) {
			methodVisitor.visitInsn(Opcodes.DDIV);
		} else {
			throw new RuntimeException();
		}
		
		return null;
	}

	@Override
	public Object visitRemExpression(RemExpression expression) {
		expression.getLeft().accept(this);
		expression.getRight().accept(this);
		if(expression.getType().equals(types.IntType)) {
			methodVisitor.visitInsn(Opcodes.IREM);			
		} else {
			throw new RuntimeException();
		}
		
		return null;
	}

	@Override
	public Object visitVariableReferenceExpression(VariableReferenceExpression expression) {
		String variableName = expression.getVariableName();
		if(!lexicalContext.variableExists(variableName)) {
			throw new RuntimeException();
		}
		
		Type variableType = lexicalContext.getVariableType(variableName);
		int location = lexicalContext.getVariableLocation(variableName);
		if(variableType.equals(types.IntType)) {
			methodVisitor.visitIntInsn(Opcodes.ILOAD, location);
		} else if(variableType.equals(types.DoubleType)) {
			methodVisitor.visitIntInsn(Opcodes.DLOAD, location);
		} else {
			throw new RuntimeException();
		}
		
		return null;
	}
}