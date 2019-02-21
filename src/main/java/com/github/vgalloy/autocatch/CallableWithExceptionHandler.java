package com.github.vgalloy.autocatch;

import java.util.concurrent.Callable;

@FunctionalInterface
interface CallableWithExceptionHandler<T, E extends Exception> {

  T run() throws E;

  static <T> CallableWithExceptionHandler<T, RuntimeException> handle(final Callable<T> runnable) {
    @SuppressWarnings("unchecked")
    final CallableWithExceptionHandler<T, RuntimeException> runtimeHandler =
        (CallableWithExceptionHandler) runnable::call;
    return runtimeHandler;
  }
}
