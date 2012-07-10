package com.loki2302.jsick.compiler;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.dom.DOMProgram;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.statements.DOMStatement;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.InitLaterEvaluator;
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.evaluator.expressions.AssignmentExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.CompilingDOMExpressionVisitor;
import com.loki2302.jsick.evaluator.expressions.DOMExpressionToExpressionConverterEvaluator;
import com.loki2302.jsick.evaluator.expressions.VariableReferenceExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.DoubleConstExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.IntConstExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.AddExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.DivExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.MulExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.RemExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.SubExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.ArithmeticOperationTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.AssignmentSemanticsEvaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.MakeSureExpressionIsOfTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.RemOperationTypeEvaluator;
import com.loki2302.jsick.evaluator.statements.ExpressionStatementEvaluator;
import com.loki2302.jsick.evaluator.statements.PrintStatementEvaluator;
import com.loki2302.jsick.evaluator.statements.StatementEvaluator;
import com.loki2302.jsick.evaluator.statements.VariableDefinitionStatementEvaluator;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.types.Types;

public class ProgramCompiler {
	
	public final StatementCompiler statementCompiler;
	
	public ProgramCompiler(StatementCompiler statementCompiler) {
		this.statementCompiler = statementCompiler;
	}
	
	public Context<Program> compile(DOMProgram domProgram) {
		List<AbstractError> errors = new ArrayList<AbstractError>();
		
		List<Statement> statements = new ArrayList<Statement>(); 
		for(DOMStatement domStatement : domProgram.getStatements()) {			
			Context<Statement> statementContext = statementCompiler.compile(domStatement);			
			if(!statementContext.isOk()) {
				errors.add(statementContext.getError());
				continue;
			} 
			
			Statement statement = statementContext.getValue();			
			statements.add(statement);
		}
		
		if(!errors.isEmpty()) {
			return Context.<Program>fail(new CompositeError(null, domProgram, errors));
		}
		
		return Context.<Program>ok(new Program(statements));
	}
	
	public static ProgramCompiler makeDefaultCompiler(Types types) {
		LexicalContext lexicalContext = new LexicalContext();
		
		ArithmeticOperationTypeEvaluator addSubMulDivOperationTypeEvaluator = 
				new ArithmeticOperationTypeEvaluator(types);		
		
		MakeSureExpressionIsOfTypeEvaluator castEvaluator = new MakeSureExpressionIsOfTypeEvaluator(); 
		
		InitLaterEvaluator<DOMExpression, Expression> compilingExpressionEvaluator = 
				new InitLaterEvaluator<DOMExpression, Expression>(); 
		
		compilingExpressionEvaluator.setEvaluator(
				new DOMExpressionToExpressionConverterEvaluator(
    					new CompilingDOMExpressionVisitor(
    							new IntConstExpressionEvaluator(types.IntType),
    							new DoubleConstExpressionEvaluator(types.DoubleType),
    							new AddExpressionEvaluator(
    									compilingExpressionEvaluator,
    									addSubMulDivOperationTypeEvaluator),    							
    							new SubExpressionEvaluator(
    	    							compilingExpressionEvaluator,
    	    							addSubMulDivOperationTypeEvaluator),
    	    					new MulExpressionEvaluator(
    	    							compilingExpressionEvaluator,
    	    							addSubMulDivOperationTypeEvaluator),
    	    					new DivExpressionEvaluator(
    	    							compilingExpressionEvaluator,
    	    							addSubMulDivOperationTypeEvaluator),
    	    					new RemExpressionEvaluator(
    	    							compilingExpressionEvaluator,
    	    							new RemOperationTypeEvaluator(types)),
    							new VariableReferenceExpressionEvaluator(lexicalContext),
    							new AssignmentExpressionEvaluator(
    									compilingExpressionEvaluator,
    									new AssignmentSemanticsEvaluator()))));
		
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(compilingExpressionEvaluator);
		
		StatementEvaluator domStatementToStatementConverterEvaluator = 
				new StatementEvaluator(
						new ExpressionStatementEvaluator(expressionCompiler),
						new PrintStatementEvaluator(expressionCompiler),
						new VariableDefinitionStatementEvaluator(
								expressionCompiler, lexicalContext, types, castEvaluator)); 
		
		StatementCompiler statementCompiler = new StatementCompiler(domStatementToStatementConverterEvaluator);
		ProgramCompiler programCompiler = new ProgramCompiler(statementCompiler);
		
		return programCompiler;			
	}		
}