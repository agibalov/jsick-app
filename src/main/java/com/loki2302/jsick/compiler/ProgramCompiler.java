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
import com.loki2302.jsick.evaluator.expressions.AddTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.BinaryOperationEvaluator;
import com.loki2302.jsick.evaluator.expressions.CompilingDOMExpressionVisitor;
import com.loki2302.jsick.evaluator.expressions.DOMExpressionToTypedExpressionConverterEvaluator;
import com.loki2302.jsick.evaluator.expressions.AssignmentExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.MakeSureExpressionIsOfTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.VariableReferenceExpressionEvaluator;
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
import com.loki2302.jsick.expressions.TypedExpression;
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
		
		InitLaterEvaluator<DOMExpression, TypedExpression> compilingExpressionEvaluator = 
				new InitLaterEvaluator<DOMExpression, TypedExpression>(); 
		
		compilingExpressionEvaluator.setEvaluator(
				new DOMExpressionToTypedExpressionConverterEvaluator(
    					new CompilingDOMExpressionVisitor(
    							new IntConstExpressionEvaluator(types.IntType),
    							new DoubleConstExpressionEvaluator(types.DoubleType),
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
    									new RemTypedExpressionBuilderEvaluator()),
    							new VariableReferenceExpressionEvaluator(lexicalContext),
    							new AssignmentExpressionEvaluator(lexicalContext, compilingExpressionEvaluator, castEvaluator))));
		
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