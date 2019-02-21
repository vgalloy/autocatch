package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.DoubleSupplierWithException;

@FunctionalInterface
interface DoubleSupplierWithExceptionHandler<E extends Exception> {

  double run() throws E;

  @SuppressWarnings("unchecked")
  static DoubleSupplierWithExceptionHandler<RuntimeException> handle(
      final DoubleSupplierWithException supplier) {
    return (DoubleSupplierWithExceptionHandler) supplier::getAsDoubleWithException;
  }
}
