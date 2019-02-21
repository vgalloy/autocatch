package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.IntSupplierWithException;

@FunctionalInterface
interface IntSupplierWithExceptionHandler<E extends Exception> {

  int run() throws E;

  static IntSupplierWithExceptionHandler<RuntimeException> handle(
      final IntSupplierWithException runnable) {
    @SuppressWarnings("unchecked")
    final IntSupplierWithExceptionHandler<RuntimeException> runtimeHandler =
        (IntSupplierWithExceptionHandler) runnable::getAsIntWithException;
    return runtimeHandler;
  }
}
