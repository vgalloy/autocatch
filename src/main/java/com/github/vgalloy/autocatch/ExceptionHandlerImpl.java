package com.github.vgalloy.autocatch;

import com.github.vgalloy.autocatch.function.RunnableWithException;
import java.util.Objects;

class ExceptionHandlerImpl implements ExceptionHandler<Exception> {

  private final RunnableWithException runnable;

  private ExceptionHandlerImpl(final RunnableWithException runnable) {
    this.runnable = Objects.requireNonNull(runnable, "runnable");
  }

  static ExceptionHandler<RuntimeException> handle(final RunnableWithException runnable) {
    final ExceptionHandler<Exception> exceptionHandler = new ExceptionHandlerImpl(runnable);
    @SuppressWarnings("unchecked")
    final ExceptionHandler<RuntimeException> runtimeHandler =
        (ExceptionHandler<RuntimeException>) (ExceptionHandler) exceptionHandler;
    return runtimeHandler;
  }

  @Override
  public void run() throws Exception {
    runnable.runWithException();
  }
}
