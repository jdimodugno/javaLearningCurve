package com.juandi.learningcurve.exception;

public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = -734788709544361930L;

  public NotFoundException(final String message) {
    super(message);
  }
}
