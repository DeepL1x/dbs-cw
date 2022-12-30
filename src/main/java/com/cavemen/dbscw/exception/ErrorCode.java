package com.cavemen.dbscw.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

  Invalid_User_Login_Or_Password(HttpStatus.NOT_FOUND, "No user login or password found");

  public final HttpStatus httpStatus;
  public final String message;

  ErrorCode(HttpStatus httpStatus, String errorMessage) {
    this.httpStatus = httpStatus;
    this.message = errorMessage;
  }
}
