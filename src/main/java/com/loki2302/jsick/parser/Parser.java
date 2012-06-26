package com.loki2302.jsick.parser;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.support.Var;

import com.loki2302.jsick.parser.tree.ArithmExpressionNode;
import com.loki2302.jsick.parser.tree.DoubleLiteralExpressionNode;
import com.loki2302.jsick.parser.tree.ExpressionNode;
import com.loki2302.jsick.parser.tree.IntLiteralExpressionNode;
import com.loki2302.jsick.parser.tree.Node;
import com.loki2302.jsick.parser.tree.PrintStatementNode;
import com.loki2302.jsick.parser.tree.ProgramNode;
import com.loki2302.jsick.parser.tree.SetVariableStatementNode;
import com.loki2302.jsick.parser.tree.SimpleTypeNode;
import com.loki2302.jsick.parser.tree.StatementNode;
import com.loki2302.jsick.parser.tree.VariableDefinitionStatementNode;
import com.loki2302.jsick.parser.tree.VariableReferenceNode;
import com.loki2302.jsick.parser.tree.TypeNode;

public class Parser extends BaseParser<Node> {
	
	public Rule program() {
		ProgramNode programNode = new ProgramNode(null);
		return Sequence(				
				OneOrMore(
						statement(), 
						ACTION(programNode.addStatement((StatementNode)pop()))),
				EOI,
				push(programNode));
	}
	
	Rule statement() {
		return Sequence(
				optGap(),
				FirstOf(
					variableDefinitionStatement(),
					variableAssignmentStatement(),
					variablePrintStatement()),
				optGap(),
				";",
				optGap());
	}
	
	Rule variableDefinitionStatement() {
		Var<String> name = new Var<String>();
		return Sequence(
				type(),
				gap(),
				variableName(),
				name.set(match()),
				optGap(),
				"=",
				optGap(),
				expression(),
				push(new VariableDefinitionStatementNode((TypeNode)pop(1), name.get(), (ExpressionNode)pop(), getContext().getMatchRange())));
	}
	
	Rule type() {
		return simpleType();
	}
	
	Rule simpleType() {
		return Sequence(
				FirstOf(
						"int", 
						"double"),
				push(new SimpleTypeNode(match(), getContext().getMatchRange())));
	}
	
	Rule variableAssignmentStatement() {
		Var<String> name = new Var<String>();
		return Sequence(
				variableName(),
				name.set(match()),
				optGap(),
				"=",
				optGap(),
				expression(),
				push(new SetVariableStatementNode(name.get(), (ExpressionNode)pop(), getContext().getMatchRange()))
				);
	}
	
	Rule variablePrintStatement() {
		return Sequence(
				"?",
				optGap(),
				expression(),				
				push(new PrintStatementNode((ExpressionNode)pop(), getContext().getMatchRange()))
				);		
	}
		
	Rule factorExpression() {
		return Sequence(
				optGap(),
				FirstOf(
					literalExpression(),
					variableReferenceExpression(),
					parensExpression()),
				optGap());
	}
	
	Rule parensExpression() {
		return Sequence("(", expression(), ")");
	}
	
	Rule literalExpression() {
		return FirstOf(
				doubleLiteralExpression(), 
				intLiteralExpression());
	}
	
	Rule intLiteralExpression() {
		return Sequence(
				OneOrMore(CharRange('0', '9')),
				push(new IntLiteralExpressionNode(match(), getContext().getMatchRange())));
	}
	
	Rule doubleLiteralExpression() {
		return Sequence(
				Sequence(
					OneOrMore(CharRange('0', '9')),
					".",
					OneOrMore(CharRange('0', '9'))),
				push(new DoubleLiteralExpressionNode(match(), getContext().getMatchRange())));
	}
	
	Rule variableName() {
		return OneOrMore(CharRange('a', 'z'));
	}
	
	Rule variableReferenceExpression() {
		return Sequence(
				variableName(),
				push(new VariableReferenceNode(match(), getContext().getMatchRange()))); 
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
							ArithmExpressionNode.operationFromChar(op.get()), 
							getContext().getMatchRange())))							
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
							ArithmExpressionNode.operationFromChar(op.get()), 
							getContext().getMatchRange())))
				);
	}
	
	Rule gap() {
		return OneOrMore(
				gapSpec()
				);
	}
	
	Rule optGap() {
		return ZeroOrMore(
				gapSpec()
				);
	}
	
	Rule gapSpec() {
		return FirstOf(
				gapChar(),
				comment());
	}
	
	Rule gapChar() {
		return FirstOf(
				' ', 
				'\t', 
				'\r', 
				'\n'
				);
	}
	
	Rule comment() {
		return FirstOf(
				multilineComment(),
				singleLineComment());
	}
	
	Rule multilineComment() {
		return Sequence(
				"/*",
				ZeroOrMore(TestNot("*/"), ANY),
				"*/");
	}
	
	Rule singleLineComment() {
		return Sequence(
				"//",
				ZeroOrMore(TestNot(AnyOf("\r\n")), ANY),
				FirstOf(
						"\r\n", 
						"\n\r", 
						'\n', 
						'\r',
						EOI));
	}
	
}