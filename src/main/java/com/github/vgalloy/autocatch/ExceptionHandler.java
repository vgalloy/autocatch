package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.RunnableWithException;
import java.util.Objects;

class ExceptionHandler implements RunnableWithGenericException<Exception> {

  private final RunnableWithException runnable;

  private ExceptionHandler(final RunnableWithException runnable) {
    this.runnable = Objects.requireNonNull(runnable, "runnable");
  }

  static RunnableWithGenericException<RuntimeException> handle(
      final RunnableWithException runnable) {
    final RunnableWithGenericException<Exception> exceptionHandler = new ExceptionHandler(runnable);
    @SuppressWarnings("unchecked")
    final RunnableWithGenericException<RuntimeException> runtimeHandler =
        (RunnableWithGenericException<RuntimeException>)
            (RunnableWithGenericException) exceptionHandler;
    return runtimeHandler;
  }

  @Override
  public void run() throws Exception {
    runnable.runWithException();
  }
}
