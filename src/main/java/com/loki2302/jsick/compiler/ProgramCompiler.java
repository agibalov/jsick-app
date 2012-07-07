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
import com.loki2302.jsick.evaluator.expressions.AssignmentOperationTypeEvaluator;
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
import com.loki2302.jsick.evaluator.statements.DOMExpressionStatementToStatementConverterEvaluator;
import com.loki2302.jsick.evaluator.statements.DOMPrintStatementToStatementConverterEvaluator;
import com.loki2302.jsick.evaluator.statements.DOMStatementToStatementConverterEvaluator;
import com.loki2302.jsick.evaluator.statements.DOMVariableDefinitionStatementToStatementConverterEvaluator;
import com.loki2302.jsick.expressions.LvalueExpression;
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
    							new BinaryOperationEvaluator<Expression, Expression>(
    									addSubMulDivOperationTypeEvaluator,
    									new AddExpressionBuilderEvaluator()),
    							new BinaryOperationEvaluator<Expression, Expression>(
    									addSubMulDivOperationTypeEvaluator,
    									new SubExpressionBuilderEvaluator()),
    							new BinaryOperationEvaluator<Expression, Expression>(
    									addSubMulDivOperationTypeEvaluator, 
    									new MulExpressionBuilderEvaluator()),
    							new BinaryOperationEvaluator<Expression, Expression>(
    									addSubMulDivOperationTypeEvaluator,  
    									new DivExpressionBuilderEvaluator()),
    							new BinaryOperationEvaluator<Expression, Expression>(
    									new RemOperationTypeEvaluator(types), 
    									new RemExpressionBuilderEvaluator()),
    							new VariableReferenceExpressionEvaluator(lexicalContext),
    							new BinaryOperationEvaluator<LvalueExpression, Expression>(
    									new AssignmentOperationTypeEvaluator(),
    									new AssignmentExpressionBuilderEvaluator()))));
		
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(compilingExpressionEvaluator);
		
		DOMStatementToStatementConverterEvaluator domStatementToStatementConverterEvaluator = 
				new DOMStatementToStatementConverterEvaluator(
						new DOMExpressionStatementToStatementConverterEvaluator(expressionCompiler),
						new DOMPrintStatementToStatementConverterEvaluator(expressionCompiler),
						new DOMVariableDefinitionStatementToStatementConverterEvaluator(
								expressionCompiler, lexicalContext, types, castEvaluator)); 
		
		StatementCompiler statementCompiler = new StatementCompiler(domStatementToStatementConverterEvaluator);
		ProgramCompiler programCompiler = new ProgramCompiler(statementCompiler);
		
		return programCompiler;			
	}		
}