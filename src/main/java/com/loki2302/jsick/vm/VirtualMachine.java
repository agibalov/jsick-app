package com.loki2302.jsick.vm;

import java.util.Stack;

public class VirtualMachine {
	
	private final Stack<Object> stack = new Stack<Object>();
	private final Printer printer;
	
	public VirtualMachine() {	
		this(new NullPrinter());
	}
	
	public VirtualMachine(Printer printer) {
		this.printer = printer;
	}
	
	public void pushInt(int x) {
		stack.push(x);
	}
	
	public void pushDouble(double x) {
		stack.push(x);
	}
	
	public void intAdd() {
		int a = (Integer)stack.pop();
		int b = (Integer)stack.pop();
		stack.push(b + a);
	}
	
	public void intSub() {		
		int a = (Integer)stack.pop();
		int b = (Integer)stack.pop();
		stack.push(b - a);
	}
	
	public void intMul() {
		int a = (Integer)stack.pop();
		int b = (Integer)stack.pop();
		stack.push(b * a);
	}
	
	public void intDiv() {
		int a = (Integer)stack.pop();
		int b = (Integer)stack.pop();
		stack.push(b / a);
	}
	
	public void doubleAdd() {
		double a = (Double)stack.pop();
		double b = (Double)stack.pop();
		stack.push(b + a);
	}
	
	public void doubleSub() {
		double a = (Double)stack.pop();
		double b = (Double)stack.pop();
		stack.push(b - a);
	}
	
	public void doubleMul() {
		double a = (Double)stack.pop();
		double b = (Double)stack.pop();
		stack.push(b * a);
	}
	
	public void doubleDiv() {
		double a = (Double)stack.pop();
		double b = (Double)stack.pop();
		stack.push(b / a);
	}
	
	public void doubleToInt() {
		double a = (Double)stack.pop();
		stack.push((int)a);
	}
	
	public void intToDouble() {
		int a = (Integer)stack.pop();
		stack.push((double)a);
	}
	
	public void printInt() {
		printer.printInt((Integer)stack.pop());
	}
	
	public void printDouble() {		
		printer.printDouble((Double)stack.pop());
	}
	
	Object peek() {
		return stack.peek();
	}
	
	int size() {
		return stack.size();
	}
}
