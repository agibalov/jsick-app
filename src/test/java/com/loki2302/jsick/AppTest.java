package com.loki2302.jsick;

import org.junit.Test;

import static com.loki2302.jsick.TestHelper.*;
import static org.junit.Assert.*;

public class AppTest {
	private final double EPS = 0.0001;
	
	@Test
	public void canDefineIntVariableFromIntConst() {		
		Variables variables = execute(
				"int x = 1;"
				);
		assertEquals(1, variables.getIntValue("x"));
	}
	
	@Test
	public void canDefineDoubleVariableFromDoubleConst() {		
		Variables variables = execute(
				"double x = 1.3;"
				);
		assertEquals(1.3, variables.getDoubleValue("x"), EPS);
	}
	
	@Test
	public void canDefineDoubleVariableFromIntConst() {		
		Variables variables = execute(
				"double x = 1;"
				);
		assertEquals(1, variables.getDoubleValue("x"), EPS);
	}
	
	@Test
	public void canDefineIntVariableFromIntAdd() {		
		Variables variables = execute(
				"int x = 1 + 2;"
				);
		assertEquals(3, variables.getIntValue("x"));
	}
	
	@Test
	public void canDefineIntVariableFromIntSub() {		
		Variables variables = execute(
				"int x = 1 - 2;"
				);
		assertEquals(-1, variables.getIntValue("x"));
	}
	
	@Test
	public void canDefineIntVariableFromIntMul() {		
		Variables variables = execute(
				"int x = 2 * 3;"
				);
		assertEquals(6, variables.getIntValue("x"));
	}
	
	@Test
	public void canDefineIntVariableFromIntDiv() {		
		Variables variables = execute(
				"int x = 3 / 2;"
				);
		assertEquals(1, variables.getIntValue("x"));
	}
	
	@Test
	public void canDefineIntVariableFromIntRem() {		
		Variables variables = execute(
				"int x = 3 % 2;"
				);
		assertEquals(1, variables.getIntValue("x"));
	}
	
	@Test
	public void canDefineDoubleVariableFromDoubleAdd() {		
		Variables variables = execute(
				"double x = 1.2 + 2.3;"
				);
		assertEquals(3.5, variables.getDoubleValue("x"), EPS);
	}
	
	@Test
	public void canDefineDoubleVariableFromDoubleSub() {		
		Variables variables = execute(
				"double x = 1.2 - 2.3;"
				);
		assertEquals(-1.1, variables.getDoubleValue("x"), EPS);
	}
	
	@Test
	public void canDefineDoubleVariableFromDoubleMul() {		
		Variables variables = execute(
				"double x = 2.1 * 3.5;"
				);
		assertEquals(7.35, variables.getDoubleValue("x"), EPS);
	}
	
	@Test
	public void canDefineDoubleVariableFromDoubleDiv() {		
		Variables variables = execute(
				"double x = 3.5 / 2.1;"
				);
		assertEquals(1.66666, variables.getDoubleValue("x"), EPS);
	}
}
