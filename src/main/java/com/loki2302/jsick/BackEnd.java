package com.loki2302.jsick;

import java.io.IOException;

import com.loki2302.jsick.statements.Program;
import com.loki2302.jsick.types.Types;

public interface BackEnd {
	void process(Program program, Types types, String sourceName) throws IOException;
}