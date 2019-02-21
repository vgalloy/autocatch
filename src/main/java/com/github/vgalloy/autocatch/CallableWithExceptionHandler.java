package com.github.vgalloy.autocatch;

import java.util.concurrent.Callable;

@FunctionalInterface
interface CallableWithExceptionHandler<T, E extends Exception> {

  T run() throws E;

  @SuppressWarnings("unchecked")
  static <T> CallableWithExceptionHandler<T, RuntimeException> handle(final Callable<T> callable) {
    return (CallableWithExceptionHandler) callable::call;
  }
}
