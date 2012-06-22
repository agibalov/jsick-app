package com.loki2302.jsick;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.model.ExecutionContext;
import com.loki2302.jsick.model.Program;
import com.loki2302.jsick.parser.ParserService;
import com.loki2302.jsick.parser.tree.ProgramNode;

public class AppTest {
	@Test
	public void sillyTest() {
		ProgramNode programNode = ParserService.parse(				
				"x=123;y=2*x+1;z=(x+y)/2;?z-4;"				
				);
				
		Program program = ModelBuilder.build(programNode);
		ExecutionContext context = new ExecutionContext();
		program.execute(context);
		
		assertEquals(185, context.getVariableValue("z"));
	}
}
