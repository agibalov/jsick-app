package com.loki2302.jsick.dom.parser;

import org.parboiled.buffers.InputBuffer;
import org.parboiled.support.IndexRange;
import org.parboiled.support.Position;

import com.loki2302.jsick.dom.DOMProgram;

public class ParseResult {
	private DOMProgram program;	
	private boolean ok;
	private InputBuffer inputBuffer;
	
	public DOMProgram getProgram() {
		return program;
	}
	
	public boolean isOk() {
		return ok;
	}
	
	public CodePositionInfo getCodePositionInfo(IndexRange indexRange) {
		Position startPosition = inputBuffer.getPosition(indexRange.start);
		String lineString = inputBuffer.extractLine(startPosition.line);		
		return new CodePositionInfo(startPosition.line, startPosition.column, lineString);
	}
	
	public static ParseResult fail(InputBuffer inputBuffer) {
		ParseResult result = new ParseResult();
		result.ok = false;
		result.inputBuffer = inputBuffer;
		return result;
	}
	
	public static ParseResult ok(InputBuffer inputBuffer, DOMProgram program) {
		ParseResult result = new ParseResult();
		result.ok = true;
		result.inputBuffer = inputBuffer;
		result.program = program;
		return result;
	}		
}