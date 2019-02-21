package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.BooleanSupplierWithException;

@FunctionalInterface
interface BooleanSupplierWithExceptionHandler<E extends Exception> {

  boolean run() throws E;

  @SuppressWarnings("unchecked")
  static BooleanSupplierWithExceptionHandler<RuntimeException> handle(
      final BooleanSupplierWithException supplier) {
    return (BooleanSupplierWithExceptionHandler) supplier::getAsBooleanWithException;
  }
}
