package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.CharSupplierWithException;

@FunctionalInterface
interface CharSupplierWithExceptionHandler<E extends Exception> {

  char run() throws E;

  @SuppressWarnings("unchecked")
  static CharSupplierWithExceptionHandler<RuntimeException> handle(
      final CharSupplierWithException supplier) {
    return (CharSupplierWithExceptionHandler) supplier::getAsCharWithException;
  }
}
