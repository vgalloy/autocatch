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

import java.util.concurrent.Callable;

/**
 * Created by Vincent Galloy on 20/11/18.
 *
 * @author Vincent Galloy
 */
public class AutoCatch {

    /**
     * Constructor
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
    public static <T> T propagate(final Callable<T> callable) {
        try {
            return callable.call();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the provided callable and ignore any exception thrown.
     *
     * @param callable the callable
     * @param <T>      the return type of the callable
     * @return the value provided by the callable
     */
    public static <T> T ignore(final Callable<T> callable) {
        try {
            return callable.call();
        } catch (final Exception e) {
            return null;
        }
    }

    public static void propagate(final RunnableWithError runnableWithError) {
        try {
            runnableWithError.run();
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the provided runnable and ignore any exception thrown.
     *
     * @param runnableWithError the runnable
     */
    public static void ignore(final RunnableWithError runnableWithError) {
        try {
            runnableWithError.run();
        } catch (final Exception e) {

        }
    }

    public interface RunnableWithError {
        void run() throws Exception;
    }
}
