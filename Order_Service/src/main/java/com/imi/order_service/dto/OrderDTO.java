package com.imi.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public @Data class OrderDTO {
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    @NotEmpty(message = "Please provide address")
    private String address;
    private boolean cancel;
}
