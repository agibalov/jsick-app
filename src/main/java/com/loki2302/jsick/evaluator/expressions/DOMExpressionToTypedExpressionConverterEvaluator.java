package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMAddExpression;
import com.loki2302.jsick.dom.expressions.DOMVariableAssignmentExpression;
import com.loki2302.jsick.dom.expressions.DOMBinaryExpression;
import com.loki2302.jsick.dom.expressions.DOMDivExpression;
import com.loki2302.jsick.dom.expressions.DOMDoubleConstExpression;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.dom.expressions.DOMExpressionVisitor;
import com.loki2302.jsick.dom.expressions.DOMIntConstExpression;
import com.loki2302.jsick.dom.expressions.DOMMulExpression;
import com.loki2302.jsick.dom.expressions.DOMRemExpression;
import com.loki2302.jsick.dom.expressions.DOMSubExpression;
import com.loki2302.jsick.dom.expressions.DOMVariableReferenceExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.Tuple2;
import com.loki2302.jsick.evaluator.errors.BadContextError;
import com.loki2302.jsick.expressions.TypedExpression;

public class DOMExpressionToTypedExpressionConverterEvaluator extends Evaluator<DOMExpression, TypedExpression> {

	private final Evaluator<DOMIntConstExpression, TypedExpression> intConstExpressionEvaluator;
	private final Evaluator<DOMDoubleConstExpression, TypedExpression> doubleConstExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> addExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> subExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> mulExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> divExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> remExpressionEvaluator;
	private final Evaluator<DOMVariableReferenceExpression, TypedExpression> variableReferenceExpressionEvaluator;
	private final Evaluator<DOMVariableAssignmentExpression, TypedExpression> variableAssignmentExpressionEvaluator;

	public DOMExpressionToTypedExpressionConverterEvaluator(
			Evaluator<DOMIntConstExpression, TypedExpression> intConstExpressionEvaluator,
			Evaluator<DOMDoubleConstExpression, TypedExpression> doubleConstExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> addExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> subExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> mulExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> divExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> remExpressionEvaluator,
			Evaluator<DOMVariableReferenceExpression, TypedExpression> variableReferenceExpressionEvaluator,
			Evaluator<DOMVariableAssignmentExpression, TypedExpression> variableAssignmentExpressionEvaluator) {
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
	public Context<TypedExpression> evaluate(DOMExpression input) {
		return input.accept(new CompilingDOMExpressionVisitor());
	}

	private class CompilingDOMExpressionVisitor implements DOMExpressionVisitor<Context<TypedExpression>> {
		@Override
		public Context<TypedExpression> visitIntConstExpression(DOMIntConstExpression expression) {
			return intConstExpressionEvaluator.evaluate(expression);
		}

		@Override
		public Context<TypedExpression> visitDoubleConstExpression(DOMDoubleConstExpression expression) {
			return doubleConstExpressionEvaluator.evaluate(expression);
		}

		@Override
		public Context<TypedExpression> visitAddExpression(DOMAddExpression expression) {
			return processBinaryExpression(expression, addExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitSubExpression(DOMSubExpression expression) {
			return processBinaryExpression(expression, subExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitMulExpression(DOMMulExpression expression) {
			return processBinaryExpression(expression, mulExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitDivExpression(DOMDivExpression expression) {
			return processBinaryExpression(expression, divExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitRemExpression(DOMRemExpression expression) {
			return processBinaryExpression(expression, remExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitVariableReferenceExpression(DOMVariableReferenceExpression expression) {
			return variableReferenceExpressionEvaluator.evaluate(expression);
		}		
		
		@Override
		public Context<TypedExpression> visitVariableAssignmentExpression(DOMVariableAssignmentExpression expression) {			
			return variableAssignmentExpressionEvaluator.evaluate(expression);
		}
		
		private Context<TypedExpression> processBinaryExpression(
				DOMBinaryExpression expression,
				Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> evaluator) {

			Context<TypedExpression> leftContext = expression.getLeft().accept(this);
			if (!leftContext.isOk()) {
				return fail(new BadContextError(evaluator, null));
			}

			Context<TypedExpression> rightContext = expression.getRight().accept(this);
			if (!rightContext.isOk()) {
				return fail(new BadContextError(evaluator, null));
			}

			return evaluator.evaluate(
					new Tuple2<TypedExpression, TypedExpression>(
							leftContext.getValue(), 
							rightContext.getValue()));
		}		
	}
}