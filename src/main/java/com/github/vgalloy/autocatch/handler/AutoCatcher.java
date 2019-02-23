package com.github.vgalloy.autocatch.handler;

import com.github.vgalloy.autocatch.function.BooleanSupplierWithException;
import com.github.vgalloy.autocatch.function.ByteSupplierWithException;
import com.github.vgalloy.autocatch.function.CharSupplierWithException;
import com.github.vgalloy.autocatch.function.DoubleSupplierWithException;
import com.github.vgalloy.autocatch.function.FunctionWithError;
import com.github.vgalloy.autocatch.function.IntSupplierWithException;
import com.github.vgalloy.autocatch.function.PredicateWithError;
import com.github.vgalloy.autocatch.function.RunnableWithException;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;

public interface AutoCatcher extends ExceptionHandler {

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param booleanSupplierWithException the boolean supplier
   * @return the primitive boolean provided by the supplier
   */
  default boolean autoCatch(final BooleanSupplierWithException booleanSupplierWithException) {
    return this.unDeclare(booleanSupplierWithException).getAsBoolean();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param byteSupplierWithException the byte supplier
   * @return the primitive byte provided by the supplier
   */
  default byte autoCatch(final ByteSupplierWithException byteSupplierWithException) {
    return this.unDeclare(byteSupplierWithException).getAsByte();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param charSupplierWithException the char supplier
   * @return the primitive char provided by the supplier
   */
  default char autoCatch(final CharSupplierWithException charSupplierWithException) {
    return this.unDeclare(charSupplierWithException).getAsChar();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param doubleSupplierWithException the double supplier
   * @return the primitive double provided by the supplier
   */
  default double autoCatch(final DoubleSupplierWithException doubleSupplierWithException) {
    return this.unDeclare(doubleSupplierWithException).getAsDouble();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param intSupplierWithException the int supplier
   * @return the primitive int provided by the supplier
   */
  default int autoCatch(final IntSupplierWithException intSupplierWithException) {
    return this.unDeclare(intSupplierWithException).getAsInt();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param runnableWithException the runnable
   */
  default void autoCatch(final RunnableWithException runnableWithException) {
    this.unDeclare(runnableWithException).run();
  }

  /**
   * Execute the provided callable without declaring the exception.
   *
   * @param callable the callable
   * @param <T> the return type of the callable
   * @return the value provided by the callable
   */
  default <T> T autoCatch(final Callable<T> callable) {
    return this.unDeclare(callable).get();
  }
  /**
   * Convert the provided {@link PredicateWithError} declaring exception into a simple {@link
   * Predicate}.
   *
   * @param predicate the predicate declaring an exception
   * @return a predicate
   */
  default <T> Predicate<T> unDeclare(final PredicateWithError<T> predicate) {
    return t -> this.unDeclare(() -> predicate.test(t)).getAsBoolean();
  }

  /**
   * Convert the provided {@link FunctionWithError} declaring exception into a simple {@link
   * Function}.
   *
   * @param function the function declaring an exception
   * @return a predicate
   */
  default <T, R> Function<T, R> unDeclare(final FunctionWithError<T, R> function) {
    return t -> this.unDeclare(() -> function.apply(t)).get();
  }
}
