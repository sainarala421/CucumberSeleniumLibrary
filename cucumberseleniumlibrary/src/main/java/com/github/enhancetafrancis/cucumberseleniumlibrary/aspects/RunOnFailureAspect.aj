package com.github.enhancetafrancis.cucumberseleniumlibrary.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

import com.github.enhancetafrancis.cucumberseleniumlibrary.utils.RunOnFailureKeywords;

public aspect RunOnFailureAspect {

	private static ThreadLocal<Throwable> lastThrowable = new ThreadLocal<Throwable>();

	pointcut handleThrowable() : 
    execution(public * co.nz.enhanceconsulting.cucumberselenium2library.*.*(..));

	after() throwing(Throwable t) : handleThrowable() {
		if (lastThrowable.get() == t) {
			// Already handled this Throwable
			return;
		}

		((RunOnFailureKeywords) thisJoinPoint.getTarget()).runOnFailureByAspectJ();
		lastThrowable.set(t);
	}
}
