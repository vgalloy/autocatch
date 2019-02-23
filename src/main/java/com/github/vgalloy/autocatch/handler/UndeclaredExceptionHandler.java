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
  public BooleanSupplier unDeclare(BooleanSupplierWithException supplier) {
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
  public ByteSupplier unDeclare(ByteSupplierWithException supplier) {
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
  public CharSupplier unDeclare(CharSupplierWithException supplier) {
    return () -> {
      try {
        return supplier.getAsChar();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public DoubleSupplier unDeclare(DoubleSupplierWithException supplier) {
    return () -> {
      try {
        return supplier.getAsDouble();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public IntSupplier unDeclare(IntSupplierWithException supplier) {
    return () -> {
      try {
        return supplier.getAsInt();
      } catch (final RuntimeException runtime) {
        throw runtime;
      } catch (final Exception exception) {
        throw new UndeclaredThrowableException(exception);
      }
    };
  }

  @Override
  public Runnable unDeclare(RunnableWithException runnable) {
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
  public <T> Supplier<T> unDeclare(Callable<T> callable) {
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
