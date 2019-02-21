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
    throw new AssertionError("No instance of com.github.vgalloy.autocatch.AutoCatch");
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param callable the callable
   * @param <T> the return type of the callable
   * @return the value provided by the callable
   */
  public static <T> T autoCatch(final Callable<T> callable) {
    return CallableWithExceptionHandler.handle(callable).run();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param runnableWithException the runnable
   */
  public static void autoCatch(final RunnableWithException runnableWithException) {
    RunnableWithExceptionHandler.handle(runnableWithException).run();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param intSupplierWithException the int supplier
   * @return the primitive int provided by the supplier
   */
  public static int autoCatch(final IntSupplierWithException intSupplierWithException) {
    return IntSupplierWithExceptionHandler.handle(intSupplierWithException).run();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param doubleSupplierWithException the double supplier
   * @return the primitive double provided by the supplier
   */
  public static double autoCatch(final DoubleSupplierWithException doubleSupplierWithException) {
    return DoubleSupplierWithExceptionHandler.handle(doubleSupplierWithException).run();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param booleanSupplierWithException the boolean supplier
   * @return the primitive boolean provided by the supplier
   */
  public static boolean autoCatch(final BooleanSupplierWithException booleanSupplierWithException) {
    return BooleanSupplierWithExceptionHandler.handle(booleanSupplierWithException).run();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param charSupplierWithException the char supplier
   * @return the primitive char provided by the supplier
   */
  public static char autoCatch(final CharSupplierWithException charSupplierWithException) {
    return CharSupplierWithExceptionHandler.handle(charSupplierWithException).run();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param byteSupplierWithException the byte supplier
   * @return the primitive byte provided by the supplier
   */
  public static byte autoCatch(final ByteSupplierWithException byteSupplierWithException) {
    return ByteSupplierWithExceptionHandler.handle(byteSupplierWithException).run();
  }
}
