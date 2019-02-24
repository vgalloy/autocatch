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
import com.github.vgalloy.autocatch.function.ByteSupplier;
import com.github.vgalloy.autocatch.function.ByteSupplierWithException;
import com.github.vgalloy.autocatch.function.CharSupplier;
import com.github.vgalloy.autocatch.function.CharSupplierWithException;
import com.github.vgalloy.autocatch.function.DoubleSupplierWithException;
import com.github.vgalloy.autocatch.function.FunctionWithError;
import com.github.vgalloy.autocatch.function.IntSupplierWithException;
import com.github.vgalloy.autocatch.function.PredicateWithError;
import com.github.vgalloy.autocatch.function.RunnableWithException;
import com.github.vgalloy.autocatch.handler.AutoCatcher;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class AutoCatch {

  private static final AutoCatcher DEFAULT_CATCHER = AutoCatcher.wrapWithUndeclaredHandler();

  /** Constructor. Private to avoid instantiation */
  private AutoCatch() {
    throw new AssertionError("No instance of com.github.vgalloy.autocatch.AutoCatch");
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param booleanSupplierWithException the boolean supplier
   * @return the primitive boolean provided by the supplier
   */
  public static boolean autoCatch(final BooleanSupplierWithException booleanSupplierWithException) {
    return DEFAULT_CATCHER.autoCatch(booleanSupplierWithException);
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param byteSupplierWithException the byte supplier
   * @return the primitive byte provided by the supplier
   */
  public static byte autoCatch(final ByteSupplierWithException byteSupplierWithException) {
    return DEFAULT_CATCHER.autoCatch(byteSupplierWithException);
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param charSupplierWithException the char supplier
   * @return the primitive char provided by the supplier
   */
  public static char autoCatch(final CharSupplierWithException charSupplierWithException) {
    return DEFAULT_CATCHER.autoCatch(charSupplierWithException);
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param doubleSupplierWithException the double supplier
   * @return the primitive double provided by the supplier
   */
  public static double autoCatch(final DoubleSupplierWithException doubleSupplierWithException) {
    return DEFAULT_CATCHER.autoCatch(doubleSupplierWithException);
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param intSupplierWithException the int supplier
   * @return the primitive int provided by the supplier
   */
  public static int autoCatch(final IntSupplierWithException intSupplierWithException) {
    return DEFAULT_CATCHER.autoCatch(intSupplierWithException);
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param runnableWithException the runnable
   */
  public static void autoCatch(final RunnableWithException runnableWithException) {
    DEFAULT_CATCHER.autoCatch(runnableWithException);
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param callable the callable
   * @param <T> the return type of the callable
   * @return the value provided by the callable
   */
  public static <T> T autoCatch(final Callable<T> callable) {
    return DEFAULT_CATCHER.autoCatch(callable);
  }

  /**
   * Convert the provided Callable into a supplier.
   *
   * @param callable the callable declaring an exception
   * @return a supplier
   */
  public static <T> Supplier<T> unDeclare(final Callable<T> callable) {
    return DEFAULT_CATCHER.unDeclare(callable);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return the supplier not declaring exception
   */
  public static BooleanSupplier unDeclare(final BooleanSupplierWithException supplier) {
    return DEFAULT_CATCHER.unDeclare(supplier);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  public static ByteSupplier unDeclare(final ByteSupplierWithException supplier) {
    return DEFAULT_CATCHER.unDeclare(supplier);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  public static CharSupplier unDeclare(final CharSupplierWithException supplier) {
    return DEFAULT_CATCHER.unDeclare(supplier);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  public static DoubleSupplier unDeclare(final DoubleSupplierWithException supplier) {
    return DEFAULT_CATCHER.unDeclare(supplier);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  public static IntSupplier unDeclare(final IntSupplierWithException supplier) {
    return DEFAULT_CATCHER.unDeclare(supplier);
  }

  /**
   * Convert the provided {@link PredicateWithError} declaring exception into a simple {@link
   * Predicate}.
   *
   * @param predicate the predicate declaring an exception
   * @return a predicate
   */
  public static <T> Predicate<T> unDeclare(final PredicateWithError<T> predicate) {
    return DEFAULT_CATCHER.unDeclare(predicate);
  }

  /**
   * Convert the provided {@link FunctionWithError} declaring exception into a simple {@link
   * Function}.
   *
   * @param function the function declaring an exception
   * @return a predicate
   */
  public static <T, R> Function<T, R> unDeclare(final FunctionWithError<T, R> function) {
    return DEFAULT_CATCHER.unDeclare(function);
  }

  /**
   * Convert the provided runnable into another which is not declaring exception.
   *
   * @param runnable the runnable declaring an exception
   * @return a runnable
   */
  public static Runnable unDeclare(final RunnableWithException runnable) {
    return DEFAULT_CATCHER.unDeclare(runnable);
  }
}
