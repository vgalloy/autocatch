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

import com.github.vgalloy.autocatch.handler.ExceptionHandler;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class TestPerformance {

  /**
   * Result
   *
   * <pre>
   * Benchmark                                                                   Mode  Cnt         Score   Error  Units
   * TestPerformance.exceptionWithAutoCatch                                     thrpt    2    130095.479          ops/s
   * TestPerformance.exceptionWithAutoCatchOldGeneration                        thrpt    2    128514.217          ops/s
   * TestPerformance.exceptionWithNothing                                       thrpt    2    135276.551          ops/s
   * TestPerformance.exceptionWithTryCatch                                      thrpt    2     65333.093          ops/s
   * TestPerformance.noExceptionWithAutoCatch                                   thrpt    2  51080177.233          ops/s
   * TestPerformance.noExceptionWithAutoCatchOldGeneration                      thrpt    2  41779046.415          ops/s
   * TestPerformance.noExceptionWithAutoCatchWithUnDeclare                      thrpt    2  51476296.390          ops/s
   * TestPerformance.noExceptionWithNothing                                     thrpt    2  63340512.237          ops/s
   * TestPerformance.noExceptionWithTryCatch                                    thrpt    2  64143181.047          ops/s
   * TestPerformance.noExceptionWtihDirectForwardExceptionHandlerInvocation     thrpt    2  51414633.712          ops/s
   * TestPerformance.noExceptionWtihDirectUndeclaredExceptionHandlerInvocation  thrpt    2  63800207.537          ops/s
   * </pre>
   */
  public static void main(String[] args) throws Exception {
    final Options opt =
        new OptionsBuilder()
            .include(TestPerformance.class.getSimpleName())
            .warmupIterations(1)
            .measurementIterations(2)
            .forks(1)
            .build();

    new Runner(opt).run();
  }

  private static final Callable<Integer> CALLABLE_WITHOUT_EXCEPTION = () -> 1;

  @Benchmark
  public Integer noExceptionWithAutoCatch() {
    try {
      return AutoCatch.autoCatch(CALLABLE_WITHOUT_EXCEPTION);
    } catch (final Exception ignored) {
      return null;
    }
  }

  @Benchmark
  public Integer noExceptionWithAutoCatchWithUnDeclare() {
    try {
      return AutoCatch.unDeclare(CALLABLE_WITHOUT_EXCEPTION).get();
    } catch (final Exception ignored) {
      return null;
    }
  }

  private static final ExceptionHandler FORWARD_HANDLER = ExceptionHandler.exceptionForwarder();

  @Benchmark
  public Integer noExceptionWtihDirectForwardExceptionHandlerInvocation() {
    try {
      return FORWARD_HANDLER.unDeclare(CALLABLE_WITHOUT_EXCEPTION).get();
    } catch (final Exception ignored) {
      return null;
    }
  }

  private static final ExceptionHandler UNDECLARED_HANDLER =
      ExceptionHandler.wrapWithUndeclaredHandler();

  @Benchmark
  public Integer noExceptionWtihDirectUndeclaredExceptionHandlerInvocation() {
    try {
      return UNDECLARED_HANDLER.unDeclare(CALLABLE_WITHOUT_EXCEPTION).get();
    } catch (final Exception ignored) {
      return null;
    }
  }

  @Benchmark
  public Integer noExceptionWithAutoCatchOldGeneration() {
    try {
      final Object[] wrapper = new Object[1];
      AutoCatch.autoCatch(
          () -> {
            wrapper[0] = CALLABLE_WITHOUT_EXCEPTION.call();
          });
      return (Integer) wrapper[0];
    } catch (final Exception ignored) {
      return null;
    }
  }

  @Benchmark
  public Integer noExceptionWithNothing() {
    try {
      return CALLABLE_WITHOUT_EXCEPTION.call();
    } catch (final Exception ignored) {
      return null;
    }
  }

  @Benchmark
  public Integer noExceptionWithTryCatch() {
    try {
      try {
        return CALLABLE_WITHOUT_EXCEPTION.call();
      } catch (final Exception e) {
        throw new RuntimeException(e);
      }
    } catch (final Exception ignored) {
      return null;
    }
  }

  private static final Callable<Integer> CALLABLE_WITH_EXCEPTION =
      () -> {
        throw new IOException("FAKE");
      };

  @Benchmark
  public Integer exceptionWithAutoCatch() {
    try {
      return AutoCatch.autoCatch(CALLABLE_WITH_EXCEPTION);
    } catch (final Exception ignored) {
      return null;
    }
  }

  @Benchmark
  public Integer exceptionWithAutoCatchOldGeneration() {
    try {
      final Object[] wrapper = new Object[1];
      AutoCatch.autoCatch(
          () -> {
            wrapper[0] = CALLABLE_WITH_EXCEPTION.call();
          });
      return (Integer) wrapper[0];
    } catch (final Exception ignored) {
      return null;
    }
  }

  @Benchmark
  public Integer exceptionWithNothing() {
    try {
      return CALLABLE_WITH_EXCEPTION.call();
    } catch (final Exception ignored) {
      return null;
    }
  }

  @Benchmark
  public Integer exceptionWithTryCatch() {
    try {
      try {
        return CALLABLE_WITH_EXCEPTION.call();
      } catch (final Exception e) {
        throw new RuntimeException(e);
      }
    } catch (final Exception ignored) {
      return null;
    }
  }
}
