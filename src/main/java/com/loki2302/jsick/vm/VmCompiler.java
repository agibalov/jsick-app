package com.loki2302.jsick.vm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loki2302.jsick.model.AssignmentExpressionStatement;
import com.loki2302.jsick.model.PrintStatement;
import com.loki2302.jsick.model.Program;
import com.loki2302.jsick.model.Statement;
import com.loki2302.jsick.model.expressions.AddExpression;
import com.loki2302.jsick.model.expressions.DivExpression;
import com.loki2302.jsick.model.expressions.Expression;
import com.loki2302.jsick.model.expressions.LiteralExpression;
import com.loki2302.jsick.model.expressions.MulExpression;
import com.loki2302.jsick.model.expressions.SubExpression;
import com.loki2302.jsick.model.expressions.VariableReferenceExpression;
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
	
		int currentPosition = 0;
		Map<String, Integer> positionsByNames = new HashMap<String, Integer>(); 
		
		for(Statement statement : program.getStatements()) {
			if(statement instanceof AssignmentExpressionStatement) {
				AssignmentExpressionStatement assignment = (AssignmentExpressionStatement)statement;
				String name = assignment.getName();
				
				if(!positionsByNames.containsKey(name)) {
					positionsByNames.put(name, currentPosition++);
				}
				int position = positionsByNames.get(name);
				
				Expression expression = assignment.getExpression();
				List<Instruction> expressionCode = compileExpression(expression, positionsByNames);
				instructions.addAll(expressionCode);
				instructions.add(new SaveLocalInstruction(position));
			} else if(statement instanceof PrintStatement) {
				PrintStatement print = (PrintStatement)statement;
				
				Expression expression = print.getExpression();
				List<Instruction> expressionCode = compileExpression(expression, positionsByNames);
				instructions.addAll(expressionCode);
				instructions.add(new PrintIntInstruction());
			} else {
				throw new RuntimeException();
			}
		}
		
		return new VmProgram(instructions);		
	}
	
	private static List<Instruction> compileExpression(Expression expression, Map<String, Integer> positionsByNames) {
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		if(expression instanceof LiteralExpression) {
			LiteralExpression literalExpression = (LiteralExpression)expression;
			instructions.add(new PushIntInstruction(literalExpression.getValue()));
		} else if(expression instanceof AddExpression) {
			AddExpression addExpression = (AddExpression)expression;
			List<Instruction> leftInstructions = compileExpression(addExpression.getLeft(), positionsByNames);
			List<Instruction> rightInstructions = compileExpression(addExpression.getRight(), positionsByNames);
			instructions.addAll(leftInstructions);
			instructions.addAll(rightInstructions);
			instructions.add(new IntAddInstruction());
		} else if(expression instanceof SubExpression) {
			SubExpression subExpression = (SubExpression)expression;
			List<Instruction> leftInstructions = compileExpression(subExpression.getLeft(), positionsByNames);
			List<Instruction> rightInstructions = compileExpression(subExpression.getRight(), positionsByNames);
			instructions.addAll(leftInstructions);
			instructions.addAll(rightInstructions);
			instructions.add(new IntSubInstruction());
		} else if(expression instanceof MulExpression) {
			MulExpression mulExpression = (MulExpression)expression;
			List<Instruction> leftInstructions = compileExpression(mulExpression.getLeft(), positionsByNames);
			List<Instruction> rightInstructions = compileExpression(mulExpression.getRight(), positionsByNames);
			instructions.addAll(leftInstructions);
			instructions.addAll(rightInstructions);
			instructions.add(new IntMulInstruction());
		} else if(expression instanceof DivExpression) {
			DivExpression divExpression = (DivExpression)expression;
			List<Instruction> leftInstructions = compileExpression(divExpression.getLeft(), positionsByNames);
			List<Instruction> rightInstructions = compileExpression(divExpression.getRight(), positionsByNames);
			instructions.addAll(leftInstructions);
			instructions.addAll(rightInstructions);
			instructions.add(new IntDivInstruction());
		} else if(expression instanceof VariableReferenceExpression) {
			VariableReferenceExpression variableReferenceExpression = (VariableReferenceExpression)expression;
			String name = variableReferenceExpression.getName();			
			instructions.add(new LoadLocalInstruction(positionsByNames.get(name)));
		} else {		
			throw new RuntimeException();
		}
		
		return instructions;
	}

}
