/*
 * Copyright 2018 Vincent Galloy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.BooleanSupplierWithException;
import com.github.vgalloy.autocatch.function.ByteSupplierWithException;
import com.github.vgalloy.autocatch.function.CharSupplierWithException;
import com.github.vgalloy.autocatch.function.DoubleSupplierWithException;
import com.github.vgalloy.autocatch.function.IntSupplierWithException;
import com.github.vgalloy.autocatch.function.RunnableWithException;
import java.util.concurrent.Callable;

public final class AutoCatch {

  /** Constructor. Private to avoid instantiation */
  private AutoCatch() {
    throw new AssertionError("No instance of AutoCatch");
  }

  /**
   * Execute the provided callable and wrap Exception (if any) in runtime.
   *
   * @param callable the callable
   * @param <T> the return type of the callable
   * @return the value provided by the callable
   */
  public static <T> T autoCatch(final Callable<T> callable) {
    final Object[] wrapper = new Object[1];
    autoCatch(
        () -> {
          wrapper[0] = callable.call();
        });
    @SuppressWarnings("unchecked")
    final T result = (T) wrapper[0];
    return result;
  }

  /**
   * Execute the provided callable and wrap Exception (if any) in runtime.
   *
   * @param runnableWithException the runnable
   */
  public static void autoCatch(final RunnableWithException runnableWithException) {
    final ExceptionHandler<RuntimeException> handler =
        ExceptionHandlerImpl.handle(runnableWithException);
    handler.run();
  }

  /**
   * Execute the provided supplier and wrap Exception (if any) in runtime.
   *
   * @param intSupplierWithException the int supplier
   * @return the primitive int provided by the supplier
   */
  public static int autoCatch(final IntSupplierWithException intSupplierWithException) {
    final int[] wrapper = new int[1];
    autoCatch(
        () -> {
          wrapper[0] = intSupplierWithException.getAsIntWithException();
        });
    return wrapper[0];
  }

  /**
   * Execute the provided callable and wrap Exception (if any) in runtime.
   *
   * @param doubleSupplierWithException the double supplier
   * @return the primitive double provided by the supplier
   */
  public static double autoCatch(final DoubleSupplierWithException doubleSupplierWithException) {
    final double[] wrapper = new double[1];
    autoCatch(
        () -> {
          wrapper[0] = doubleSupplierWithException.getAsDoubleWithException();
        });
    return wrapper[0];
  }

  /**
   * Execute the provided callable and wrap Exception (if any) in runtime.
   *
   * @param booleanSupplierWithException the boolean supplier
   * @return the primitive boolean provided by the supplier
   */
  public static boolean autoCatch(final BooleanSupplierWithException booleanSupplierWithException) {
    final boolean[] wrapper = new boolean[1];
    autoCatch(
        () -> {
          wrapper[0] = booleanSupplierWithException.getAsBooleanWithException();
        });
    return wrapper[0];
  }

  /**
   * Execute the provided callable and wrap Exception (if any) in runtime.
   *
   * @param charSupplierWithException the char supplier
   * @return the primitive char provided by the supplier
   */
  public static char autoCatch(final CharSupplierWithException charSupplierWithException) {
    final char[] wrapper = new char[1];
    autoCatch(
        () -> {
          wrapper[0] = charSupplierWithException.getAsCharWithException();
        });
    return wrapper[0];
  }

  /**
   * Execute the provided callable and wrap Exception (if any) in runtime.
   *
   * @param byteSupplierWithException the byte supplier
   * @return the primitive byte provided by the supplier
   */
  public static byte autoCatch(final ByteSupplierWithException byteSupplierWithException) {
    final byte[] wrapper = new byte[1];
    autoCatch(
        () -> {
          wrapper[0] = byteSupplierWithException.getAsByteWithException();
        });
    return wrapper[0];
  }
}
