package com.loki2302.jsick;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.model.AssignmentExpressionStatement;
import com.loki2302.jsick.model.PrintStatement;
import com.loki2302.jsick.model.Program;
import com.loki2302.jsick.model.Statement;
import com.loki2302.jsick.model.expressions.AddExpression;
import com.loki2302.jsick.model.expressions.DivExpression;
import com.loki2302.jsick.model.expressions.Expression;
import com.loki2302.jsick.model.expressions.LiteralExpression;
import com.loki2302.jsick.model.expressions.MulExpression;
import com.loki2302.jsick.model.expressions.SubExpression;
import com.loki2302.jsick.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.parser.tree.ArithmExpressionNode;
import com.loki2302.jsick.parser.tree.ExpressionNode;
import com.loki2302.jsick.parser.tree.LiteralExpressionNode;
import com.loki2302.jsick.parser.tree.PrintStatementNode;
import com.loki2302.jsick.parser.tree.ProgramNode;
import com.loki2302.jsick.parser.tree.SetVariableStatementNode;
import com.loki2302.jsick.parser.tree.StatementNode;
import com.loki2302.jsick.parser.tree.VariableReferenceNode;
import com.loki2302.jsick.parser.tree.ArithmExpressionNode.Operation;

public class ModelBuilder {
	
	public static Program build(ProgramNode programNode) {
		List<Statement> statements = new ArrayList<Statement>();
		for(StatementNode statementNode : programNode.getStatements()) {
			Statement statement = makeStatement(statementNode);				
			statements.add(statement);
		}
		
		return new Program(statements);
		
	}
	
	private static Statement makeStatement(StatementNode statementNode) {
		Statement statement = null;
		if(statementNode instanceof PrintStatementNode) {
			PrintStatementNode printStatementNode = (PrintStatementNode)statementNode;
			ExpressionNode expressionNode = printStatementNode.getExpression();
			Expression expression = makeExpression(expressionNode);
			statement = new PrintStatement(expression);
		} else if(statementNode instanceof SetVariableStatementNode) {
			SetVariableStatementNode setVariableStatementNode = (SetVariableStatementNode)statementNode;
			String name = setVariableStatementNode.getName();
			ExpressionNode expressionNode = setVariableStatementNode.getExpression();
			Expression expression = makeExpression(expressionNode);
			statement = new AssignmentExpressionStatement(name, expression);
		} else {
			throw new RuntimeException();
		}
		
		return statement;
	}
	
	private static Expression makeExpression(ExpressionNode expressionNode) {
		Expression expression = null;
		if(expressionNode instanceof LiteralExpressionNode) {
			LiteralExpressionNode literalExpressionNode = (LiteralExpressionNode)expressionNode;
			expression = new LiteralExpression(literalExpressionNode.getValue());
		} else if(expressionNode instanceof VariableReferenceNode) {
			VariableReferenceNode variableReferenceNode = (VariableReferenceNode)expressionNode;
			String name = variableReferenceNode.getName();
			expression = new VariableReferenceExpression(name);
		} else if(expressionNode instanceof ArithmExpressionNode) {
			ArithmExpressionNode arithmExpressionNode = (ArithmExpressionNode)expressionNode;
			Expression expressionA = makeExpression(arithmExpressionNode.getA());
			Expression expressionB = makeExpression(arithmExpressionNode.getB());
			expression = makeArithmExpression(expressionA, expressionB, arithmExpressionNode.getOperation());
		} else {
			throw new RuntimeException();
		}
		
		return expression;
	}
	
	private static Expression makeArithmExpression(Expression a, Expression b, Operation op) {
		if(op == Operation.Add) {
			return new AddExpression(a, b);
		} else if(op == Operation.Sub) {
			return new SubExpression(a, b);
		} else if(op == Operation.Mul) {
			return new MulExpression(a, b);
		} else if(op == Operation.Div) {
			return new DivExpression(a, b);
		} 
		
		throw new RuntimeException();			
	}
	
}