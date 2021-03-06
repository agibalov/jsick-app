package com.loki2302.jsick.compiler.backend.jvm;

import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.types.Types;

public class JVMCodeGenerator {
	
	private final Types types;
	
	public JVMCodeGenerator(Types types) {
		this.types = types;
	}
	
	public byte[] generateCode(Program program, String sourceName) throws IOException {		
		ClassWriter cw = new ClassWriter(0);    	
    	cw.visit(49 /*???*/, Opcodes.ACC_PUBLIC, sourceName, null, "java/lang/Object", null);
    	
    	cw.visitSource(String.format("%s.java", sourceName), null);
    	
    	MethodVisitor initVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL,
                "java/lang/Object",
                "<init>",
                "()V");
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();
    	
    	MethodVisitor mainVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        
    	LocalsContext localsContext = new LocalsContext();
    	CodeGeneratingTypedExpressionVisitor expressionVisitor = new CodeGeneratingTypedExpressionVisitor(
    			types,
    			mainVisitor,
    			localsContext);    	
    	CodeGeneratingStatementCompilerVisitor statementVisitor = new CodeGeneratingStatementCompilerVisitor(
    			types, 
    			mainVisitor, 
    			expressionVisitor,
    			localsContext);
		for(Statement statement : program.getStatements()) {
			statement.accept(statementVisitor);
		}
                
        mainVisitor.visitInsn(Opcodes.RETURN);
        // TODO: how do i compute max stack size?
        mainVisitor.visitMaxs(100, localsContext.getLocalsCount() + 1); // TODO: why +1 here?
        mainVisitor.visitEnd();
    	
    	cw.visitEnd();
    	
    	return cw.toByteArray();		
	}

}
