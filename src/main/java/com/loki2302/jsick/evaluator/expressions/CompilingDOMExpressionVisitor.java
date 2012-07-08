package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMAddExpression;
import com.loki2302.jsick.dom.expressions.DOMAssignmentExpression;
import com.loki2302.jsick.dom.expressions.DOMDivExpression;
import com.loki2302.jsick.dom.expressions.DOMDoubleConstExpression;
import com.loki2302.jsick.dom.expressions.DOMExpressionVisitor;
import com.loki2302.jsick.dom.expressions.DOMIntConstExpression;
import com.loki2302.jsick.dom.expressions.DOMMulExpression;
import com.loki2302.jsick.dom.expressions.DOMRemExpression;
import com.loki2302.jsick.dom.expressions.DOMSubExpression;
import com.loki2302.jsick.dom.expressions.DOMVariableReferenceExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.expressions.Expression;

public class CompilingDOMExpressionVisitor implements DOMExpressionVisitor<Context<Expression>> {
	
	private final Evaluator<DOMIntConstExpression, Expression> intConstExpressionEvaluator;
	private final Evaluator<DOMDoubleConstExpression, Expression> doubleConstExpressionEvaluator;
	private final Evaluator<DOMAddExpression, Expression> addExpressionEvaluator;
	private final Evaluator<DOMSubExpression, Expression> subExpressionEvaluator;
	private final Evaluator<DOMMulExpression, Expression> mulExpressionEvaluator;
	private final Evaluator<DOMDivExpression, Expression> divExpressionEvaluator;
	private final Evaluator<DOMRemExpression, Expression> remExpressionEvaluator;
	private final Evaluator<DOMVariableReferenceExpression, Expression> variableReferenceExpressionEvaluator;
	private final Evaluator<DOMAssignmentExpression, Expression> variableAssignmentExpressionEvaluator;
	
	public CompilingDOMExpressionVisitor(
			Evaluator<DOMIntConstExpression, Expression> intConstExpressionEvaluator,
			Evaluator<DOMDoubleConstExpression, Expression> doubleConstExpressionEvaluator,
			Evaluator<DOMAddExpression, Expression> addExpressionEvaluator,
			Evaluator<DOMSubExpression, Expression> subExpressionEvaluator,
			Evaluator<DOMMulExpression, Expression> mulExpressionEvaluator,
			Evaluator<DOMDivExpression, Expression> divExpressionEvaluator,
			Evaluator<DOMRemExpression, Expression> remExpressionEvaluator,
			Evaluator<DOMVariableReferenceExpression, Expression> variableReferenceExpressionEvaluator,
			Evaluator<DOMAssignmentExpression, Expression> variableAssignmentExpressionEvaluator) {
		this.intConstExpressionEvaluator = intConstExpressionEvaluator;
		this.doubleConstExpressionEvaluator = doubleConstExpressionEvaluator;
		this.addExpressionEvaluator = addExpressionEvaluator;
		this.subExpressionEvaluator = subExpressionEvaluator;
		this.mulExpressionEvaluator = mulExpressionEvaluator;
		this.divExpressionEvaluator = divExpressionEvaluator;
		this.remExpressionEvaluator = remExpressionEvaluator;
		this.variableReferenceExpressionEvaluator = variableReferenceExpressionEvaluator;
		this.variableAssignmentExpressionEvaluator = variableAssignmentExpressionEvaluator;
	}
	
	@Override
	public Context<Expression> visitIntConstExpression(DOMIntConstExpression expression) {
		return intConstExpressionEvaluator.evaluate(expression);
	}

	@Override
	public Context<Expression> visitDoubleConstExpression(DOMDoubleConstExpression expression) {
		return doubleConstExpressionEvaluator.evaluate(expression);
	}

	@Override
	public Context<Expression> visitAddExpression(DOMAddExpression expression) {
		return addExpressionEvaluator.evaluate(expression);
	}

	@Override
	public Context<Expression> visitSubExpression(DOMSubExpression expression) {
		return subExpressionEvaluator.evaluate(expression);
	}

	@Override
	public Context<Expression> visitMulExpression(DOMMulExpression expression) {
		return mulExpressionEvaluator.evaluate(expression);
	}

	@Override
	public Context<Expression> visitDivExpression(DOMDivExpression expression) {
		return divExpressionEvaluator.evaluate(expression);
	}

	@Override
	public Context<Expression> visitRemExpression(DOMRemExpression expression) {
		return remExpressionEvaluator.evaluate(expression);
	}

	@Override
	public Context<Expression> visitVariableReferenceExpression(DOMVariableReferenceExpression expression) {
		return variableReferenceExpressionEvaluator.evaluate(expression);
	}		
	
	@Override
	public Context<Expression> visitAssignmentExpression(DOMAssignmentExpression expression) {
		return variableAssignmentExpressionEvaluator.evaluate(expression);
	}
		
}