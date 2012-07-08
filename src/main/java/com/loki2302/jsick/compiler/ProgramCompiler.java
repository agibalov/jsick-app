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
import com.loki2302.jsick.evaluator.expressions.AddSubMulDivOperationTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.AddExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.AssignmentSemanticsEvaluator;
import com.loki2302.jsick.evaluator.expressions.AssignmentExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.BinaryOperationEvaluator;
import com.loki2302.jsick.evaluator.expressions.CompilingDOMExpressionVisitor;
import com.loki2302.jsick.evaluator.expressions.DOMExpressionToExpressionConverterEvaluator;
import com.loki2302.jsick.evaluator.expressions.MakeSureExpressionIsOfTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.VariableReferenceExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.DivExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.DoubleConstExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.IntConstExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.MulExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.RemOperationTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.RemExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.SubExpressionBuilderEvaluator;
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
		
		AddSubMulDivOperationTypeEvaluator addSubMulDivOperationTypeEvaluator = 
				new AddSubMulDivOperationTypeEvaluator(types);		
		
		MakeSureExpressionIsOfTypeEvaluator castEvaluator = new MakeSureExpressionIsOfTypeEvaluator(); 
		
		InitLaterEvaluator<DOMExpression, Expression> compilingExpressionEvaluator = 
				new InitLaterEvaluator<DOMExpression, Expression>(); 
		
		compilingExpressionEvaluator.setEvaluator(
				new DOMExpressionToExpressionConverterEvaluator(
    					new CompilingDOMExpressionVisitor(
    							new IntConstExpressionEvaluator(types.IntType),
    							new DoubleConstExpressionEvaluator(types.DoubleType),
    							new BinaryOperationEvaluator(
    									addSubMulDivOperationTypeEvaluator,
    									new AddExpressionBuilderEvaluator()),
    							new BinaryOperationEvaluator(
    									addSubMulDivOperationTypeEvaluator,
    									new SubExpressionBuilderEvaluator()),
    							new BinaryOperationEvaluator(
    									addSubMulDivOperationTypeEvaluator, 
    									new MulExpressionBuilderEvaluator()),
    							new BinaryOperationEvaluator(
    									addSubMulDivOperationTypeEvaluator,  
    									new DivExpressionBuilderEvaluator()),
    							new BinaryOperationEvaluator(
    									new RemOperationTypeEvaluator(types), 
    									new RemExpressionBuilderEvaluator()),
    							new VariableReferenceExpressionEvaluator(lexicalContext),
    							new BinaryOperationEvaluator(
    									new AssignmentSemanticsEvaluator(),
    									new AssignmentExpressionBuilderEvaluator()))));
		
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