package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.ByteSupplierWithException;

@FunctionalInterface
interface ByteSupplierWithExceptionHandler<E extends Exception> {

  byte run() throws E;

  @SuppressWarnings("unchecked")
  static ByteSupplierWithExceptionHandler<RuntimeException> handle(
      final ByteSupplierWithException supplier) {
    return (ByteSupplierWithExceptionHandler) supplier::getAsByteWithException;
  }
}
