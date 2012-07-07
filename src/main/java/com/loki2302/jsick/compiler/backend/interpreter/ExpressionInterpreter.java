package com.loki2302.jsick.compiler.backend.interpreter;

import java.util.Map;

import com.loki2302.jsick.expressions.AddExpression;
import com.loki2302.jsick.expressions.CastExpression;
import com.loki2302.jsick.expressions.DivExpression;
import com.loki2302.jsick.expressions.DoubleConstExpression;
import com.loki2302.jsick.expressions.IntConstExpression;
import com.loki2302.jsick.expressions.MulExpression;
import com.loki2302.jsick.expressions.RemExpression;
import com.loki2302.jsick.expressions.SetVariableValueExpression;
import com.loki2302.jsick.expressions.SubExpression;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.expressions.ExpressionVisitor;
import com.loki2302.jsick.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Types;

public class ExpressionInterpreter {
	
	private final Types types; 
	private final Map<Instance, Object> variables;
	
	public ExpressionInterpreter(Types types, Map<Instance, Object> variables) {
		this.types = types;
		this.variables = variables;
	}
	
	public Object interprete(Expression expression) {
		return expression.accept(new ExpressionVisitor<Object>() {
			@Override
			public Object visitIntConstExpression(IntConstExpression expression) {
				return expression.getValue();
			}

			@Override
			public Object visitDoubleConstExpression(DoubleConstExpression expression) {
				return expression.getValue();
			}

			@Override
			public Object visitCastExpression(CastExpression expression) {
				if(expression.getType().equals(types.DoubleType) && expression.getExpression().getType().equals(types.IntType)) {
					return (double)(int)(Integer)expression.getExpression().accept(this);					
				}
				
				throw new RuntimeException();
			}

			@Override
			public Object visitAddExpression(AddExpression expression) {
				if(expression.getType().equals(types.IntType)) {
					return (Integer)expression.getLeft().accept(this) + (Integer)expression.getRight().accept(this); 
				} else if(expression.getType().equals(types.DoubleType)) {
					return (Double)expression.getLeft().accept(this) + (Double)expression.getRight().accept(this);
				}
				
				throw new RuntimeException();
			}

			@Override
			public Object visitSubExpression(SubExpression expression) {
				if(expression.getType().equals(types.IntType)) {
					return (Integer)expression.getLeft().accept(this) - (Integer)expression.getRight().accept(this); 
				} else if(expression.getType().equals(types.DoubleType)) {
					return (Double)expression.getLeft().accept(this) - (Double)expression.getRight().accept(this);
				}
				
				throw new RuntimeException();
			}

			@Override
			public Object visitMulExpression(MulExpression expression) {
				if(expression.getType().equals(types.IntType)) {
					return (Integer)expression.getLeft().accept(this) * (Integer)expression.getRight().accept(this); 
				} else if(expression.getType().equals(types.DoubleType)) {
					return (Double)expression.getLeft().accept(this) * (Double)expression.getRight().accept(this);
				}
				
				throw new RuntimeException();
			}

			@Override
			public Object visitDivExpression(DivExpression expression) {
				if(expression.getType().equals(types.IntType)) {
					return (Integer)expression.getLeft().accept(this) / (Integer)expression.getRight().accept(this); 
				} else if(expression.getType().equals(types.DoubleType)) {
					return (Double)expression.getLeft().accept(this) / (Double)expression.getRight().accept(this);
				}
				
				throw new RuntimeException();
			}

			@Override
			public Object visitRemExpression(RemExpression expression) {
				if(expression.getType().equals(types.IntType)) {
					return (Integer)expression.getLeft().accept(this) % (Integer)expression.getRight().accept(this); 
				}
				
				throw new RuntimeException();
			}

			@Override
			public Object visitVariableReferenceExpression(VariableReferenceExpression expression) {
				return variables.get(expression.getInstance());
			}

			@Override
			public Object visitSetVariableValueExpression(SetVariableValueExpression expression) {
				variables.put(expression.getInstance(), expression.getExpression().accept(this));
				return variables.get(expression.getInstance());
			}				
		});
	}
}