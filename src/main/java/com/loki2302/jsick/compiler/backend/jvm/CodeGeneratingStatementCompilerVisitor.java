package com.loki2302.jsick.compiler.backend.jvm;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.loki2302.jsick.LexicalContext;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.expressions.TypedExpressionVisitor;
import com.loki2302.jsick.statements.ExpressionStatement;
import com.loki2302.jsick.statements.PrintStatement;
import com.loki2302.jsick.statements.StatementVisitor;
import com.loki2302.jsick.statements.VariableDefinitionStatement;
import com.loki2302.jsick.types.Type;
import com.loki2302.jsick.types.Types;

class CodeGeneratingStatementCompilerVisitor implements StatementVisitor<Object> {

	private final Types types;
	private final MethodVisitor methodVisitor;
	private final TypedExpressionVisitor<Object> expressionVisitor;
	private final LexicalContext lexicalContext;
	
	public CodeGeneratingStatementCompilerVisitor(
			Types types,
			MethodVisitor methodVisitor, 
			TypedExpressionVisitor<Object> expressionVisitor,
			LexicalContext lexicalContext) {
		this.types = types;
		this.methodVisitor = methodVisitor; 
		this.expressionVisitor = expressionVisitor;
		this.lexicalContext = lexicalContext;
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
		Type variableType = statement.getVariableType();
		String variableName = statement.getVariableName();
		TypedExpression expression = statement.getExpression();
		
		if(lexicalContext.variableExists(variableName)) {
			throw new RuntimeException();
		}
		
		lexicalContext.addVariable(variableName, variableType);
		int location = lexicalContext.getVariableLocation(variableName);
		
		expression.accept(expressionVisitor);
		if(variableType.equals(types.IntType)) {
			methodVisitor.visitIntInsn(Opcodes.ISTORE, location);
		} else if(variableType.equals(types.DoubleType)) {
			methodVisitor.visitIntInsn(Opcodes.DSTORE, location);
		} else {
			throw new RuntimeException();
		}
		
		return null;
	}
}