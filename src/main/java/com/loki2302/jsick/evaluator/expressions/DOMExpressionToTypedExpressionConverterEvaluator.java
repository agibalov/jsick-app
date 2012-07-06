package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.LexicalContext;
import com.loki2302.jsick.dom.expressions.DOMAddExpression;
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
import com.loki2302.jsick.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.Type;

public class DOMExpressionToTypedExpressionConverterEvaluator extends Evaluator<DOMExpression, TypedExpression> {

	private final LexicalContext lexicalContext;
	private final Evaluator<DOMIntConstExpression, TypedExpression> intConstExpressionEvaluator;
	private final Evaluator<DOMDoubleConstExpression, TypedExpression> doubleConstExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> addExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> subExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> mulExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> divExpressionEvaluator;
	private final Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> remExpressionEvaluator;

	public DOMExpressionToTypedExpressionConverterEvaluator(
			LexicalContext lexicalContext,
			Evaluator<DOMIntConstExpression, TypedExpression> intConstExpressionEvaluator,
			Evaluator<DOMDoubleConstExpression, TypedExpression> doubleConstExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> addExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> subExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> mulExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> divExpressionEvaluator,
			Evaluator<Tuple2<TypedExpression, TypedExpression>, TypedExpression> remExpressionEvaluator) {
		this.lexicalContext = lexicalContext;
		this.intConstExpressionEvaluator = intConstExpressionEvaluator;
		this.doubleConstExpressionEvaluator = doubleConstExpressionEvaluator;
		this.addExpressionEvaluator = addExpressionEvaluator;
		this.subExpressionEvaluator = subExpressionEvaluator;
		this.mulExpressionEvaluator = mulExpressionEvaluator;
		this.divExpressionEvaluator = divExpressionEvaluator;
		this.remExpressionEvaluator = remExpressionEvaluator;
	}

	@Override
	protected Context<TypedExpression> evaluateImpl(Context<DOMExpression> input) {
		DOMExpression domExpression = input.getValue();
		return domExpression.accept(new CompilingDOMExpressionVisitor(lexicalContext));
	}

	private class CompilingDOMExpressionVisitor implements DOMExpressionVisitor<Context<TypedExpression>> {
		private final LexicalContext lexicalContext;
		
		public CompilingDOMExpressionVisitor(LexicalContext lexicalContext) {
			this.lexicalContext = lexicalContext;
		}
		
		@Override
		public Context<TypedExpression> visitDOMIntConstExpression(DOMIntConstExpression expression) {
			return intConstExpressionEvaluator.evaluate(Context.<DOMIntConstExpression> ok(expression));
		}

		@Override
		public Context<TypedExpression> visitDOMDoubleConstExpression(DOMDoubleConstExpression expression) {
			return doubleConstExpressionEvaluator.evaluate(Context.<DOMDoubleConstExpression> ok(expression));
		}

		@Override
		public Context<TypedExpression> visitDOMAddExpression(DOMAddExpression expression) {
			return processBinaryExpression(expression, addExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitDOMSubExpression(DOMSubExpression expression) {
			return processBinaryExpression(expression, subExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitDOMMulExpression(DOMMulExpression expression) {
			return processBinaryExpression(expression, mulExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitDOMDivExpression(DOMDivExpression expression) {
			return processBinaryExpression(expression, divExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitDOMRemExpression(DOMRemExpression expression) {
			return processBinaryExpression(expression, remExpressionEvaluator);
		}

		@Override
		public Context<TypedExpression> visitDOMVariableReferenceExpression(DOMVariableReferenceExpression expression) {
			
			String variableName = expression.getVariableName();
			if(!lexicalContext.variableExists(variableName)) {
				System.out.println("123");
				return Context.<TypedExpression>fail(new BadContextError(null, null));
			}
			
			Type variableType = lexicalContext.getVariableType(variableName);
			
			return Context.<TypedExpression>ok(new VariableReferenceExpression(variableName, variableType));
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

			return evaluator.evaluate(Context.<Tuple2<TypedExpression, TypedExpression>> ok(
					new Tuple2<TypedExpression, TypedExpression>(
							leftContext.getValue(), 
							rightContext.getValue())));
		}
	}

}