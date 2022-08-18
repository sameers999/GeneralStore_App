package com.imi.order_service.model;

import com.imi.order_service.dto.OrderDTO;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "orderDetils")
public @Data class Order {
    @Id
    @GeneratedValue
    private Integer orderID;
    private LocalDate date = LocalDate.now();
    private Integer price;
    private Integer quantity;
    private String address;
    private Integer userId;
    private Integer productId;
    private boolean cancel;

    public Order() {
        super();
    }

    public Order(OrderDTO dto) {
        super();
        this.quantity = dto.getQuantity();
        this.address = dto.getAddress();
        this.userId = dto.getUserId();
        this.productId = dto.getProductId();
        this.cancel = dto.isCancel();
    }
}
