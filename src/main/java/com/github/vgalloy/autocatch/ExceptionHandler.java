package com.github.vgalloy.autocatch;

interface ExceptionHandler<E extends Exception> {

  void run() throws E;
}
