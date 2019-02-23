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
import com.github.vgalloy.autocatch.handler.ExceptionHandler;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class AutoCatch {

  private static final ExceptionHandler EXCEPTION_HANDLER = ExceptionHandler.exceptionForwarder();

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
    return EXCEPTION_HANDLER.unDeclare(booleanSupplierWithException).getAsBoolean();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param byteSupplierWithException the byte supplier
   * @return the primitive byte provided by the supplier
   */
  public static byte autoCatch(final ByteSupplierWithException byteSupplierWithException) {
    return EXCEPTION_HANDLER.unDeclare(byteSupplierWithException).getAsByte();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param charSupplierWithException the char supplier
   * @return the primitive char provided by the supplier
   */
  public static char autoCatch(final CharSupplierWithException charSupplierWithException) {
    return EXCEPTION_HANDLER.unDeclare(charSupplierWithException).getAsChar();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param doubleSupplierWithException the double supplier
   * @return the primitive double provided by the supplier
   */
  public static double autoCatch(final DoubleSupplierWithException doubleSupplierWithException) {
    return EXCEPTION_HANDLER.unDeclare(doubleSupplierWithException).getAsDouble();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param intSupplierWithException the int supplier
   * @return the primitive int provided by the supplier
   */
  public static int autoCatch(final IntSupplierWithException intSupplierWithException) {
    return EXCEPTION_HANDLER.unDeclare(intSupplierWithException).getAsInt();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param runnableWithException the runnable
   */
  public static void autoCatch(final RunnableWithException runnableWithException) {
    EXCEPTION_HANDLER.unDeclare(runnableWithException).run();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param callable the callable
   * @param <T> the return type of the callable
   * @return the value provided by the callable
   */
  public static <T> T autoCatch(final Callable<T> callable) {
    return EXCEPTION_HANDLER.unDeclare(callable).get();
  }

  /**
   * Convert the provided Callable into a supplier.
   *
   * @param callable the callable declaring an exception
   * @return a supplier
   */
  public static <T> Supplier<T> unDeclare(final Callable<T> callable) {
    return EXCEPTION_HANDLER.unDeclare(callable);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return the supplier not declaring exception
   */
  public static BooleanSupplier unDeclare(final BooleanSupplierWithException supplier) {
    return EXCEPTION_HANDLER.unDeclare(supplier);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  public static ByteSupplier unDeclare(final ByteSupplierWithException supplier) {
    return EXCEPTION_HANDLER.unDeclare(supplier);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  public static CharSupplier unDeclare(final CharSupplierWithException supplier) {
    return EXCEPTION_HANDLER.unDeclare(supplier);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  public static DoubleSupplier unDeclare(final DoubleSupplierWithException supplier) {
    return EXCEPTION_HANDLER.unDeclare(supplier);
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  public static IntSupplier unDeclare(final IntSupplierWithException supplier) {
    return EXCEPTION_HANDLER.unDeclare(supplier);
  }

  /**
   * Convert the provided {@link PredicateWithError} declaring exception into a simple {@link
   * Predicate}.
   *
   * @param predicate the predicate declaring an exception
   * @return a predicate
   */
  public static <T> Predicate<T> unDeclare(final PredicateWithError<T> predicate) {
    return t -> EXCEPTION_HANDLER.unDeclare(() -> predicate.test(t)).getAsBoolean();
  }

  /**
   * Convert the provided {@link PredicateWithError} declaring exception into a simple {@link
   * Predicate}.
   *
   * @param predicate the predicate declaring an exception
   * @return a predicate
   */
  public static <T, R> Function<T, R> unDeclare(final FunctionWithError<T, R> predicate) {
    return t -> EXCEPTION_HANDLER.unDeclare(() -> predicate.apply(t)).get();
  }

  /**
   * Convert the provided runnable into another which is not declaring exception.
   *
   * @param runnable the runnable declaring an exception
   * @return a runnable
   */
  public static Runnable unDeclare(final RunnableWithException runnable) {
    return EXCEPTION_HANDLER.unDeclare(runnable);
  }
}
