package com.loki2302.jsick.parser;

public class CodePositionInfo {
	private final int line;
	private final int column;
	private final String lineString;
	
	public CodePositionInfo(int line, int column, String lineString) {
		this.line = line;
		this.column = column;
		this.lineString = lineString;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String getLineString() {
		return lineString;
	}
}