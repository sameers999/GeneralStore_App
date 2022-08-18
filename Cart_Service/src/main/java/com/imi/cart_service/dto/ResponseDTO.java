package com.imi.cart_service.dto;

import lombok.Data;

public @Data class ResponseDTO {
    private String message;
    private Object object;

    public ResponseDTO(String message, Object insertItemsInCart) {
        this.message=message;
        this.object=insertItemsInCart;
    }
}
