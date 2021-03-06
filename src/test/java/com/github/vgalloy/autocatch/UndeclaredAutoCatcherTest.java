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

import com.github.vgalloy.autocatch.function.ByteSupplierWithException;
import com.github.vgalloy.autocatch.function.CharSupplierWithException;
import com.github.vgalloy.autocatch.function.ConsumerWithException;
import com.github.vgalloy.autocatch.function.IntSupplierWithException;
import com.github.vgalloy.autocatch.function.RunnableWithException;
import com.github.vgalloy.autocatch.handler.AutoCatcher;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 20/11/18.
 *
 * @author Vincent Galloy
 */
class UndeclaredAutoCatcherTest {

  private static final AutoCatcher AUTO_CATCHER = AutoCatcher.wrapWithUndeclaredHandler();

  @Test
  void correctCase() {
    // GIVEN
    final Integer value = 4;
    final Callable<Integer> callable = () -> value;

    // WHEN
    final Integer result = AUTO_CATCHER.autoCatch(callable);

    // THEN
    Assertions.assertEquals(value, result);
  }

  @Test
  void throwRuntime() {
    // GIVEN
    final String message = "FAKE";
    final Callable<Integer> callable =
        () -> {
          throw new IllegalStateException(message);
        };

    // WHEN
    final IllegalStateException exception =
        Assertions.assertThrows(
            IllegalStateException.class, () -> AUTO_CATCHER.autoCatch(callable));

    // THEN
    Assertions.assertEquals(message, exception.getMessage());
  }

  @Test
  void throwException() {
    // GIVEN
    final String message = "FAKE";
    final Callable<Integer> callable =
        () -> {
          throw new IOException(message);
        };

    // WHEN
    final UndeclaredThrowableException exception =
        Assertions.assertThrows(
            UndeclaredThrowableException.class, () -> AUTO_CATCHER.autoCatch(callable));

    // THEN
    Assertions.assertEquals("FAKE", exception.getUndeclaredThrowable().getMessage());
    Assertions.assertEquals(IOException.class, exception.getUndeclaredThrowable().getClass());
  }

  @Test
  void intSupplier() {
    // GIVEN
    final String message = "FAKE";
    final IntSupplierWithException callable =
        () -> {
          throw new IOException(message);
        };

    // WHEN
    final UndeclaredThrowableException exception =
        Assertions.assertThrows(
            UndeclaredThrowableException.class, () -> AUTO_CATCHER.autoCatch(callable));

    // THEN
    Assertions.assertEquals("FAKE", exception.getUndeclaredThrowable().getMessage());
    Assertions.assertEquals(IOException.class, exception.getUndeclaredThrowable().getClass());
  }

  @Test
  void charSupplier() {
    // GIVEN
    final CharSupplierWithException callable = () -> '2';

    // WHEN
    final char result = AUTO_CATCHER.autoCatch(callable);

    // THEN
    Assertions.assertEquals('2', result);
  }

  @Test
  void correctByteSupplier() {
    // GIVEN
    final byte input = 2;
    final ByteSupplierWithException callable = () -> input;

    // WHEN
    final byte result = AUTO_CATCHER.autoCatch(callable);

    // THEN
    Assertions.assertEquals(input, result);
  }

  @Test
  void predicate() {
    // GIVEN
    final Optional<File> empty = Optional.empty();

    // WHEN
    final boolean result =
        empty
            .filter(AUTO_CATCHER.unDeclare(this::isAbsolute))
            .map(AUTO_CATCHER.<File, Boolean>unDeclare(this::isAbsolute))
            .orElse(false);

    // THEN
    Assertions.assertFalse(result);
  }

  @Test
  void function() {
    // GIVEN
    final Optional<File> file = Optional.of(new File(""));

    // WHEN
    final boolean result =
        file.map(AUTO_CATCHER.<File, Boolean>unDeclare(this::isAbsolute)).orElse(false);

    // THEN
    Assertions.assertTrue(result);
  }

  @Test
  void runnable() {
    // GIVEN
    final RunnableWithException runnableWithException =
        () -> {
          throw new IOException("FAKE");
        };

    // WHEN
    final UndeclaredThrowableException exception =
        Assertions.assertThrows(
            UndeclaredThrowableException.class,
            () -> AUTO_CATCHER.autoCatch(runnableWithException));

    // THEN
    Assertions.assertEquals("FAKE", exception.getUndeclaredThrowable().getMessage());
    Assertions.assertEquals(IOException.class, exception.getUndeclaredThrowable().getClass());
  }

  @Test
  void consumer() {
    // GIVEN
    final ConsumerWithException<Integer> consumerWithException =
        a -> {
          throw new IOException("FAKE");
        };
    final Consumer<Integer> consumer = AUTO_CATCHER.unDeclare(consumerWithException);

    // WHEN
    final UndeclaredThrowableException exception =
        Assertions.assertThrows(UndeclaredThrowableException.class, () -> consumer.accept(1));

    // THEN
    Assertions.assertEquals("FAKE", exception.getUndeclaredThrowable().getMessage());
    Assertions.assertEquals(IOException.class, exception.getUndeclaredThrowable().getClass());
  }

  private boolean isAbsolute(final File file) throws Exception {
    return file.getCanonicalFile().isAbsolute();
  }
}
