package com.loki2302.jsick;

import com.loki2302.jsick.compiler.VmCompiler;
import com.loki2302.jsick.model.Program;
import com.loki2302.jsick.parser.ParserService;
import com.loki2302.jsick.parser.tree.ProgramNode;
import com.loki2302.jsick.vm.PrintStreamPrinter;
import com.loki2302.jsick.vm.VirtualMachine;
import com.loki2302.jsick.vm.VmProgram;

public class App {
	
	public static void main(String[] args) {
		ProgramNode programNode = ParserService.parse(				
				"x=123;y=2*x+1;z=(x+y)/2;?x;?y;?z;?z-4;"				
				);
				
		Program program = ModelBuilder.build(programNode);
		
		VmProgram vmProgram = VmCompiler.compile(program);
		vmProgram.dump();
		
		VirtualMachine vm = new VirtualMachine(new PrintStreamPrinter(System.out));
		vmProgram.execute(vm);
    }
    
}
