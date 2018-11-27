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

import java.util.concurrent.Callable;

public final class AutoCatch {

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private AutoCatch() {
        throw new AssertionError("No instance of AutoCatch");
    }

    /**
     * Execute the provided callable and wrap Exception (if any) in runtime.
     *
     * @param callable the callable
     * @param <T>      the return type of the callable
     * @return the value provided by the callable
     */
    public static <T> T autoCatch(final Callable<T> callable) {
        try {
            return callable.call();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the provided callable and wrap Exception (if any) in runtime.
     *
     * @param runnableWithException the runnable
     */
    public static void autoCatch(final RunnableWithException runnableWithException) {
        try {
            runnableWithException.run();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the provided supplier and wrap Exception (if any) in runtime.
     *
     * @param intSupplier the int supplier
     * @return the primitive int provided by the supplier
     */
    public static int autoCatch(final IntSupplierWithException intSupplier) {
        try {
            return intSupplier.getAsInt();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the provided callable and wrap Exception (if any) in runtime.
     *
     * @param doubleSupplier the double supplier
     * @return the primitive double provided by the supplier
     */
    public static double autoCatch(final DoubleSupplierWithException doubleSupplier) {
        try {
            return doubleSupplier.getAsDouble();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the provided callable and wrap Exception (if any) in runtime.
     *
     * @param booleanSupplier the boolean supplier
     * @return the primitive boolean provided by the supplier
     */
    public static boolean autoCatch(final BooleanSupplierWithException booleanSupplier) {
        try {
            return booleanSupplier.getAsBoolean();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the provided callable and wrap Exception (if any) in runtime.
     *
     * @param charSupplier the char supplier
     * @return the primitive char provided by the supplier
     */
    public static char autoCatch(final CharSupplierWithException charSupplier) {
        try {
            return charSupplier.getAsChar();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the provided callable and wrap Exception (if any) in runtime.
     *
     * @param byteSupplier the byte supplier
     * @return the primitive byte provided by the supplier
     */
    public static byte autoCatch(final ByteSupplierWithException byteSupplier) {
        try {
            return byteSupplier.getAsByte();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
