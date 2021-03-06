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

import com.github.vgalloy.autocatch.function.ConsumerWithException;

@FunctionalInterface
interface ConsumerWithExceptionHandler<T, E extends Exception> {

  /**
   * Consume the provided object.
   *
   * @param t input
   */
  void accept(T t) throws E;

  @SuppressWarnings("unchecked")
  static <T> ConsumerWithExceptionHandler<T, RuntimeException> handle(
      final ConsumerWithException<T> supplier) {
    return (ConsumerWithExceptionHandler) a -> supplier.accept((T) a);
  }
}
