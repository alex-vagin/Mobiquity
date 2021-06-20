package com.mobiquity.model;

import java.util.function.Function;

public final class UtilException {
	@FunctionalInterface
	public interface Function_WithExceptions<T, R, E extends Exception> {
		R apply(T t) throws E;
	}
	
	public static <T, R, E extends Exception> Function<T, R> rethrowFunction(Function_WithExceptions<T, R, E> function) throws E {
		return t -> {
			try { return function.apply(t); }
			catch (Exception exception) { throwAsUnchecked(exception); return null; }
		};
	}
	@SuppressWarnings ("unchecked")
	private static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E { throw (E)exception; }
}
