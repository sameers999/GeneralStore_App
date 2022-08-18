package com.imi.order_service.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public UserException( HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public UserException(String message) {
        super(message);
    }
}
