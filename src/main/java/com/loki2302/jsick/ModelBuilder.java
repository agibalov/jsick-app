package com.loki2302.jsick;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.model.Program;
import com.loki2302.jsick.compiler.model.SimpleType;
import com.loki2302.jsick.compiler.model.Type;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.RemExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.compiler.model.statements.AssignmentStatement;
import com.loki2302.jsick.compiler.model.statements.PrintStatement;
import com.loki2302.jsick.compiler.model.statements.Statement;
import com.loki2302.jsick.compiler.model.statements.VariableDefinitionStatement;
import com.loki2302.jsick.parser.tree.AddExpressionNode;
import com.loki2302.jsick.parser.tree.DivExpressionNode;
import com.loki2302.jsick.parser.tree.DoubleLiteralExpressionNode;
import com.loki2302.jsick.parser.tree.ExpressionNode;
import com.loki2302.jsick.parser.tree.IntLiteralExpressionNode;
import com.loki2302.jsick.parser.tree.MulExpressionNode;
import com.loki2302.jsick.parser.tree.PrintStatementNode;
import com.loki2302.jsick.parser.tree.ProgramNode;
import com.loki2302.jsick.parser.tree.RemExpressionNode;
import com.loki2302.jsick.parser.tree.SetVariableStatementNode;
import com.loki2302.jsick.parser.tree.SimpleTypeNode;
import com.loki2302.jsick.parser.tree.StatementNode;
import com.loki2302.jsick.parser.tree.SubExpressionNode;
import com.loki2302.jsick.parser.tree.VariableDefinitionStatementNode;
import com.loki2302.jsick.parser.tree.VariableReferenceNode;

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
			statement = new PrintStatement(expression, statementNode);
		} else if(statementNode instanceof SetVariableStatementNode) {
			SetVariableStatementNode setVariableStatementNode = (SetVariableStatementNode)statementNode;
			String name = setVariableStatementNode.getName();
			ExpressionNode expressionNode = setVariableStatementNode.getExpression();
			Expression expression = makeExpression(expressionNode);
			statement = new AssignmentStatement(name, expression, statementNode);
		} else if(statementNode instanceof VariableDefinitionStatementNode) {
			VariableDefinitionStatementNode variableDefinitionStatementNode = (VariableDefinitionStatementNode)statementNode;
			Type type = new SimpleType(((SimpleTypeNode)variableDefinitionStatementNode.getType()).getTypeName(), statementNode);
			String variableName = variableDefinitionStatementNode.getName();
			ExpressionNode expressionNode = variableDefinitionStatementNode.getExpression();
			Expression expression = makeExpression(expressionNode);
			statement = new VariableDefinitionStatement(type, variableName, expression, statementNode);
		} else {
			throw new RuntimeException();
		}
		
		return statement;
	}
	
	private static Expression makeExpression(ExpressionNode expressionNode) {
		Expression expression = null;
		if(expressionNode instanceof IntLiteralExpressionNode) {
			IntLiteralExpressionNode literalExpressionNode = (IntLiteralExpressionNode)expressionNode;
			expression = new IntLiteralExpression(literalExpressionNode.getValue(), expressionNode);
		} else if(expressionNode instanceof DoubleLiteralExpressionNode) {
			DoubleLiteralExpressionNode literalExpressionNode = (DoubleLiteralExpressionNode)expressionNode;
			expression = new DoubleLiteralExpression(literalExpressionNode.getValue());
		} else if(expressionNode instanceof VariableReferenceNode) {
			VariableReferenceNode variableReferenceNode = (VariableReferenceNode)expressionNode;
			String name = variableReferenceNode.getName();
			expression = new VariableReferenceExpression(name, expressionNode);
		} else if(expressionNode instanceof AddExpressionNode) {
			AddExpressionNode arithmExpressionNode = (AddExpressionNode)expressionNode;
			Expression expressionA = makeExpression(arithmExpressionNode.getA());
			Expression expressionB = makeExpression(arithmExpressionNode.getB());
			expression = new AddExpression(expressionA, expressionB, expressionNode);
		} else if(expressionNode instanceof SubExpressionNode) {
			SubExpressionNode arithmExpressionNode = (SubExpressionNode)expressionNode;
			Expression expressionA = makeExpression(arithmExpressionNode.getA());
			Expression expressionB = makeExpression(arithmExpressionNode.getB());
			expression = new SubExpression(expressionA, expressionB, expressionNode);
		} else if(expressionNode instanceof MulExpressionNode) {
			MulExpressionNode arithmExpressionNode = (MulExpressionNode)expressionNode;
			Expression expressionA = makeExpression(arithmExpressionNode.getA());
			Expression expressionB = makeExpression(arithmExpressionNode.getB());
			expression = new MulExpression(expressionA, expressionB, expressionNode);
		} else if(expressionNode instanceof DivExpressionNode) {
			DivExpressionNode arithmExpressionNode = (DivExpressionNode)expressionNode;
			Expression expressionA = makeExpression(arithmExpressionNode.getA());
			Expression expressionB = makeExpression(arithmExpressionNode.getB());
			expression = new DivExpression(expressionA, expressionB, expressionNode);
		} else if(expressionNode instanceof RemExpressionNode) {
			RemExpressionNode arithmExpressionNode = (RemExpressionNode)expressionNode;
			Expression expressionA = makeExpression(arithmExpressionNode.getA());
			Expression expressionB = makeExpression(arithmExpressionNode.getB());
			expression = new RemExpression(expressionA, expressionB, expressionNode);
		} else {
			throw new RuntimeException();
		}
		
		return expression;
	}	
	
}