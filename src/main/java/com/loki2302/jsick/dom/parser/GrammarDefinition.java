package com.loki2302.jsick.dom.parser;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.support.IndexRange;
import org.parboiled.support.Var;

import com.loki2302.jsick.dom.DOMNode;
import com.loki2302.jsick.dom.DOMProgram;
import com.loki2302.jsick.dom.expressions.DOMBinaryExpression;
import com.loki2302.jsick.dom.expressions.DOMDoubleConstExpression;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMIntConstExpression;
import com.loki2302.jsick.dom.expressions.DOMAssignmentExpression;
import com.loki2302.jsick.dom.expressions.DOMVariableReferenceExpression;
import com.loki2302.jsick.dom.statements.DOMExpressionStatement;
import com.loki2302.jsick.dom.statements.DOMPrintStatement;
import com.loki2302.jsick.dom.statements.DOMStatement;
import com.loki2302.jsick.dom.statements.DOMVariableDefinitionStatement;

public class GrammarDefinition extends BaseParser<DOMNode> {
	
	Rule program() {
		DOMProgram program = new DOMProgram(null);
		return Sequence(				
				OneOrMore(
						statement(), 
						ACTION(program.addStatement((DOMStatement)pop()))),
				EOI,
				push(program));
	}
	
	Rule statement() {
		return Sequence(
				optGap(),
				FirstOf(
						variableDefinitionStatement(),
						expressionStatement(),
						printStatement()),
				optGap(),
				";",
				optGap());
	}
	
	Rule expressionStatement() {
		return Sequence(
				expression(),
				push(new DOMExpressionStatement((DOMExpression)pop(), getContext().getMatchRange())));
	}
	
	Rule printStatement() {
		return Sequence(
				"?",
				optGap(),
				expression(),				
				push(new DOMPrintStatement((DOMExpression)pop(), getContext().getMatchRange())));
	}
	
	Rule variableDefinitionStatement() {
		Var<String> typeName = new Var<String>();
		Var<String> variableName = new Var<String>();
		return Sequence(
				type(),
				typeName.set(match()),
				gap(),
				variableName(),
				variableName.set(match()),
				optGap(),
				"=",
				optGap(),
				expression(),
				push(new DOMVariableDefinitionStatement(typeName.get(), variableName.get(), (DOMExpression)pop(), getContext().getMatchRange())));
	}
		
	Rule factorExpression() {
		return Sequence(
				optGap(),
				FirstOf(
					variableReferenceExpression(),
					literalExpression(),
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
				push(new DOMIntConstExpression(Integer.parseInt(match()), getContext().getMatchRange())));
	}
	
	Rule doubleLiteralExpression() {
		return Sequence(
				Sequence(
					OneOrMore(CharRange('0', '9')),
					".",
					OneOrMore(CharRange('0', '9'))),
				push(new DOMDoubleConstExpression(Double.parseDouble(match()), getContext().getMatchRange())));
	}
	
	Rule variableName() {
		return OneOrMore(CharRange('a', 'z'));
	}
	
	Rule variableReferenceExpression() {
		return Sequence(
				variableName(),
				push(new DOMVariableReferenceExpression(match(), getContext().getMatchRange()))); 
	}
	
	Rule expression() {
		return FirstOf(				
				assignmentExpression(),
				additiveExpression(),
				parensExpression());
	}
	
	Rule assignmentExpression() {
		Var<IndexRange> opMatchRange = new Var<IndexRange>();
		return Sequence(
				variableReferenceExpression(),
				optGap(),
				"=",
				opMatchRange.set(getContext().getMatchRange()),
				optGap(),
				expression(),
				push(new DOMAssignmentExpression(
						(DOMVariableReferenceExpression)pop(1), 
						(DOMExpression)pop(), 
						opMatchRange.get())));
	}
		
	Rule multiplicativeExpression() {
		Var<Character> op = new Var<Character>();
		Var<IndexRange> opMatchRange = new Var<IndexRange>();
		return Sequence(
				factorExpression(),
				ZeroOrMore(
					FirstOf("*", "/", "%"),
					op.set(matchedChar()),
					opMatchRange.set(getContext().getMatchRange()),
					factorExpression(),
					push(DOMBinaryExpression.expressionFromChar(
							op.get(),
							(DOMExpression)pop(1), 
							(DOMExpression)pop(), 
							opMatchRange.get())))							
				);		
	}
	
	Rule additiveExpression() {
		Var<Character> op = new Var<Character>();
		Var<IndexRange> opMatchRange = new Var<IndexRange>(); 
		return Sequence(
				multiplicativeExpression(),
				ZeroOrMore(
					FirstOf("+", "-"),
					op.set(matchedChar()),
					opMatchRange.set(getContext().getMatchRange()),
					multiplicativeExpression(),
					push(DOMBinaryExpression.expressionFromChar(
							op.get(),
							(DOMExpression)pop(1), 
							(DOMExpression)pop(), 
							opMatchRange.get())))
				);
	}
	
	Rule type() {
		return simpleType();
	}
	
	Rule simpleType() {
		return FirstOf(
				"int", 
				"double");
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