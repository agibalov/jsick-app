package com.loki2302.jsick.evaluator.expressions;

import com.loki2302.jsick.dom.expressions.DOMAssignmentExpression;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.Evaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.AssignmentSemanticsEvaluator;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressions;
import com.loki2302.jsick.evaluator.expressions.semantics.TwoExpressionsAndType;
import com.loki2302.jsick.expressions.Expression;
import com.loki2302.jsick.expressions.LvalueExpression;

public class AssignmentExpressionEvaluator extends AbstractBinaryExpressionEvaluator<DOMAssignmentExpression> {
	
	private final AssignmentSemanticsEvaluator assignmentSemanticsEvaluator;
	
	public AssignmentExpressionEvaluator(
			Evaluator<DOMExpression, Expression> genericExpressionEvaluator,
			AssignmentSemanticsEvaluator assignmentSemanticsEvaluator) {
		super(genericExpressionEvaluator);
		this.assignmentSemanticsEvaluator = assignmentSemanticsEvaluator;
	}

	@Override
	protected Context<Expression> processExpressions(DOMExpression sourceDOMExpression, Expression leftExpression, Expression rightExpression) {
		Context<TwoExpressionsAndType> resultContext = assignmentSemanticsEvaluator.evaluate(
				new TwoExpressions(leftExpression, rightExpression));
		if(!resultContext.isOk()) {
			return fail(resultContext.getError());
		}
		
		return ok(((LvalueExpression)resultContext.getValue().getLeft()).asSetter(sourceDOMExpression, resultContext.getValue().getRight()));
	}

}
