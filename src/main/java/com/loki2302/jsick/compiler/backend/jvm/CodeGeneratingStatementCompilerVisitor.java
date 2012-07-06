package com.loki2302.jsick.compiler.backend.jvm;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.expressions.TypedExpressionVisitor;
import com.loki2302.jsick.statements.ExpressionStatement;
import com.loki2302.jsick.statements.PrintStatement;
import com.loki2302.jsick.statements.StatementVisitor;
import com.loki2302.jsick.statements.VariableDefinitionStatement;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;

class CodeGeneratingStatementCompilerVisitor implements StatementVisitor<Object> {
	
	private final Types types;
	private final MethodVisitor methodVisitor;
	private final TypedExpressionVisitor<Object> expressionVisitor;
	private final LocalsContext localsContext;
	
	public CodeGeneratingStatementCompilerVisitor(
			Types types,
			MethodVisitor methodVisitor, 
			TypedExpressionVisitor<Object> expressionVisitor,
			LocalsContext localsContext) {
		this.types = types;
		this.methodVisitor = methodVisitor; 
		this.expressionVisitor = expressionVisitor;
		this.localsContext = localsContext;
	}

	@Override
	public Object visitExpressionStatement(ExpressionStatement statement) {
		TypedExpression expression = statement.getExpression();
		expression.accept(expressionVisitor);
		methodVisitor.visitInsn(Opcodes.POP);
		return null;
	}

	@Override
	public Object visitPrintStatement(PrintStatement statement) {					
		methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	    
		TypedExpression expression = statement.getExpression();
		expression.accept(expressionVisitor);
	    
	    if(expression.getType().equals(types.IntType)) {
	    	methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V");        	
	    } else if(expression.getType().equals(types.DoubleType)) {
	    	methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V");
	    } else {
	    	throw new RuntimeException();
	    }
		
		return null;
	}

	@Override
	public Object visitVariableDefinitionStatement(VariableDefinitionStatement statement) {
		Instance instance = statement.getInstance();
		Type instanceType = instance.getType();
		TypedExpression expression = statement.getExpression();
		
		localsContext.addLocal(instance);
		int location = localsContext.getLocalIndex(instance);
		
		expression.accept(expressionVisitor);
		if(instanceType.equals(types.IntType)) {
			methodVisitor.visitIntInsn(Opcodes.ISTORE, location);
		} else if(instanceType.equals(types.DoubleType)) {
			methodVisitor.visitIntInsn(Opcodes.DSTORE, location);
		} else {
			throw new RuntimeException();
		}
		
		return null;
	}
}