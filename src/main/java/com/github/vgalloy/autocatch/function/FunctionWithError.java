package com.github.vgalloy.autocatch.function;

public interface FunctionWithError<T, R> {

  R apply(T t) throws Exception;
}
