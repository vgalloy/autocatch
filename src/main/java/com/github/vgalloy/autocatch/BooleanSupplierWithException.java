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

import java.util.function.BooleanSupplier;

@FunctionalInterface
public interface BooleanSupplierWithException extends BooleanSupplier {

    @Override
    default boolean getAsBoolean() {
        return AutoCatch.autoCatch(this);
    }

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Exception the exception to wrap
     */
    boolean getAsBooleanWithException() throws Exception;
}
