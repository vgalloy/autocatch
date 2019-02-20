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
import com.github.vgalloy.autocatch.function.IntSupplierWithException;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 20/11/18.
 *
 * @author Vincent Galloy
 */
class AutoCatchTest {

  @Test
  void correctCase() {
    // GIVEN
    final Integer value = 4;
    final Callable<Integer> callable = () -> value;

    // WHEN
    final Integer result = AutoCatch.autoCatch(callable);

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
        Assertions.assertThrows(IllegalStateException.class, () -> AutoCatch.autoCatch(callable));

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
    final IOException exception =
        Assertions.assertThrows(IOException.class, () -> AutoCatch.autoCatch(callable));

    // THEN
    Assertions.assertEquals("FAKE", exception.getMessage());
    Assertions.assertEquals(IOException.class, exception.getClass());
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
    final IOException exception =
        Assertions.assertThrows(IOException.class, () -> AutoCatch.autoCatch(callable));

    // THEN
    Assertions.assertEquals("FAKE", exception.getMessage());
    Assertions.assertEquals(IOException.class, exception.getClass());
  }

  @Test
  void correctByteSupplier() {
    // GIVEN
    final byte input = 2;
    final ByteSupplierWithException callable = () -> input;

    // WHEN
    final byte result = AutoCatch.autoCatch(callable);

    // THEN
    Assertions.assertEquals(input, result);
  }
}
