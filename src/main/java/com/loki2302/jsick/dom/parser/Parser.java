package com.loki2302.jsick.dom.parser;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.loki2302.jsick.dom.DOMNode;
import com.loki2302.jsick.dom.DOMProgram;

public class Parser {
	
	public ParseResult parse(String sourceCode) {		
		GrammarDefinition grammar = Parboiled.createParser(GrammarDefinition.class);
		ParsingResult<DOMNode> result = new RecoveringParseRunner<DOMNode>(grammar.program()).run(sourceCode);
		
		if(result.hasErrors()) {			
			return ParseResult.fail(result.inputBuffer); 
		}
		
		return ParseResult.ok(result.inputBuffer, (DOMProgram)result.resultValue);		
	}

}
