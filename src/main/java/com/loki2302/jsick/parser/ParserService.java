package com.loki2302.jsick.parser;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.loki2302.jsick.parser.tree.Node;
import com.loki2302.jsick.parser.tree.ProgramNode;

public class ParserService {
	
	public static ParseResult parse(String code) {
		Parser parser = Parboiled.createParser(Parser.class);
		ParsingResult<Node> result = new RecoveringParseRunner<Node>(parser.program()).run(code);
		
		if(result.hasErrors()) {			
			return ParseResult.bad(result.inputBuffer); 
		}
		
		return ParseResult.good(result.inputBuffer, (ProgramNode)result.resultValue);
	}

}
