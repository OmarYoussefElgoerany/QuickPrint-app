package com.screen.quickprint.common.exception.permission_exception;

public class PermissionCreationException extends RuntimeException {
  public PermissionCreationException(String message) {
    super(message);
  }

  public PermissionCreationException(String message, Throwable cause) {
    super(message, cause);
  }
}
