package com.loki2302.jsick;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {
	private final Map<String, Integer> variables = new HashMap<String, Integer>();
	
	public void setVariable(String name, int value) {
		variables.put(name, value);
	}
	
	public int getVariableValue(String name) {
		return variables.get(name);
	}
}