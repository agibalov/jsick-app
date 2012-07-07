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

public class App {	
	
	public static void main(String[] args) throws IOException {		
		String code =
				"int x = 123; /* assign 123 to x */\n" + 
				"int y = 2 * x + 1;\n" +
				"double z = (x + y) / 2.0; // dividing int by double\n" + 
				"? x; // print x\n" + 
				"? y;\n" +
				"? z;\n" +
				"? z - 4; // print (z - 4) \n" + 
				"x=1; // x is now 1\n" + 
				"?x; // it is\n" + 
				"x=y=2; // x and y are now 2\n" + 
				"?x;?y; // they are\n" + 
				"?z=x=y=3; // x and y are now 3, z is 3.0, print z\n"; 
		
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
