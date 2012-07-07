package com.loki2302.jsick;

import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.compiler.ProgramCompiler;
import com.loki2302.jsick.compiler.backend.interpreter.ProgramInterpreter;
import com.loki2302.jsick.dom.parser.ParseResult;
import com.loki2302.jsick.dom.parser.Parser;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Types;

public class IntegrationHelper {
	
	public static Variables execute(String sourceCode) {
		Parser parser = new Parser();
		ParseResult parseResult = parser.parse(sourceCode);
		if(!parseResult.isOk()) {
			throw new RuntimeException();
		}
		
		Types types = new Types();
		ProgramCompiler programCompiler = ProgramCompiler.makeDefaultCompiler(types);
		Context<Program> programContext = programCompiler.compile(parseResult.getProgram());
		if(!programContext.isOk()) {
			throw new RuntimeException();
		}
		
		Program program = programContext.getValue();
		
    	Map<Instance, Object> variables = new HashMap<Instance, Object>(); 
    	ProgramInterpreter programInterpreter = ProgramInterpreter.makeDefaultProgramInterpreter(types, variables);
    	programInterpreter.interprete(program);
		
		return new Variables(variables);
	}
	
	public static class Variables {
		private final Map<Instance, Object> variables;
		
		public Variables(Map<Instance, Object> variables) {
			this.variables = variables;
		}
		
		public int getIntValue(String name) {
			return (Integer)getValue(name);
		}
		
		public double getDoubleValue(String name) {
			return (Double)getValue(name);
		}
		
		private Object getValue(String name) {
			for(Instance instance : variables.keySet()) {
				if(instance.getName().equals(name)) {
					return variables.get(instance);
				}
			}
			
			throw new RuntimeException();
		}
	}

}
