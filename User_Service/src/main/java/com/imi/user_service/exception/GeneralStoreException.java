package com.imi.user_service.exception;

import lombok.Data;

@Data
public class GeneralStoreException extends RuntimeException {
    public GeneralStoreException(String message) {
        super(message);
    }
}
