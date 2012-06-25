package com.loki2302.jsick.compiler.model.statements;

import com.loki2302.jsick.compiler.model.expressions.Expression;

public class AssignmentStatement extends Statement {
	
	private final String name;
	private final Expression expression;
	
	public AssignmentStatement(String name, Expression expression) {
		this(name, expression, null);
	}
	
	public AssignmentStatement(String name, Expression expression, Object sourceContext) {
		super(sourceContext);
		this.name = name;
		this.expression = expression;
	}	
	
	public String getName() {
		return name;
	}
	
	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visitAssignmentStatement(this);
	}

}
