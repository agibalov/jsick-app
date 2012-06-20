package com.loki2302.jsick.nodes;

import com.loki2302.jsick.ExecutionContext;

public abstract class StatementNode extends Node {
	
	abstract void execute(ExecutionContext context);
	
}
