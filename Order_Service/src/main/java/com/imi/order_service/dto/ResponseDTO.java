package com.imi.order_service.dto;

import lombok.Data;

public @Data class ResponseDTO {
    private String message;
    private Object order;

    public ResponseDTO(String message, Object order) {
        super();
        this.message = message;
        this.order = order;
    }

    public ResponseDTO() {
        super();
    }
}
