package com.loki2302.jsick;

import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.types.Type;

public class LexicalContext {
	private int location = 0;
	private final Map<String, LexicalContext.VariableInfo> variables = new HashMap<String, LexicalContext.VariableInfo>();
	
	public void addVariable(String name, Type type) {
		if(variables.containsKey(name)) {
			throw new RuntimeException();
		}
		
		variables.put(name, new VariableInfo(type, location++));
	}
	
	public boolean variableExists(String name) {
		return variables.containsKey(name);
	}
	
	public Type getVariableType(String name) {
		return variables.get(name).getVariableType();
	}
	
	public int getVariableLocation(String name) {
		return variables.get(name).getVariableLocation();
	}
	
	public int getNumberOfVariables() {
		return variables.size();
	}
	
	private static class VariableInfo {
		private final Type variableType;
		private final int location;
		
		public VariableInfo(Type variableType, int location) {
			this.variableType = variableType;
			this.location = location;
		}
					
		public Type getVariableType() {
			return variableType;
		}
		
		public int getVariableLocation() {
			return location;
		}
	}
}