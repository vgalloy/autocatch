package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.ByteSupplierWithException;

@FunctionalInterface
interface ByteSupplierWithExceptionHandler<E extends Exception> {

  byte run() throws E;

  static ByteSupplierWithExceptionHandler<RuntimeException> handle(
      final ByteSupplierWithException runnable) {
    @SuppressWarnings("unchecked")
    final ByteSupplierWithExceptionHandler<RuntimeException> runtimeHandler =
        (ByteSupplierWithExceptionHandler) runnable::getAsByteWithException;
    return runtimeHandler;
  }
}
