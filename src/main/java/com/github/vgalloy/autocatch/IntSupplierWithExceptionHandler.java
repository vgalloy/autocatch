package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.IntSupplierWithException;

@FunctionalInterface
interface IntSupplierWithExceptionHandler<E extends Exception> {

  int run() throws E;

  @SuppressWarnings("unchecked")
  static IntSupplierWithExceptionHandler<RuntimeException> handle(
      final IntSupplierWithException supplier) {
    return (IntSupplierWithExceptionHandler) supplier::getAsIntWithException;
  }
}
