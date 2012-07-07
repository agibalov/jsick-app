package com.loki2302.jsick;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.loki2302.jsick.compiler.backend.jvm.JVMCodeGenerator;
import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.types.Types;

public class JVMCompilerApp extends AbstractJsickApp {
	
	public static void main(String[] args) throws IOException {
		compileAndPassToBackEnd(args, new BackEnd() {
			@Override
			public void process(Program program, Types types, String sourceName) throws IOException {
				JVMCodeGenerator jvmCodeGenerator = new JVMCodeGenerator(types);
		    	byte[] byteCode = jvmCodeGenerator.generateCode(program, sourceName);
		    	
		    	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(String.format("%s.class", sourceName)));
		    	bos.write(byteCode);
		    	bos.flush();
		    	bos.close();
			}			
		});
	}

}
