package com.loki2302.jsick.parser;

import org.parboiled.buffers.InputBuffer;
import org.parboiled.support.IndexRange;
import org.parboiled.support.Position;

import com.loki2302.jsick.parser.tree.ProgramNode;

public class ParseResult {
	
	private ProgramNode programNode;	
	private boolean hasErrors;
	private InputBuffer inputBuffer;
			
	public ProgramNode getProgramNode() {
		return programNode;
	}
	
	public boolean hasErrors() {
		return hasErrors;
	}
	
	public CodePositionInfo getCodePositionInfo(IndexRange indexRange) {
		Position startPosition = inputBuffer.getPosition(indexRange.start);
		String lineString = inputBuffer.extractLine(startPosition.line);		
		return new CodePositionInfo(startPosition.line, startPosition.column, lineString);
	}
	
	public static ParseResult bad(InputBuffer inputBuffer) {
		ParseResult result = new ParseResult();
		result.hasErrors = true;
		result.inputBuffer = inputBuffer;
		return result;
	}
	
	public static ParseResult good(InputBuffer inputBuffer, ProgramNode programNode) {
		ParseResult result = new ParseResult();
		result.hasErrors = false;
		result.inputBuffer = inputBuffer;
		result.programNode = programNode;
		return result;
	}
}