package com.loki2302.jsick.vm;

import java.io.PrintStream;

public class PrintStreamPrinter implements Printer {

	private final PrintStream printStream;
	
	public PrintStreamPrinter(PrintStream printStream) {
		this.printStream = printStream;
	}
	
	@Override
	public void printInt(int x) {
		printStream.printf("PRINT %d\n", x);		
	}

	@Override
	public void printDouble(double x) {
		printStream.printf("PRINT %f\n", x);		
	}

}
