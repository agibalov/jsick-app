package com.loki2302.jsick.compiler.model.statements;

import com.loki2302.jsick.compiler.model.Type;
import com.loki2302.jsick.compiler.model.expressions.Expression;

public class VariableDefinitionStatement extends Statement {
	
	private final Type type;
	private final String name;
	private final Expression expression;
	
	public VariableDefinitionStatement(Type type, String name, Expression expression) {
		this(type, name, expression, null);
	}
	
	public VariableDefinitionStatement(Type type, String name, Expression expression, Object sourceContext) {
		super(sourceContext);
		this.type = type;
		this.name = name;
		this.expression = expression;
	}	
	
	public Type getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public Expression getExpression() {
		return expression;
	}

}
