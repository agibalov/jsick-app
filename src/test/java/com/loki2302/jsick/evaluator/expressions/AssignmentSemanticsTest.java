package com.loki2302.jsick.evaluator.expressions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.expressions.DoubleConstExpression;
import com.loki2302.jsick.expressions.IntConstExpression;
import com.loki2302.jsick.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.Instance;
import com.loki2302.jsick.types.Types;

public class AssignmentSemanticsTest {
	
	@Test
	public void semanticAllowsAssigningIntToIntVariable() {
		Types types = new Types();
		Instance intInstance = types.IntType.makeInstance("x");
		
		VariableReferenceExpression variableReferenceExpression = new VariableReferenceExpression(intInstance);
		IntConstExpression intConstExpression = new IntConstExpression(types.IntType, 1);
		
		AssignmentSemanticsEvaluator evaluator = new AssignmentSemanticsEvaluator();
		Context<TwoExpressionsAndType> resultContext = evaluator.evaluate(
				new TwoExpressions(variableReferenceExpression, intConstExpression));
		
		assertTrue(resultContext.isOk());
		assertEquals(variableReferenceExpression, resultContext.getValue().getLeft());
		assertEquals(intConstExpression, resultContext.getValue().getRight());
		assertEquals(types.IntType, resultContext.getValue().getType());
	}
	
	@Test
	public void semanticDoesntAllowAssigningDoubleToIntVariable() {
		Types types = new Types();
		Instance intInstance = types.IntType.makeInstance("x");
		
		VariableReferenceExpression variableReferenceExpression = new VariableReferenceExpression(intInstance);
		DoubleConstExpression doubleConstExpression = new DoubleConstExpression(types.DoubleType, 1);
		
		AssignmentSemanticsEvaluator evaluator = new AssignmentSemanticsEvaluator();
		Context<TwoExpressionsAndType> resultContext = evaluator.evaluate(
				new TwoExpressions(variableReferenceExpression, doubleConstExpression));
		
		assertFalse(resultContext.isOk());
	}

}
