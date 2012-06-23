package com.loki2302.jsick.compiler;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.expressiondetails.BinaryExpressionCompilationDetails;
import com.loki2302.jsick.compiler.expressiondetails.BinaryExpressionCompilationDetails.CommonnessKind;
import com.loki2302.jsick.compiler.expressiondetails.ExpressionCompilationDetails;
import com.loki2302.jsick.compiler.expressiondetails.ExpressionCompiler;
import com.loki2302.jsick.compiler.model.AssignmentExpressionStatement;
import com.loki2302.jsick.compiler.model.PrintStatement;
import com.loki2302.jsick.compiler.model.Program;
import com.loki2302.jsick.compiler.model.Statement;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.BinaryExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.compiler.model.expressions.DoubleLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.IntLiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.types.DoubleType;
import com.loki2302.jsick.types.IntType;
import com.loki2302.jsick.types.JType;
import com.loki2302.jsick.vm.VmProgram;
import com.loki2302.jsick.vm.instructions.DoubleAddInstruction;
import com.loki2302.jsick.vm.instructions.DoubleDivInstruction;
import com.loki2302.jsick.vm.instructions.DoubleMulInstruction;
import com.loki2302.jsick.vm.instructions.DoubleSubInstruction;
import com.loki2302.jsick.vm.instructions.DoubleToIntInstruction;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntAddInstruction;
import com.loki2302.jsick.vm.instructions.IntDivInstruction;
import com.loki2302.jsick.vm.instructions.IntMulInstruction;
import com.loki2302.jsick.vm.instructions.IntSubInstruction;
import com.loki2302.jsick.vm.instructions.IntToDoubleInstruction;
import com.loki2302.jsick.vm.instructions.LoadLocalInstruction;
import com.loki2302.jsick.vm.instructions.PrintDoubleInstruction;
import com.loki2302.jsick.vm.instructions.PrintIntInstruction;
import com.loki2302.jsick.vm.instructions.PushDoubleInstruction;
import com.loki2302.jsick.vm.instructions.PushIntInstruction;
import com.loki2302.jsick.vm.instructions.SaveLocalInstruction;

public class VmCompiler {
	
	private final static IntType intType = new IntType();		
	private final static DoubleType doubleType = new DoubleType();
	
	public static VmProgram compile(Program program) {
		List<Instruction> instructions = new ArrayList<Instruction>();
	
		LexicalContext lexicalContext = new LexicalContext();		
		
		ExpressionCompiler expressionCompiler = new ExpressionCompiler(lexicalContext, intType, doubleType);
		
		for(Statement statement : program.getStatements()) {
			if(statement instanceof AssignmentExpressionStatement) {
				AssignmentExpressionStatement assignment = (AssignmentExpressionStatement)statement;
				String name = assignment.getName();
				
				Expression expression = assignment.getExpression();
				ExpressionCompilationDetails expressionDetails = expressionCompiler.analyze(expression);
				
				if(!lexicalContext.hasVariable(name)) {
					JType expressionType = expressionDetails.getType();
					lexicalContext.addVariable(name, expressionType);
					System.out.printf("added %s of type %s\n", name, expressionType.getName());
				}
				int position = lexicalContext.getVariablePosition(name);
				
				List<Instruction> expressionCode = compileExpression(expression, lexicalContext, expressionCompiler);
				instructions.addAll(expressionCode);
				instructions.add(new SaveLocalInstruction(position));
			} else if(statement instanceof PrintStatement) {
				PrintStatement print = (PrintStatement)statement;
				
				Expression expression = print.getExpression();
				List<Instruction> expressionCode = compileExpression(expression, lexicalContext, expressionCompiler);
				instructions.addAll(expressionCode);
				
				ExpressionCompilationDetails d = expressionCompiler.analyze(expression);
				if(d.getType().equals(intType)) {
					instructions.add(new PrintIntInstruction());
				} else if(d.getType().equals(doubleType)) {
					instructions.add(new PrintDoubleInstruction());
				} else {
					// TODO: i don't know how to print the type other than int or double
					throw new RuntimeException();
				}
			} else {
				// TODO: we have this statement in parser, but don't yet know how to handle it
				throw new RuntimeException();
			}
		}
		
		return new VmProgram(instructions);		
	}
	
	private static List<Instruction> compileExpression(Expression expression, LexicalContext lexicalContext, ExpressionCompiler expressionCompiler) {
		List<Instruction> instructions = new ArrayList<Instruction>();		
				
		if(expression instanceof IntLiteralExpression) {
			IntLiteralExpression literalExpression = (IntLiteralExpression)expression;
			instructions.add(new PushIntInstruction(Integer.parseInt(literalExpression.getValue())));
		} else if(expression instanceof DoubleLiteralExpression) {
			DoubleLiteralExpression literalExpression = (DoubleLiteralExpression)expression;
			instructions.add(new PushDoubleInstruction(Double.parseDouble(literalExpression.getValue())));
		} else if(expression instanceof BinaryExpression) {
			BinaryExpression addExpression = (BinaryExpression)expression;
			List<Instruction> leftInstructions = compileExpression(addExpression.getLeft(), lexicalContext, expressionCompiler);
			List<Instruction> rightInstructions = compileExpression(addExpression.getRight(), lexicalContext, expressionCompiler);			
			
			BinaryExpressionCompilationDetails d = (BinaryExpressionCompilationDetails)expressionCompiler.analyze(addExpression);
			if(d.getCommonnessKind() == CommonnessKind.None) {
				// TODO: there's no common type for left and right expressions so that this binary operation could be possible
				throw new RuntimeException();
			} else if(d.getCommonnessKind() == CommonnessKind.Directly) {
				instructions.addAll(leftInstructions);
				instructions.addAll(rightInstructions);
			} else if(d.getCommonnessKind() == CommonnessKind.CastLeft) {
				instructions.addAll(leftInstructions);
				if(d.getType().equals(intType)) {
					instructions.add(new DoubleToIntInstruction());
				} else if(d.getType().equals(doubleType)) {
					instructions.add(new IntToDoubleInstruction());
				} else {
					// TODO: don't know how to cast left expression to required type
					throw new RuntimeException();
				}				
				instructions.addAll(rightInstructions);
			} else if(d.getCommonnessKind() == CommonnessKind.CastRight) {
				instructions.addAll(leftInstructions);
				instructions.addAll(rightInstructions);
				if(d.getType().equals(intType)) {
					instructions.add(new DoubleToIntInstruction());
				} else if(d.getType().equals(doubleType)) {
					instructions.add(new IntToDoubleInstruction());
				} else {
					// TODO: don't know how to cast right expression to required type
					throw new RuntimeException();
				}
			} else {
				// TODO: compilation advisor says we need to do something we don't understand
				throw new RuntimeException();
			}
			
			if(expression instanceof AddExpression) {
				if(d.getType().equals(intType)) {
					instructions.add(new IntAddInstruction());
				} else if(d.getType().equals(doubleType)) {
					instructions.add(new DoubleAddInstruction());
				} else {
					// TODO: operation add is not defined for the type 
					throw new RuntimeException();
				}				
			} else if(expression instanceof SubExpression) {
				if(d.getType().equals(intType)) {
					instructions.add(new IntSubInstruction());
				} else if(d.getType().equals(doubleType)) {
					instructions.add(new DoubleSubInstruction());
				} else {
					// TODO: operation sub is not defined for the type
					throw new RuntimeException();
				}
			} else if(expression instanceof MulExpression) {
				if(d.getType().equals(intType)) {
					instructions.add(new IntMulInstruction());
				} else if(d.getType().equals(doubleType)) {
					instructions.add(new DoubleMulInstruction());
				} else {
					// TODO: operation mul is not defined for the type
					throw new RuntimeException();
				}
			} else if(expression instanceof DivExpression) {
				if(d.getType().equals(intType)) {
					instructions.add(new IntDivInstruction());
				} else if(d.getType().equals(doubleType)) {
					instructions.add(new DoubleDivInstruction());
				} else {
					// TODO: operation div is not defined for the type
					throw new RuntimeException();
				}
			} else {
				// TODO: unknown operation
				throw new RuntimeException();
			}		
		} else if(expression instanceof VariableReferenceExpression) {
			VariableReferenceExpression variableReferenceExpression = (VariableReferenceExpression)expression;
			String name = variableReferenceExpression.getName();			
			instructions.add(new LoadLocalInstruction(lexicalContext.getVariablePosition(name)));
		} else {		
			// TODO: unknown expression kind
			throw new RuntimeException();
		}
		
		return instructions;
	}
	
}
