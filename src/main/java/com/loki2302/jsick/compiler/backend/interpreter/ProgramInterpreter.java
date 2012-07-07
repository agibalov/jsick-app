package com.loki2302.jsick.compiler.backend.interpreter;

import java.util.Map;

import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Types;

public class ProgramInterpreter {
	private final StatementInterpreter statementExecutor;
	
	public ProgramInterpreter(StatementInterpreter statementExecutor) {
		this.statementExecutor = statementExecutor;
	}
	
	public void interprete(Program program) {
		for(Statement statement : program.getStatements()) {
			statementExecutor.interprete(statement);
		}
	}		
	
	public static ProgramInterpreter makeDefaultProgramInterpreter(Types types, Map<Instance, Object> variables) {
		ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter(types, variables);
    	StatementInterpreter statementInterpreter = new StatementInterpreter(
    			expressionInterpreter, 
    			new Printer.SystemOutPrintlnPrinter(),
    			variables);
    	ProgramInterpreter programInterpreter = new ProgramInterpreter(statementInterpreter);
    	return programInterpreter;	
	}
}