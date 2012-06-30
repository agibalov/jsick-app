package com.loki2302.jsick.compiler;

import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.types.Type;

public class LexicalContext {
	private int currentPosition;
	private final Map<String, Integer> positionsByNames = new HashMap<String, Integer>();
	private final Map<String, Type> variableTypesByNames = new HashMap<String, Type>();
		
	public void addVariable(String name, Type type) {
		positionsByNames.put(name, currentPosition);
		variableTypesByNames.put(name, type);
		++currentPosition;
	}
	
	public Type getVariableType(String name) {
		return variableTypesByNames.get(name);
	}
	
	public int getVariablePosition(String name) {
		return positionsByNames.get(name);
	}
	
	public boolean hasVariable(String name) {
		return positionsByNames.containsKey(name);
	}
	
	public void setVariableType(String name, Type type) {
		variableTypesByNames.put(name, type);
	}
	
}