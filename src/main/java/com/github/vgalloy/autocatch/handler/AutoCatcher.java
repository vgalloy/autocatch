package com.github.vgalloy.autocatch.handler;

import com.github.vgalloy.autocatch.function.BooleanSupplierWithException;
import com.github.vgalloy.autocatch.function.ByteSupplier;
import com.github.vgalloy.autocatch.function.ByteSupplierWithException;
import com.github.vgalloy.autocatch.function.CharSupplier;
import com.github.vgalloy.autocatch.function.CharSupplierWithException;
import com.github.vgalloy.autocatch.function.ConsumerWithException;
import com.github.vgalloy.autocatch.function.DoubleSupplierWithException;
import com.github.vgalloy.autocatch.function.FunctionWithError;
import com.github.vgalloy.autocatch.function.IntSupplierWithException;
import com.github.vgalloy.autocatch.function.PredicateWithError;
import com.github.vgalloy.autocatch.function.RunnableWithException;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface AutoCatcher {

  /**
   * The exception will be wrapped into an {@link UndeclaredExceptionHandler}.
   *
   * @return a instance of catcher
   */
  static AutoCatcher wrapWithUndeclaredHandler() {
    return new UndeclaredExceptionHandler();
  }

  /**
   * The exception is not declare but still throw if exists.
   *
   * <pre>{@code
   * void throwIOException() {
   *   Autocatch.autocatch(() -> { throw new IOException("Some thing goes wrong") })
   * }
   * }</pre>
   *
   * calling throwIOException will throw an exception but the method throwIOException don't need to
   * declare it
   *
   * @return a forwarder exception handler
   */
  static AutoCatcher exceptionForwarder() {
    return new ExceptionForwarder();
  }

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return the supplier not declaring exception
   */
  BooleanSupplier unDeclare(final BooleanSupplierWithException supplier);

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  ByteSupplier unDeclare(final ByteSupplierWithException supplier);

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  CharSupplier unDeclare(final CharSupplierWithException supplier);

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  DoubleSupplier unDeclare(final DoubleSupplierWithException supplier);

  /**
   * Convert the provided supplier into another which is not declaring exception.
   *
   * @param supplier the supplier declaring an exception
   * @return a supplier not declaring exception
   */
  IntSupplier unDeclare(final IntSupplierWithException supplier);

  /**
   * Convert the provided runnable into another which is not declaring exception.
   *
   * @param runnable the runnable declaring an exception
   * @return a runnable
   */
  Runnable unDeclare(final RunnableWithException runnable);

  /**
   * Convert the provided Callable into a supplier.
   *
   * @param callable the callable declaring an exception
   * @return a supplier
   */
  <T> Supplier<T> unDeclare(final Callable<T> callable);

  /**
   * Convert the provided consumer into another which is not declaring exception.
   *
   * @param consumer the consumer declaring an exception
   * @return a consumer
   */
  <T> Consumer<T> unDeclare(final ConsumerWithException<T> consumer);

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
