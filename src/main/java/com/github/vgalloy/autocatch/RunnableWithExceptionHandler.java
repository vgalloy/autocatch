package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.RunnableWithException;

@FunctionalInterface
interface RunnableWithExceptionHandler<E extends Exception> {

  void run() throws E;

  @SuppressWarnings("unchecked")
  static RunnableWithExceptionHandler<RuntimeException> handle(
      final RunnableWithException supplier) {
    return (RunnableWithExceptionHandler) supplier::runWithException;
  }
}
