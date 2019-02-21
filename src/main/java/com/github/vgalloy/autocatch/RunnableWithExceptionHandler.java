package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.RunnableWithException;

@FunctionalInterface
interface RunnableWithExceptionHandler<E extends Exception> {

  void run() throws E;

  static RunnableWithExceptionHandler<RuntimeException> handle(
      final RunnableWithException runnable) {
    @SuppressWarnings("unchecked")
    final RunnableWithExceptionHandler<RuntimeException> runtimeHandler =
        (RunnableWithExceptionHandler) runnable::runWithException;
    return runtimeHandler;
  }
}
