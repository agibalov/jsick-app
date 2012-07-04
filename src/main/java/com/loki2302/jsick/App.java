package com.loki2302.jsick;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.ExpressionCompiler;
import com.loki2302.jsick.compiler.StatementCompiler;
import com.loki2302.jsick.compiler.StatementCompilingVisitor;
import com.loki2302.jsick.compiler.backend.jvm.JVMCodeGenerator;
import com.loki2302.jsick.dom.DOMProgram;
import com.loki2302.jsick.dom.parser.ParseResult;
import com.loki2302.jsick.dom.parser.Parser;
import com.loki2302.jsick.dom.statements.DOMStatement;
import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.types.Types;

public class App {	
	
	public static void main(String[] args) throws IOException {
		String code =
				"int x = 123; /* assign 123 to x */\n" + 
				"int y = 2 * x + 1;\n" +
				"double z = (x + y) / 2.0; // dividing int by double\n" + 
				"? x; // print x\n" + 
				"? y;\n" +
				"? z;\n" +
				"? z - 4; // print (z - 4) \n"; 
		
		Parser parser = new Parser();
		ParseResult parseResult = parser.parse(code);
		if(!parseResult.isOk()) {
			System.out.println("SYNTAX ERRORS");
			return;
		}
		
		Types types = new Types();
		LexicalContext lexicalContext = new LexicalContext();
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(types, lexicalContext);
		StatementCompilingVisitor statementCompilingVisitor = new StatementCompilingVisitor(types, expressionCompiler, lexicalContext);
		StatementCompiler statementCompiler = new StatementCompiler(statementCompilingVisitor);
		
		DOMProgram domProgram = parseResult.getProgram();
		List<Statement> statements = new ArrayList<Statement>(); 
		for(DOMStatement domStatement : domProgram.getStatements()) {			
			Statement statement = statementCompiler.compile(domStatement);
			statements.add(statement);
		}
		    		
    	JVMCodeGenerator jvmCompiler = new JVMCodeGenerator(types);
    	Program program = new Program(statements);
    	byte[] byteCode = jvmCompiler.generateCode(program);
    	
    	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("HelloWorld.class"));
    	bos.write(byteCode);
    	bos.flush();
    	bos.close();
    }
	
}
