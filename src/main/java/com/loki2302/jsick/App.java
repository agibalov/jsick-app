package com.loki2302.jsick;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.loki2302.jsick.nodes.Node;
import com.loki2302.jsick.nodes.ProgramNode;

public class App {
	
	public static void main(String[] args) {				
		Parser parser = Parboiled.createParser(Parser.class);
		ParsingResult<Node> result = new RecoveringParseRunner<Node>(parser.program()).run(
				
				"x=123;y=2*x+1;z=(x+y)/2;?z-4;"
				
				);
		
		System.out.printf("Matched: %b\n", result.matched);
		
		ProgramNode n = (ProgramNode)result.resultValue;
		n.execute();
    }
    
}
