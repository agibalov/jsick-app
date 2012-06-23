package com.loki2302.jsick.compiler;

import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.types.JType;

public class LexicalContext {
	private int currentPosition;
	private final Map<String, Integer> positionsByNames = new HashMap<String, Integer>();
	private final Map<String, JType> variableTypesByNames = new HashMap<String, JType>();
		
	public void addVariable(String name, JType type) {
		positionsByNames.put(name, currentPosition);
		variableTypesByNames.put(name, type);
		++currentPosition;
	}
	
	public JType getVariableType(String name) {
		return variableTypesByNames.get(name);
	}
	
	public int getVariablePosition(String name) {
		return positionsByNames.get(name);
	}
	
	public boolean hasVariable(String name) {
		return positionsByNames.containsKey(name);
	}
	
}