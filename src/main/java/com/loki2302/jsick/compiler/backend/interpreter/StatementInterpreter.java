package com.loki2302.jsick.compiler.backend.interpreter;

import java.util.Map;

import com.loki2302.jsick.statements.ExpressionStatement;
import com.loki2302.jsick.statements.PrintStatement;
import com.loki2302.jsick.statements.Statement;
import com.loki2302.jsick.statements.StatementVisitor;
import com.loki2302.jsick.statements.VariableDefinitionStatement;
import com.loki2302.jsick.types.Instance;

public class StatementInterpreter {
	private final ExpressionInterpreter expressionExecutor;
	private final Printer printer;
	private final Map<Instance, Object> variables;
	
	public StatementInterpreter(ExpressionInterpreter expressionExecutor, Printer printer, Map<Instance, Object> variables) {
		this.expressionExecutor = expressionExecutor;
		this.printer = printer;
		this.variables = variables;
	}
	
	public void interprete(Statement statement) {
		statement.accept(new StatementVisitor<Object>() {
			@Override
			public Object visitVariableDefinitionStatement(VariableDefinitionStatement statement) {
				Object result = expressionExecutor.interprete(statement.getExpression());
				variables.put(statement.getInstance(), result);
				return null;
			}

			@Override
			public Object visitExpressionStatement(ExpressionStatement statement) {
				expressionExecutor.interprete(statement.getExpression());
				return null;
			}

			@Override
			public Object visitPrintStatement(PrintStatement statement) {
				Object result = expressionExecutor.interprete(statement.getExpression());
				if(result instanceof Integer) {
					printer.printInt((Integer)result);
				} else if(result instanceof Double) {
					printer.printDouble((Double)result);
				} else {
					throw new RuntimeException();
				}
				
				return null;
			}				
		});
	}
}