package com.github.vgalloy.autocatch.function;

public interface PredicateWithError<T> {

  boolean test(T t) throws Exception;
}
