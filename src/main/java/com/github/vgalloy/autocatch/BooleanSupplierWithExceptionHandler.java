package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.BooleanSupplierWithException;

@FunctionalInterface
interface BooleanSupplierWithExceptionHandler<E extends Exception> {

  boolean run() throws E;

  static BooleanSupplierWithExceptionHandler<RuntimeException> handle(
      final BooleanSupplierWithException runnable) {
    @SuppressWarnings("unchecked")
    final BooleanSupplierWithExceptionHandler<RuntimeException> runtimeHandler =
        (BooleanSupplierWithExceptionHandler) runnable::getAsBooleanWithException;
    return runtimeHandler;
  }
}
