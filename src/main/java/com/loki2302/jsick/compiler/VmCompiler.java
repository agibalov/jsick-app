package com.loki2302.jsick.compiler;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.jsick.compiler.model.AssignmentExpressionStatement;
import com.loki2302.jsick.compiler.model.PrintStatement;
import com.loki2302.jsick.compiler.model.Program;
import com.loki2302.jsick.compiler.model.Statement;
import com.loki2302.jsick.compiler.model.expressions.AddExpression;
import com.loki2302.jsick.compiler.model.expressions.DivExpression;
import com.loki2302.jsick.compiler.model.expressions.Expression;
import com.loki2302.jsick.compiler.model.expressions.LiteralExpression;
import com.loki2302.jsick.compiler.model.expressions.MulExpression;
import com.loki2302.jsick.compiler.model.expressions.SubExpression;
import com.loki2302.jsick.compiler.model.expressions.VariableReferenceExpression;
import com.loki2302.jsick.vm.VmProgram;
import com.loki2302.jsick.vm.instructions.Instruction;
import com.loki2302.jsick.vm.instructions.IntAddInstruction;
import com.loki2302.jsick.vm.instructions.IntDivInstruction;
import com.loki2302.jsick.vm.instructions.IntMulInstruction;
import com.loki2302.jsick.vm.instructions.IntSubInstruction;
import com.loki2302.jsick.vm.instructions.LoadLocalInstruction;
import com.loki2302.jsick.vm.instructions.PrintIntInstruction;
import com.loki2302.jsick.vm.instructions.PushIntInstruction;
import com.loki2302.jsick.vm.instructions.SaveLocalInstruction;

public class VmCompiler {
	
	public static VmProgram compile(Program program) {
		List<Instruction> instructions = new ArrayList<Instruction>();
	
		LexicalContext lexicalContext = new LexicalContext(); 
		
		for(Statement statement : program.getStatements()) {
			if(statement instanceof AssignmentExpressionStatement) {
				AssignmentExpressionStatement assignment = (AssignmentExpressionStatement)statement;
				String name = assignment.getName();
				
				if(!lexicalContext.hasVariable(name)) {
					lexicalContext.addVariable(name);
				}
				int position = lexicalContext.getVariablePosition(name);
				
				Expression expression = assignment.getExpression();
				List<Instruction> expressionCode = compileExpression(expression, lexicalContext);
				instructions.addAll(expressionCode);
				instructions.add(new SaveLocalInstruction(position));
			} else if(statement instanceof PrintStatement) {
				PrintStatement print = (PrintStatement)statement;
				
				Expression expression = print.getExpression();
				List<Instruction> expressionCode = compileExpression(expression, lexicalContext);
				instructions.addAll(expressionCode);
				instructions.add(new PrintIntInstruction());
			} else {
				throw new RuntimeException();
			}
		}
		
		return new VmProgram(instructions);		
	}
	
	private static List<Instruction> compileExpression(Expression expression, LexicalContext lexicalContext) {
		List<Instruction> instructions = new ArrayList<Instruction>();		
				
		if(expression instanceof LiteralExpression) {
			LiteralExpression literalExpression = (LiteralExpression)expression;
			instructions.add(new PushIntInstruction(Integer.parseInt(literalExpression.getValue())));
		} else if(expression instanceof AddExpression) {
			AddExpression addExpression = (AddExpression)expression;
			List<Instruction> leftInstructions = compileExpression(addExpression.getLeft(), lexicalContext);
			List<Instruction> rightInstructions = compileExpression(addExpression.getRight(), lexicalContext);
			instructions.addAll(leftInstructions);
			instructions.addAll(rightInstructions);
			instructions.add(new IntAddInstruction());
		} else if(expression instanceof SubExpression) {
			SubExpression subExpression = (SubExpression)expression;
			List<Instruction> leftInstructions = compileExpression(subExpression.getLeft(), lexicalContext);
			List<Instruction> rightInstructions = compileExpression(subExpression.getRight(), lexicalContext);
			instructions.addAll(leftInstructions);
			instructions.addAll(rightInstructions);
			instructions.add(new IntSubInstruction());
		} else if(expression instanceof MulExpression) {
			MulExpression mulExpression = (MulExpression)expression;
			List<Instruction> leftInstructions = compileExpression(mulExpression.getLeft(), lexicalContext);
			List<Instruction> rightInstructions = compileExpression(mulExpression.getRight(), lexicalContext);
			instructions.addAll(leftInstructions);
			instructions.addAll(rightInstructions);
			instructions.add(new IntMulInstruction());
		} else if(expression instanceof DivExpression) {
			DivExpression divExpression = (DivExpression)expression;
			List<Instruction> leftInstructions = compileExpression(divExpression.getLeft(), lexicalContext);
			List<Instruction> rightInstructions = compileExpression(divExpression.getRight(), lexicalContext);
			instructions.addAll(leftInstructions);
			instructions.addAll(rightInstructions);
			instructions.add(new IntDivInstruction());
		} else if(expression instanceof VariableReferenceExpression) {
			VariableReferenceExpression variableReferenceExpression = (VariableReferenceExpression)expression;
			String name = variableReferenceExpression.getName();			
			instructions.add(new LoadLocalInstruction(lexicalContext.getVariablePosition(name)));
		} else {		
			throw new RuntimeException();
		}
		
		return instructions;
	}
	
}
