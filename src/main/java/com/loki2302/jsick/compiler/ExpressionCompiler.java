package com.loki2302.jsick.compiler;

import com.loki2302.jsick.LexicalContext;
import com.loki2302.jsick.dom.expressions.DOMExpression;
import com.loki2302.jsick.evaluator.Context;
import com.loki2302.jsick.evaluator.expressions.AddSubMulDivOperationTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.AddTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.BinaryOperationEvaluator;
import com.loki2302.jsick.evaluator.expressions.DOMExpressionToTypedExpressionConverterEvaluator;
import com.loki2302.jsick.evaluator.expressions.DivTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.DoubleConstExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.IntConstExpressionEvaluator;
import com.loki2302.jsick.evaluator.expressions.MulTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.RemOperationTypeEvaluator;
import com.loki2302.jsick.evaluator.expressions.RemTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.evaluator.expressions.SubTypedExpressionBuilderEvaluator;
import com.loki2302.jsick.expressions.TypedExpression;
import com.loki2302.jsick.types.Types;

public class ExpressionCompiler {
	private final DOMExpressionToTypedExpressionConverterEvaluator evaluator;
	
	public ExpressionCompiler(Types types, LexicalContext lexicalContext) {
		evaluator = makeDOMExpressionToTypedExpressionConverterEvaluator(types, lexicalContext); 
	}
	
	public Context<TypedExpression> compile(DOMExpression domExpression) {
		return evaluator.evaluate(Context.<DOMExpression>ok(domExpression));
	}
	
	private static DOMExpressionToTypedExpressionConverterEvaluator makeDOMExpressionToTypedExpressionConverterEvaluator(
			Types types, 
			LexicalContext lexicalContext) {    	
    	IntConstExpressionEvaluator intConstExpressionEvaluator = new IntConstExpressionEvaluator();
    	DoubleConstExpressionEvaluator doubleConstExpressionEvaluator = new DoubleConstExpressionEvaluator();
    	
    	AddSubMulDivOperationTypeEvaluator addSubMulDivOperationTypeEvaluator = new AddSubMulDivOperationTypeEvaluator(types);
		
    	AddTypedExpressionBuilderEvaluator addExpressionBuilderEvaluator = new AddTypedExpressionBuilderEvaluator();
		BinaryOperationEvaluator addExpressionEvaluator = new BinaryOperationEvaluator(addSubMulDivOperationTypeEvaluator, addExpressionBuilderEvaluator);
		
		SubTypedExpressionBuilderEvaluator subExpressionBuilderEvaluator = new SubTypedExpressionBuilderEvaluator();
		BinaryOperationEvaluator subExpressionEvaluator = new BinaryOperationEvaluator(addSubMulDivOperationTypeEvaluator, subExpressionBuilderEvaluator);
		
		MulTypedExpressionBuilderEvaluator mulExpressionBuilderEvaluator = new MulTypedExpressionBuilderEvaluator();
		BinaryOperationEvaluator mulExpressionEvaluator = new BinaryOperationEvaluator(addSubMulDivOperationTypeEvaluator, mulExpressionBuilderEvaluator);
		
		DivTypedExpressionBuilderEvaluator divExpressionBuilderEvaluator = new DivTypedExpressionBuilderEvaluator();
		BinaryOperationEvaluator divExpressionEvaluator = new BinaryOperationEvaluator(addSubMulDivOperationTypeEvaluator, divExpressionBuilderEvaluator);
		
		RemOperationTypeEvaluator remTypeResolver = new RemOperationTypeEvaluator(types);
		RemTypedExpressionBuilderEvaluator remExpressionBuilderEvaluator = new RemTypedExpressionBuilderEvaluator();
		BinaryOperationEvaluator remExpressionEvaluator = new BinaryOperationEvaluator(remTypeResolver, remExpressionBuilderEvaluator);
    	    	    	
    	DOMExpressionToTypedExpressionConverterEvaluator compiler = new DOMExpressionToTypedExpressionConverterEvaluator(
    			lexicalContext,
    			intConstExpressionEvaluator,
    			doubleConstExpressionEvaluator,
    			addExpressionEvaluator,
    			subExpressionEvaluator,
    			mulExpressionEvaluator,
    			divExpressionEvaluator,
    			remExpressionEvaluator);
    	
    	return compiler;
	}
}