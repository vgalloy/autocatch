package com.github.vgalloy.autocatch;

import java.io.IOException;
import java.util.concurrent.Callable;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Warmup;

public class TestPerformance {

  /**
   * Result "com.github.vgalloy.autocatch.TestPerformance.noExceptionWithTryCatch": 232864054.337
   * ±(99.9%) 3956224.363 ops/s [Average] (min, avg, max) = (231608895.919, 232864054.337,
   * 234307641.496), stdev = 1027418.904 CI (99.9%): [228907829.973, 236820278.700] (assumes normal
   * distribution)
   *
   * <p># Run complete. Total time: 00:08:14
   *
   * <p>REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up
   * on why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
   * experiments, perform baseline and negative tests that provide experimental control, make sure
   * the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain
   * experts. Do not assume the numbers tell you what you want them to tell.
   *
   * <p>Benchmark Mode Cnt Score Error Units TestPerformance.exceptionWithAutoCatch thrpt 5
   * 137606.601 ± 9483.009 ops/s TestPerformance.exceptionWithAutoCatchOldGeneration thrpt 5
   * 138263.851 ± 3985.082 ops/s TestPerformance.exceptionWithNothing thrpt 5 144224.108 ± 3954.272
   * ops/s TestPerformance.exceptionWithTryCatch __________thrpt 5 68815.141 ± 861.399 ops/s
   * TestPerformance.noExceptionWithAutoCatch _____________thrpt 5 224966585.577 ± 20264899.088
   * ops/s TestPerformance.noExceptionWithAutoCatchOldGeneration thrpt 5 95196421.562 ± 9492264.557
   * ops/s TestPerformance.noExceptionWithNothing _______________thrpt 5 226469413.017 ± 5743253.163
   * ops/s TestPerformance.noExceptionWithTryCatch ______________thrpt 5 232864054.337 ± 3956224.363
   * ops/s
   */
  public static void main(String[] args) throws Exception {
    org.openjdk.jmh.Main.main(args);
  }

  private static final Callable<Integer> CALLABLE_WITHOUT_EXCEPTION = () -> 1;

  @Warmup(iterations = 1)
  @Fork(value = 1)
  @Benchmark
  public void noExceptionWithAutoCatch() {
    AutoCatch.autoCatch(CALLABLE_WITHOUT_EXCEPTION);
  }

  @Warmup(iterations = 1)
  @Fork(value = 1)
  @Benchmark
  public void noExceptionWithAutoCatchOldGeneration() {
    final Object[] wrapper = new Object[1];
    AutoCatch.autoCatch(
        () -> {
          wrapper[0] = CALLABLE_WITHOUT_EXCEPTION.call();
        });
    final Integer result = (Integer) wrapper[0];
  }

  @Warmup(iterations = 1)
  @Fork(value = 1)
  @Benchmark
  public void noExceptionWithNothing() throws Exception {
    CALLABLE_WITHOUT_EXCEPTION.call();
  }

  @Warmup(iterations = 1)
  @Fork(value = 1)
  @Benchmark
  public void noExceptionWithTryCatch() {
    try {
      CALLABLE_WITHOUT_EXCEPTION.call();
    } catch (final Exception e) {

    }
  }

  private static final Callable<Integer> CALLABLE_WITH_EXCEPTION =
      () -> {
        throw new IOException("FAKE");
      };

  @Warmup(iterations = 1)
  @Fork(value = 1)
  @Benchmark
  public void exceptionWithAutoCatch() {
    try {
      AutoCatch.autoCatch(CALLABLE_WITH_EXCEPTION);
    } catch (final Exception expected) {

    }
  }

  @Warmup(iterations = 1)
  @Fork(value = 1)
  @Benchmark
  public void exceptionWithAutoCatchOldGeneration() {
    try {
      final Object[] wrapper = new Object[1];
      AutoCatch.autoCatch(
          () -> {
            wrapper[0] = CALLABLE_WITH_EXCEPTION.call();
          });
      final Integer result = (Integer) wrapper[0];
    } catch (final Exception expected) {

    }
  }

  @Warmup(iterations = 1)
  @Fork(value = 1)
  @Benchmark
  public void exceptionWithNothing() {
    try {
      CALLABLE_WITH_EXCEPTION.call();
    } catch (final Exception expected) {

    }
  }

  @Warmup(iterations = 1)
  @Fork(value = 1)
  @Benchmark
  public void exceptionWithTryCatch() {
    try {
      try {
        CALLABLE_WITH_EXCEPTION.call();
      } catch (final Exception first) {
        throw new RuntimeException(first);
      }
    } catch (final Exception expected) {

    }
  }
}
