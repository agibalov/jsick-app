package com.loki2302.jsick;

import java.io.BufferedOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.parboiled.support.IndexRange;

import com.loki2302.jsick.compiler.ProgramCompiler;
import com.loki2302.jsick.compiler.backend.interpreter.ProgramInterpreter;
import com.loki2302.jsick.compiler.backend.jvm.JVMCodeGenerator;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.parser.CodePositionInfo;
import com.loki2302.jsick.dom.parser.ParseResult;
import com.loki2302.jsick.dom.parser.Parser;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Types;
import com.loki2302.jsick.evaluator.expressions.errors.CannotCastImplicitlyError;
import com.loki2302.jsick.expressions.Expression;

// TODO: add parser error handling
// TODO: add compiler error handling
// TODO: add global error handling

public class Playground {	
	
	public static void dumpError(ParseResult parseResult, AbstractError error) {
		
		if(error instanceof CompositeError) {
			for(AbstractError e : ((CompositeError)error).getEvaluatorErrors()) {
				dumpError(parseResult, e);
			}
			return;
		} else if(error instanceof CannotCastImplicitlyError) {
			CannotCastImplicitlyError e = (CannotCastImplicitlyError)error;
			Expression expression = e.getExpression();
			DOMExpression domExpression = expression.getSourceDOMExpression();
			IndexRange matchRange = domExpression.getMatchRange();
			CodePositionInfo codePositionInfo = parseResult.getCodePositionInfo(matchRange);
			System.out.printf("[Line %d, Column %d]\n", 
					codePositionInfo.getLine(), 
					codePositionInfo.getColumn());
			System.out.println(codePositionInfo.getLineString());
			
			for(int i = 1; i < codePositionInfo.getColumn(); ++i) {
				System.out.print(" ");
			}
			
			for(int i = 0; i < matchRange.length(); ++i) {
				System.out.print("^");
			}
			
			System.out.println();
			
			System.out.printf("Cannot cast expression of type %s to type %s\n", 
					e.getExpression().getType().getName(), 
					e.getType().getName());			
		} else {	
			System.out.println(error);
		}
		System.out.println("----------------");
	}
	
	public static void main(String[] args) throws IOException {		
		String code =
				"int x = 3.0;int y = 1 + 2.0;";
		
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
			dumpError(parseResult, programContext.getError());
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
