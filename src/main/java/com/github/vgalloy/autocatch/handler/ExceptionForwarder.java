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
import com.github.vgalloy.autocatch.function.ConsumerWithException;
import com.github.vgalloy.autocatch.function.DoubleSupplierWithException;
import com.github.vgalloy.autocatch.function.IntSupplierWithException;
import com.github.vgalloy.autocatch.function.RunnableWithException;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

final class ExceptionForwarder implements AutoCatcher {

  @Override
  public BooleanSupplier unDeclare(final BooleanSupplierWithException supplier) {
    return BooleanSupplierWithExceptionHandler.handle(supplier)::getAsBoolean;
  }

  @Override
  public ByteSupplier unDeclare(final ByteSupplierWithException supplier) {
    return ByteSupplierWithExceptionHandler.handle(supplier)::getAsByte;
  }

  @Override
  public CharSupplier unDeclare(final CharSupplierWithException supplier) {
    return CharSupplierWithExceptionHandler.handle(supplier)::getAsChar;
  }

  @Override
  public DoubleSupplier unDeclare(final DoubleSupplierWithException supplier) {
    return DoubleSupplierWithExceptionHandler.handle(supplier)::getAsDouble;
  }

  @Override
  public IntSupplier unDeclare(final IntSupplierWithException supplier) {
    return IntSupplierWithExceptionHandler.handle(supplier)::getAsInt;
  }

  @Override
  public Runnable unDeclare(final RunnableWithException runnable) {
    return RunnableWithExceptionHandler.handle(runnable)::run;
  }

  @Override
  public <T> Supplier<T> unDeclare(final Callable<T> callable) {
    return CallableWithExceptionHandler.handle(callable)::call;
  }

  @Override
  public <T> Consumer<T> unDeclare(final ConsumerWithException<T> consumer) {
    return ConsumerWithExceptionHandler.handle(consumer)::accept;
  }
}
