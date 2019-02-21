package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.CharSupplierWithException;

@FunctionalInterface
interface CharSupplierWithExceptionHandler<E extends Exception> {

  char run() throws E;

  static CharSupplierWithExceptionHandler<RuntimeException> handle(
      final CharSupplierWithException runnable) {
    @SuppressWarnings("unchecked")
    final CharSupplierWithExceptionHandler<RuntimeException> runtimeHandler =
        (CharSupplierWithExceptionHandler) runnable::getAsCharWithException;
    return runtimeHandler;
  }
}
