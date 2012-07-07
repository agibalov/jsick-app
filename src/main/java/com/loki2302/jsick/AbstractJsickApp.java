package com.loki2302.jsick;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import com.loki2302.jsick.compiler.ProgramCompiler;
import com.loki2302.jsick.dom.parser.ParseResult;
import com.loki2302.jsick.dom.parser.Parser;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.types.Types;


public abstract class AbstractJsickApp {
	
	protected static void compileAndPassToBackEnd(String[] args, BackEnd backEnd) throws IOException {
		if(args.length != 1) {
			System.out.println("Specify a source file name without extension");
			return;
		}
		
		String sourceName = args[0];
		String sourceFileName = String.format("%s.jsick", sourceName);
		String code = readFile(sourceFileName); 
		
		Parser parser = new Parser();
		ParseResult parseResult = parser.parse(code);
		if(!parseResult.isOk()) {
			System.out.println("SYNTAX ERRORS");
			return;
		}
		
		Types types = new Types();
		ProgramCompiler programCompiler = ProgramCompiler.makeDefaultCompiler(types);
		Context<Program> programContext = programCompiler.compile(parseResult.getProgram());
		if(!programContext.isOk()) {
			System.out.println("SEMANTIC ERRORS");
			return;
		}
		
		Program program = programContext.getValue();
		backEnd.process(program, types, sourceName);
	}

	private static String readFile(String path) throws IOException {		
		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}	
}
