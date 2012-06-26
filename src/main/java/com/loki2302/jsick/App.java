package com.loki2302.jsick;

import java.util.List;

import com.loki2302.jsick.compiler.ProgramCompilationResult;
import com.loki2302.jsick.compiler.ProgramCompiler;
import com.loki2302.jsick.compiler.SourceContextAware;
import com.loki2302.jsick.compiler.errors.CompilationError;
import com.loki2302.jsick.compiler.model.Program;
import com.loki2302.jsick.parser.CodePositionInfo;
import com.loki2302.jsick.parser.ParseResult;
import com.loki2302.jsick.parser.ParserService;
import com.loki2302.jsick.parser.tree.Node;
import com.loki2302.jsick.parser.tree.ProgramNode;
import com.loki2302.jsick.vm.PrintStreamPrinter;
import com.loki2302.jsick.vm.VirtualMachine;
import com.loki2302.jsick.vm.VmProgram;
import com.loki2302.jsick.vm.instructions.Instruction;

public class App {
	
	public static void main(String[] args) {
		
		String code =
				"int x = 123; /* assign 123 to x */\n" + 
				"int y = 2 * x + 1;\n" +
				"double z = (x + y) / 2.0; // dividing int by double causes z to be double\n" + 
				"? x; // print x\n" + 
				"? y;\n" +
				"? z;\n" +
				"? z - 4; // print (z - 4) \n" + 
				"z = 1;\n" + 
				"? z;\n" +
				"x = 3.1;\n";
		
		ParseResult parseResult = ParserService.parse(code);
		if(parseResult.hasErrors()) {
			System.out.println("There are syntax errors");
			return;
		}
		
		ProgramNode programNode = parseResult.getProgramNode();
				
		Program program = ModelBuilder.build(programNode);
		
		ProgramCompilationResult compilationResult = ProgramCompiler.compile(program);
		if(compilationResult.hasErrors()) {
			System.out.println("COMPILATION FAILED!");
			List<CompilationError> errors = compilationResult.getErrors();
			for(CompilationError error : errors) {
				String extraInfo = "";
				Object sourceContext = error.getSourceContext();
				if(sourceContext != null && sourceContext instanceof SourceContextAware) {
					SourceContextAware sourceContextAware = (SourceContextAware)sourceContext;
					sourceContext = sourceContextAware.getSourceContext();
					if(sourceContext != null && sourceContext instanceof Node) {
						Node node = (Node)sourceContext;
						CodePositionInfo codePositionInfo = parseResult.getCodePositionInfo(node.getMatchRange());
						extraInfo = String.format("ERROR at Line %d, column %d: '%s'", 
								codePositionInfo.getLine(),
								codePositionInfo.getColumn(),
								codePositionInfo.getLineString());
					}
				}
				
				System.out.println(extraInfo);
				System.out.printf("  %s\n\n", error);
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
