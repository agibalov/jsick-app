package com.loki2302.jsick.vm;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class VirtualMachineTest {
	
	private final static double DELTA = 0.00001;
	
	@Test
	public void testPushInt() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushInt(1);
		assertEquals(1, vm.size());
		assertEquals(1, vm.peek());
	}
	
	@Test
	public void testIntAdd() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushInt(1);
		vm.pushInt(2);
		vm.intAdd();
		assertEquals(1, vm.size());
		assertEquals(3, vm.peek());
	}
	
	@Test
	public void testIntSub() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushInt(1);
		vm.pushInt(2);
		vm.intSub();
		assertEquals(1, vm.size());
		assertEquals(-1, vm.peek());
	}

	@Test
	public void testIntMul() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushInt(1);
		vm.pushInt(2);
		vm.intMul();
		assertEquals(1, vm.size());
		assertEquals(2, vm.peek());
	}
	
	@Test
	public void testIntDiv() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushInt(3);
		vm.pushInt(2);
		vm.intDiv();
		assertEquals(1, vm.size());
		assertEquals(1, vm.peek());
	}

	@Test
	public void testPushDouble() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushDouble(1);
		assertEquals(1, vm.size());
		assertEquals(1, (Double)vm.peek(), DELTA);
	}
	
	@Test
	public void testDoubleAdd() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushDouble(1);
		vm.pushDouble(2);
		vm.doubleAdd();
		assertEquals(1, vm.size());
		assertEquals(3, (Double)vm.peek(), DELTA);
	}
	
	@Test
	public void testDoubleSub() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushDouble(1);
		vm.pushDouble(2);
		vm.doubleSub();
		assertEquals(1, vm.size());
		assertEquals(-1, (Double)vm.peek(), DELTA);
	}

	@Test
	public void testDoubleMul() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushDouble(1);
		vm.pushDouble(2);
		vm.doubleMul();
		assertEquals(1, vm.size());
		assertEquals(2, (Double)vm.peek(), DELTA);
	}
	
	@Test
	public void testDoubleDiv() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushDouble(3);
		vm.pushDouble(2);
		vm.doubleDiv();
		assertEquals(1, vm.size());
		assertEquals(1.5, (Double)vm.peek(), DELTA);
	}
	
	@Test
	public void testIntToDouble() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushInt(123);
		vm.intToDouble();
		assertEquals(1, vm.size());
		assertEquals(123, (Double)vm.peek(), DELTA);
	}
	
	@Test
	public void testDoubleToInt() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushDouble(3.14);
		vm.doubleToInt();
		assertEquals(1, vm.size());
		assertEquals(3, (int)(Integer)vm.peek());
	}
	
	@Test
	public void localsTest() {
		VirtualMachine vm = new VirtualMachine();
		vm.pushInt(1);
		vm.saveLocal(0);
		assertEquals(0, vm.size());
		vm.loadLocal(0);
		assertEquals(1, vm.size());
		assertEquals(1, (int)(Integer)vm.peek());
	}
	
	@Test
	public void testPrintInt() {
		Mockery context = new Mockery();
		final Printer printer = context.mock(Printer.class);
		context.checking(new Expectations() {{
			oneOf(printer).printInt(123);
		}});
		
		VirtualMachine vm = new VirtualMachine(printer);
		vm.pushInt(123);
		vm.printInt();
		assertEquals(0, vm.size());
		
		context.assertIsSatisfied();
	}
	
	@Test
	public void testPrintDouble() {
		Mockery context = new Mockery();
		final Printer printer = context.mock(Printer.class);
		context.checking(new Expectations() {{
			oneOf(printer).printDouble(3.14);
		}});
		
		VirtualMachine vm = new VirtualMachine(printer);
		vm.pushDouble(3.14);
		vm.printDouble();
		assertEquals(0, vm.size());
		
		context.assertIsSatisfied();
	}

	
}
