package com.loki2302.jsick;


import java.util.List;

import com.loki2302.jsick.compiler.ProgramCompilationResult;
import com.loki2302.jsick.compiler.ProgramCompiler;
import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.model.Program;
import com.loki2302.jsick.parser.ParserService;
import com.loki2302.jsick.parser.tree.ProgramNode;
import com.loki2302.jsick.vm.PrintStreamPrinter;
import com.loki2302.jsick.vm.VirtualMachine;
import com.loki2302.jsick.vm.VmProgram;
import com.loki2302.jsick.vm.instructions.Instruction;

public class App {
	
	public static void main(String[] args) {
		ProgramNode programNode = ParserService.parse(				
				"x=123;y=2*x+1;z=(x+y)/2.0;?x;?y;?z;?z-4;"
				);
				
		Program program = ModelBuilder.build(programNode);
		
		ProgramCompilationResult compilationResult = ProgramCompiler.compile(program);
		if(compilationResult.hasErrors()) {
			System.out.println("COMPILATION FAILED!");
			List<CompilationError> errors = compilationResult.getErrors();
			for(CompilationError error : errors) {
				System.out.println(error);
			}			
		} else {
			System.out.println("COMPILATION SUCCEEDED!");
			List<Instruction> instructions = compilationResult.getInstructions();
			VmProgram vmProgram = new VmProgram(instructions);
			vmProgram.dump();
			VirtualMachine vm = new VirtualMachine(new PrintStreamPrinter(System.out));
			vmProgram.execute(vm);
		}				
    }
    
}
