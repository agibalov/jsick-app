package com.loki2302.jsick;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.compiler.backend.interpreter.ProgramInterpreter;
import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Types;

public class InterpreterApp extends AbstractJsickApp {
	
	public static void main(String[] args) throws IOException {
		compileAndPassToBackEnd(args, new BackEnd() {
			@Override
			public void process(Program program, Types types, String sourceName) {
				Map<Instance, Object> variables = new HashMap<Instance, Object>(); 
		    	ProgramInterpreter programInterpreter = ProgramInterpreter.makeDefaultProgramInterpreter(types, variables);
		    	programInterpreter.interprete(program);				
			}			
		});
	}
	
}
