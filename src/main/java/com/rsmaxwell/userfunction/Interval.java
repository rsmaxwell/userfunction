package com.rsmaxwell.userfunction;

import org.codehaus.groovy.control.CompilationFailedException;

import groovy.util.Eval;

public class Interval {

	private double start;
	private double end;
	private String script;

	public boolean inScope(double time) {

		if (time < start) {
			return false;
		}

		if (time >= end) {
			return false;
		}

		return true;
	}

	public double calculate(double time) throws CompilationFailedException, AppException {

		Object object = Eval.me("t", time, script);
		if (object instanceof Number) {
			Number number = (Number) object;
			return number.doubleValue();
		}

		throw new AppException("Expression '" + script + "' returned unexpected type: " + object.getClass().getSimpleName());
	}
}
