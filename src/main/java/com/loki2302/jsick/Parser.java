package com.loki2302.jsick;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.support.Var;

import com.loki2302.jsick.nodes.ArithmExpressionNode;
import com.loki2302.jsick.nodes.ExpressionNode;
import com.loki2302.jsick.nodes.LiteralExpressionNode;
import com.loki2302.jsick.nodes.Node;
import com.loki2302.jsick.nodes.PrintVariableStatementNode;
import com.loki2302.jsick.nodes.ProgramNode;
import com.loki2302.jsick.nodes.SetVariableStatementNode;
import com.loki2302.jsick.nodes.StatementNode;
import com.loki2302.jsick.nodes.VariableReferenceNode;

public class Parser extends BaseParser<Node> {
	
	Rule program() {
		ProgramNode programNode = new ProgramNode();
		return Sequence(				
				OneOrMore(
						statement(), 
						ACTION(programNode.addStatement((StatementNode)pop()))),
				EOI,
				push(programNode));
	}
	
	Rule statement() {
		return Sequence(
				FirstOf(
					variableAssignmentStatement(),
					variablePrintStatement()),
				";");
	}
	
	Rule variableAssignmentStatement() {
		Var<String> name = new Var<String>();
		return Sequence(
				variableName(),
				name.set(match()),
				"=",
				expression(),
				push(new SetVariableStatementNode(name.get(), (ExpressionNode)pop()))
				);
	}
	
	Rule variablePrintStatement() {
		return Sequence(
				"?",
				expression(),
				push(new PrintVariableStatementNode((ExpressionNode)pop()))
				);		
	}
		
	Rule factorExpression() {
		return FirstOf(
				literalExpression(),
				variableReferenceExpression(),
				parensExpression());
	}
	
	Rule parensExpression() {
		return Sequence("(", expression(), ")");
	}
	
	Rule literalExpression() {
		return Sequence(
				OneOrMore(CharRange('0', '9')),
				push(new LiteralExpressionNode(Integer.parseInt(match()))));
	}
	
	Rule variableName() {
		return OneOrMore(CharRange('a', 'z'));
	}
	
	Rule variableReferenceExpression() {
		return Sequence(
				variableName(),
				push(new VariableReferenceNode(match()))); 
	}
	
	Rule termExpression() {
		Var<Character> op = new Var<Character>();
		return Sequence(
				factorExpression(),
				ZeroOrMore(
					FirstOf("*", "/"),
					op.set(matchedChar()),
					factorExpression(),
					push(new ArithmExpressionNode(
							(ExpressionNode)pop(1), 
							(ExpressionNode)pop(), 
							ArithmExpressionNode.operationFromChar(op.get()))))							
				);		
	}
	
	Rule expression() {
		Var<Character> op = new Var<Character>();
		return Sequence(
				termExpression(),
				ZeroOrMore(
					FirstOf("+", "-"),
					op.set(matchedChar()),
					termExpression(),
					push(new ArithmExpressionNode(
							(ExpressionNode)pop(1), 
							(ExpressionNode)pop(), 
							ArithmExpressionNode.operationFromChar(op.get()))))
				);
	}
	
}