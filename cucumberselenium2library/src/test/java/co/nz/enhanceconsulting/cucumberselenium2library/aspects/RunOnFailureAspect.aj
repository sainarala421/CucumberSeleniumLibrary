package co.nz.enhanceconsulting.cucumberselenium2library.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import co.nz.enhanceconsulting.cucumberselenium2library.utils.RunOnFailureKeywords;

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
