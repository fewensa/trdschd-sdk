package com.pujiang.trdschd.sdk.types.thr;

public class TrdschdException extends Exception {
  public TrdschdException() {
    super();
  }

  public TrdschdException(String message) {
    super(message);
  }

  public TrdschdException(String message, Throwable cause) {
    super(message, cause);
  }

  public TrdschdException(Throwable cause) {
    super(cause);
  }

  protected TrdschdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
