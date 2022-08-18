package com.imi.generalstore.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GeneralStoreException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public GeneralStoreException(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public GeneralStoreException(String message) {
        super(message);
    }
}
