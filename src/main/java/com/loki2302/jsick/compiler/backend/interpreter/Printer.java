package com.loki2302.jsick.compiler.backend.interpreter;

public interface Printer {
	void printInt(int value);
	void printDouble(double value);
	
	public static class NullPrinter implements Printer {
		@Override
		public void printInt(int value) {
		}

		@Override
		public void printDouble(double value) {
		}			
	}
	
	public static class SystemOutPrintlnPrinter implements Printer {
		@Override
		public void printInt(int value) {
			System.out.println(value);
		}

		@Override
		public void printDouble(double value) {
			System.out.println(value);
		}
	}
}