package com.loki2302.jsick.compiler;

import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.types.Instance;

public class LexicalContext {
	
	private final Map<String, Instance> variables = new HashMap<String, Instance>();
	
	public void addVariable(String name, Instance instance) {
		if(variables.containsKey(name)) {
			throw new RuntimeException();
		}
		
		variables.put(name, instance);
	}
	
	public boolean variableExists(String name) {
		return variables.containsKey(name);
	}
	
	public Instance getVariable(String name) {
		return variables.get(name);
	}
	
}