package com.loki2302.jsick;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.loki2302.jsick.compiler.VmCompiler;
import com.loki2302.jsick.model.Program;
import com.loki2302.jsick.parser.ParserService;
import com.loki2302.jsick.parser.tree.ProgramNode;
import com.loki2302.jsick.vm.Printer;
import com.loki2302.jsick.vm.VirtualMachine;
import com.loki2302.jsick.vm.VmProgram;

public class AppTest {
	@Test
	public void sillyTest() {
		ProgramNode programNode = ParserService.parse(				
				"x=123;y=2*x+1;z=(x+y)/2;?x;?y;?z;?z-4;"				
				);
				
		Program program = ModelBuilder.build(programNode);
		
		Mockery context = new Mockery();
		final Printer printer = context.mock(Printer.class);
		context.checking(new Expectations() {{
			oneOf(printer).printInt(123);
			oneOf(printer).printInt(247);
			oneOf(printer).printInt(185);
			oneOf(printer).printInt(181);
		}});
		
		VmProgram vmProgram = VmCompiler.compile(program);
		
		VirtualMachine vm = new VirtualMachine(printer);
		vmProgram.execute(vm);
		
		context.assertIsSatisfied();
	}
}
