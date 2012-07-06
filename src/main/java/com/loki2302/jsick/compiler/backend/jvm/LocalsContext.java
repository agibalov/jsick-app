package com.loki2302.jsick.compiler.backend.jvm;

import java.util.HashMap;
import java.util.Map;

import com.loki2302.jsick.types.Instance;

public class LocalsContext {
	private int freeIndex;
	private final Map<Instance, Integer> locals = new HashMap<Instance, Integer>();
	
	public void addLocal(Instance instance) {
		if(locals.containsKey(instance)) {
			throw new RuntimeException();
		}
		
		locals.put(instance, freeIndex++);
	}
	
	public int getLocalIndex(Instance instance) {
		return locals.get(instance);
	}
	
	public int getLocalsCount() {
		return locals.size();
	}
}