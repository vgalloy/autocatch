/*
 * Copyright 2019 Vincent Galloy
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
package com.github.vgalloy.autocatch.handler;

import com.github.vgalloy.autocatch.function.BooleanSupplierWithException;
import com.github.vgalloy.autocatch.function.ByteSupplier;
import com.github.vgalloy.autocatch.function.ByteSupplierWithException;
import com.github.vgalloy.autocatch.function.CharSupplier;
import com.github.vgalloy.autocatch.function.CharSupplierWithException;
import com.github.vgalloy.autocatch.function.DoubleSupplierWithException;
import com.github.vgalloy.autocatch.function.IntSupplierWithException;
import com.github.vgalloy.autocatch.function.RunnableWithException;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface ExceptionHandler {

  /**
   * The exception will be wrapped into an {@link UndeclaredExceptionHandler}.
   *
   * @return a instance of ExceptionHandler
   */
  static AutoCatcher wrapWithUndeclaredHandler() {
    return new UndeclaredExceptionHandler();
  }

  /**
   * The exception is not declare but still throw if exists.
   *
   * <pre>{@code
   * void throwIOException() {
   *   Autocatch.autocatch(() -> { throw new IOException("Some thing goes wrong") })
   * }
   * }</pre>
   *
   * calling throwIOException will throw an exception but the method throwIOException don't need to
   * declare it
   *
   * @return a forwarder exception handler
   */
  static AutoCatcher exceptionForwarder() {
    return new ExceptionForwarder();
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return the supplier not declaring exception
   */
  BooleanSupplier unDeclare(final BooleanSupplierWithException supplier);

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  ByteSupplier unDeclare(final ByteSupplierWithException supplier);

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  CharSupplier unDeclare(final CharSupplierWithException supplier);

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  DoubleSupplier unDeclare(final DoubleSupplierWithException supplier);

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  IntSupplier unDeclare(final IntSupplierWithException supplier);

  /**
   * Convert the provided runnable into another which is not declaring exception.
   *
   * @param runnable the runnable declaring an exception
   * @return a runnable
   */
  Runnable unDeclare(final RunnableWithException runnable);

  /**
   * Convert the provided Callable into a supplier.
   *
   * @param callable the callable declaring an exception
   * @return a supplier
   */
  <T> Supplier<T> unDeclare(final Callable<T> callable);
}
