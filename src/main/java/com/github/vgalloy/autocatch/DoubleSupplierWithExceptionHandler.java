package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.DoubleSupplierWithException;

@FunctionalInterface
interface DoubleSupplierWithExceptionHandler<E extends Exception> {

  double run() throws E;

  static DoubleSupplierWithExceptionHandler<RuntimeException> handle(
      final DoubleSupplierWithException runnable) {
    @SuppressWarnings("unchecked")
    final DoubleSupplierWithExceptionHandler<RuntimeException> runtimeHandler =
        (DoubleSupplierWithExceptionHandler) runnable::getAsDoubleWithException;
    return runtimeHandler;
  }
}
