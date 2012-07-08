package com.loki2302.jsick;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.compiler.ProgramCompiler;
import com.loki2302.jsick.compiler.backend.interpreter.ProgramInterpreter;
import com.loki2302.jsick.compiler.backend.jvm.JVMCodeGenerator;
import com.loki2302.jsick.dom.parser.ParseResult;
import com.loki2302.jsick.dom.parser.Parser;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Types;

// TODO: add parser error handling
// TODO: add compiler error handling
// TODO: add global error handling

public class Playground {	
	
	public static void main(String[] args) throws IOException {		
		String code =
				"int x = 0;\n" + 
				"int y = 0;\n" + 
				"int z = (x = 1) + (y = 2);" + 
				"?x;?y;?z;"; 
		
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
		
		// write Java bytecode
    	JVMCodeGenerator jvmCompiler = new JVMCodeGenerator(types);
    	byte[] byteCode = jvmCompiler.generateCode(program, "HelloWorld");
    	
    	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("HelloWorld.class"));
    	bos.write(byteCode);
    	bos.flush();
    	bos.close();
    	
    	// interprete
    	Map<Instance, Object> variables = new HashMap<Instance, Object>(); 
    	ProgramInterpreter programInterpreter = ProgramInterpreter.makeDefaultProgramInterpreter(types, variables);
    	programInterpreter.interprete(program);    	
    }	
	
}
