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
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

final class UndeclaredExceptionHandler implements ExceptionHandler {

  @Override
  public BooleanSupplier unDeclare(final BooleanSupplierWithException supplier) {
    return () -> {
      try {
        return supplier.getAsBooleanWithException();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public ByteSupplier unDeclare(final ByteSupplierWithException supplier) {
    return () -> {
      try {
        return supplier.getAsByteWithException();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public CharSupplier unDeclare(final CharSupplierWithException supplier) {
    return () -> {
      try {
        return supplier.getAsCharWithException();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public DoubleSupplier unDeclare(final DoubleSupplierWithException supplier) {
    return () -> {
      try {
        return supplier.getAsDoubleWithException();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public IntSupplier unDeclare(final IntSupplierWithException supplier) {
    return () -> {
      try {
        return supplier.getAsIntWithException();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public Runnable unDeclare(final RunnableWithException runnable) {
    return () -> {
      try {
        runnable.runWithException();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public <T> Supplier<T> unDeclare(final Callable<T> callable) {
    return () -> {
      try {
        return callable.call();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }
}
