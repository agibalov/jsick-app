package com.loki2302.jsick;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.loki2302.jsick.compiler.ProgramCompiler;
import com.loki2302.jsick.compiler.backend.jvm.JVMCodeGenerator;
import com.loki2302.jsick.dom.parser.ParseResult;
import com.loki2302.jsick.dom.parser.Parser;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.statements.Program;
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
		ProgramCompiler programCompiler = ProgramCompiler.makeDefaultCompiler(types);
		Context<Program> programContext = programCompiler.compile(parseResult.getProgram());
		if(!programContext.isOk()) {
			System.out.println("SEMANTIC ERRORS");
			return;
		}
		
		Program program = programContext.getValue();
		    		
    	JVMCodeGenerator jvmCompiler = new JVMCodeGenerator(types);    	
    	byte[] byteCode = jvmCompiler.generateCode(program);
    	
    	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("HelloWorld.class"));
    	bos.write(byteCode);
    	bos.flush();
    	bos.close();
    }		
}
