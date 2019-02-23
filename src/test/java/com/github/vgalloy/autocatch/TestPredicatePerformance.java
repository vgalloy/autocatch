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
package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.PredicateWithError;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class TestPredicatePerformance {

  /**
   * Result
   *
   * <pre>
   * Benchmark                                           Mode  Cnt         Score   Error  Units
   * TestPredicatePerformance.base                      thrpt    2  61807272.165          ops/s
   * TestPredicatePerformance.noExceptionWithAutoCatch  thrpt    2  48215562.216          ops/s
   * TestPredicatePerformance.noExceptionWithNothing    thrpt    2  62268506.808          ops/s
   * </pre>
   */
  public static void main(String[] args) throws Exception {
    final Options opt =
        new OptionsBuilder()
            .include(TestPredicatePerformance.class.getSimpleName())
            .warmupIterations(1)
            .measurementIterations(2)
            .forks(1)
            .build();

    new Runner(opt).run();
  }

  private static final PredicateWithError<Integer> PREDICATE_WITHOUT_EXCEPTION = integer -> true;

  @Benchmark
  public boolean base() {
    return true;
  }

  @Benchmark
  public boolean noExceptionWithNothing() {
    try {
      return PREDICATE_WITHOUT_EXCEPTION.test(1);
    } catch (final Exception ignored) {
      return false;
    }
  }

  @Benchmark
  public boolean noExceptionWithAutoCatch() {
    try {
      return AutoCatch.unDeclare(PREDICATE_WITHOUT_EXCEPTION).test(1);
    } catch (final Exception ignored) {
      return false;
    }
  }
}
