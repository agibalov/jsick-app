package com.loki2302.jsick.evaluator.expressions;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.dom.expressions.DOMAddExpression;
import com.loki2302.jsick.dom.expressions.DOMAssignmentExpression;
import com.loki2302.jsick.dom.expressions.DOMBinaryExpression;
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
import com.loki2302.jsick.evaluator.errors.AbstractError;
import com.loki2302.jsick.evaluator.errors.CompositeError;
import com.loki2302.jsick.expressions.Expression;

public class CompilingDOMExpressionVisitor implements DOMExpressionVisitor<Context<Expression>> {
	
	private final Evaluator<DOMIntConstExpression, Expression> intConstExpressionEvaluator;
	private final Evaluator<DOMDoubleConstExpression, Expression> doubleConstExpressionEvaluator;
	private final Evaluator<TwoExpressions, Expression> addExpressionEvaluator;
	private final Evaluator<TwoExpressions, Expression> subExpressionEvaluator;
	private final Evaluator<TwoExpressions, Expression> mulExpressionEvaluator;
	private final Evaluator<TwoExpressions, Expression> divExpressionEvaluator;
	private final Evaluator<TwoExpressions, Expression> remExpressionEvaluator;
	private final Evaluator<DOMVariableReferenceExpression, Expression> variableReferenceExpressionEvaluator;
	private final Evaluator<TwoExpressions, Expression> variableAssignmentExpressionEvaluator;
	
	public CompilingDOMExpressionVisitor(
			Evaluator<DOMIntConstExpression, Expression> intConstExpressionEvaluator,
			Evaluator<DOMDoubleConstExpression, Expression> doubleConstExpressionEvaluator,
			Evaluator<TwoExpressions, Expression> addExpressionEvaluator,
			Evaluator<TwoExpressions, Expression> subExpressionEvaluator,
			Evaluator<TwoExpressions, Expression> mulExpressionEvaluator,
			Evaluator<TwoExpressions, Expression> divExpressionEvaluator,
			Evaluator<TwoExpressions, Expression> remExpressionEvaluator,
			Evaluator<DOMVariableReferenceExpression, Expression> variableReferenceExpressionEvaluator,
			Evaluator<TwoExpressions, Expression> variableAssignmentExpressionEvaluator) {
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
		return processBinaryExpression(expression, addExpressionEvaluator);
	}

	@Override
	public Context<Expression> visitSubExpression(DOMSubExpression expression) {
		return processBinaryExpression(expression, subExpressionEvaluator);
	}

	@Override
	public Context<Expression> visitMulExpression(DOMMulExpression expression) {
		return processBinaryExpression(expression, mulExpressionEvaluator);
	}

	@Override
	public Context<Expression> visitDivExpression(DOMDivExpression expression) {
		return processBinaryExpression(expression, divExpressionEvaluator);
	}

	@Override
	public Context<Expression> visitRemExpression(DOMRemExpression expression) {
		return processBinaryExpression(expression, remExpressionEvaluator);
	}

	@Override
	public Context<Expression> visitVariableReferenceExpression(DOMVariableReferenceExpression expression) {
		return variableReferenceExpressionEvaluator.evaluate(expression);
	}		
	
	@Override
	public Context<Expression> visitAssignmentExpression(DOMAssignmentExpression expression) {
		return processBinaryExpression(expression, variableAssignmentExpressionEvaluator);
	}
	
	private Context<Expression> processBinaryExpression(
			DOMBinaryExpression expression,
			Evaluator<TwoExpressions, Expression> evaluator) {

		List<AbstractError> errors = new ArrayList<AbstractError>();
		Context<Expression> leftContext = expression.getLeft().accept(this);
		if (!leftContext.isOk()) {
			errors.add(leftContext.getError());
		}

		Context<Expression> rightContext = expression.getRight().accept(this);
		if (!rightContext.isOk()) {
			errors.add(rightContext.getError());
		}
		
		if(!errors.isEmpty()) {
			return Context.<Expression>fail(new CompositeError(evaluator, rightContext, errors));
		}

		return evaluator.evaluate(
				new TwoExpressions(
						leftContext.getValue(),
						rightContext.getValue()));
	}		
}