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
package com.vgalloy.github.autocatch;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 20/11/18.
 *
 * @author Vincent Galloy
 */
public class AutoCatchTest {

    @Test
    public void correctCase() {
        // GIVEN
        final Integer value = 4;
        final Callable<Integer> callable = () -> value;

        // WHEN
        final Integer result = AutoCatch.autoCatch(callable);

        // THEN
        Assert.assertEquals(value, result);
    }

    @Test
    public void throwRuntime() {
        // GIVEN
        final String message = "FAKE";
        final Callable<Integer> callable = () -> {
            throw new IllegalStateException(message);
        };

        // WHEN / THEN
        Assertions.assertThatThrownBy(() -> AutoCatch.autoCatch(callable))
            .hasMessage(message)
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void throwException() {
        // GIVEN
        final String message = "FAKE";
        final Callable<Integer> callable = () -> {
            throw new IOException(message);
        };

        // WHEN / THEN
        Assertions.assertThatThrownBy(() -> AutoCatch.autoCatch(callable))
            .hasMessage("java.io.IOException: FAKE")
            .isInstanceOf(RuntimeException.class)
            .hasCauseExactlyInstanceOf(IOException.class);
    }

    @Test
    public void intSupplier() {
        // GIVEN
        final String message = "FAKE";
        final IntSupplierWithException callable = () -> {
            throw new IOException(message);
        };

        // WHEN / THEN
        Assertions.assertThatThrownBy(() -> AutoCatch.autoCatch(callable))
            .hasMessage("java.io.IOException: FAKE")
            .isInstanceOf(RuntimeException.class)
            .hasCauseExactlyInstanceOf(IOException.class);
    }

    @Test
    public void correctByteSupplier() {
        // GIVEN
        final byte input = 2;
        final ByteSupplierWithException callable = () -> input;

        // WHEN
        final byte result = AutoCatch.autoCatch(callable);

        // THEN
        Assertions.assertThat(result).isEqualTo(input);
    }
}