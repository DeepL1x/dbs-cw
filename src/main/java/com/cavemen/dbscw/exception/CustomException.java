package com.cavemen.dbscw.exception;

public class CustomException extends RuntimeException{

  private final ErrorCode errorCode;

  public CustomException(ErrorCode errorCode){
    this.errorCode = errorCode;
  }
}

