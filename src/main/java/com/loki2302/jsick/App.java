package com.loki2302.jsick;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.ExpressionCompiler;
import com.loki2302.jsick.compiler.StatementCompiler;
import com.loki2302.jsick.compiler.backend.jvm.JVMCodeGenerator;
import com.loki2302.jsick.dom.DOMProgram;
import com.loki2302.jsick.dom.parser.ParseResult;
import com.loki2302.jsick.dom.parser.Parser;
import com.loki2302.jsick.dom.statements.DOMStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.expressions.AddSubMulDivOperationTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.AddTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.BinaryOperationEvaluator;
import com.loki2302.jsick.evaluator.expressions.DOMExpressionToTypedExpressionConverterEvaluator;
import com.loki2302.jsick.evaluator.expressions.DivTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.DoubleConstExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.IntConstExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.MulTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.RemOperationTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.RemTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.SubTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.statements.DOMExpressionStatementToStatementConverterEvaluator;
import com.loki2302.jsick.evaluator.statements.DOMPrintStatementToStatementConverterEvaluator;
import com.loki2302.jsick.evaluator.statements.DOMStatementToStatementConverterEvaluator;
import com.loki2302.jsick.evaluator.statements.DOMVariableDefinitionStatementToStatementConverterEvaluator;
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
		StatementCompiler statementCompiler = makeStatementCompiler(types);
		
		DOMProgram domProgram = parseResult.getProgram();
		List<Statement> statements = new ArrayList<Statement>(); 
		for(DOMStatement domStatement : domProgram.getStatements()) {			
			Context<Statement> statementContext = statementCompiler.compile(domStatement);
			if(!statementContext.isOk()) {
				throw new RuntimeException(); // TODO: error handling
			}
			
			Statement statement = statementContext.getValue();			
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
	
	private static StatementCompiler makeStatementCompiler(Types types) {
		LexicalContext lexicalContext = new LexicalContext();
		
		AddSubMulDivOperationTypeEvaluator addSubMulDivOperationTypeEvaluator = 
				new AddSubMulDivOperationTypeEvaluator(types);		
    	DOMExpressionToTypedExpressionConverterEvaluator compilingExpressionEvaluator = 
    			new DOMExpressionToTypedExpressionConverterEvaluator(
	    			lexicalContext,
	    			new IntConstExpressionEvaluator(),
	    			new DoubleConstExpressionEvaluator(),
	    			new BinaryOperationEvaluator(
	    					addSubMulDivOperationTypeEvaluator, 
	    					new AddTypedExpressionBuilderEvaluator()),
	    			new BinaryOperationEvaluator(
	    					addSubMulDivOperationTypeEvaluator, 
	    					new SubTypedExpressionBuilderEvaluator()),
	    			new BinaryOperationEvaluator(
	    					addSubMulDivOperationTypeEvaluator, 
	    					new MulTypedExpressionBuilderEvaluator()),
	    			new BinaryOperationEvaluator(
	    					addSubMulDivOperationTypeEvaluator,  
	    					new DivTypedExpressionBuilderEvaluator()),
	    			new BinaryOperationEvaluator(
	    					new RemOperationTypeEvaluator(types), 
	    					new RemTypedExpressionBuilderEvaluator()));
		
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(compilingExpressionEvaluator);
		
		DOMStatementToStatementConverterEvaluator domStatementToStatementConverterEvaluator = 
				new DOMStatementToStatementConverterEvaluator(
						new DOMExpressionStatementToStatementConverterEvaluator(expressionCompiler),
						new DOMPrintStatementToStatementConverterEvaluator(expressionCompiler),
						new DOMVariableDefinitionStatementToStatementConverterEvaluator(
								expressionCompiler, lexicalContext, types)); 
		
		StatementCompiler statementCompiler = new StatementCompiler(domStatementToStatementConverterEvaluator);
		
		return statementCompiler;
	}
	
}
