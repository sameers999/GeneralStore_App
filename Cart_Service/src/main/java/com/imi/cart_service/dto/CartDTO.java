package com.imi.cart_service.dto;

import lombok.Data;

public @Data class CartDTO {
    private int userId;
    private int productId;
    private int productquantity;
    private int cartID;
}
