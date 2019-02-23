package com.github.vgalloy.autocatch.function;

@FunctionalInterface
public interface ByteSupplier {

  /**
   * Gets a result.
   *
   * @return a result
   */
  byte getAsByte();
}
