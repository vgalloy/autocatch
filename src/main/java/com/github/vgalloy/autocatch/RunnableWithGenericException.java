package com.github.vgalloy.autocatch;

interface RunnableWithGenericException<E extends Exception> {

  void run() throws E;
}
