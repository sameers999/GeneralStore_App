package com.imi.cart_service.model;

import com.imi.cart_service.dto.CartDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public @Data class Cart {
    @Id
    @GeneratedValue
    private Integer cartID;
    private int userId;
    private int productId;
    private int productquantity;

    public Cart(Integer cartId, CartDTO dto) {
        super();
        this.cartID=cartId;
        this.userId = dto.getUserId();
        this.productId = dto.getProductId();
        this.productquantity = dto.getProductquantity();
    }


    public Cart() {
        super();
    }
    public Cart(CartDTO dto) {
        this.userId = dto.getUserId();
        this.productId = dto.getProductId();
        this.productquantity = dto.getProductquantity();
    }
}
