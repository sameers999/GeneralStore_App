package com.imi.generalstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class GeneralStoreDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid First Letter Should be Upper case in ProductName!!")
    private String productName;
    @NotBlank(message = "quantity can not be empty")
    private String quantity;
    @NotNull(message = "price can not be empty")
    private String price;
    @NotNull(message = "image can not be empty")
    private String productImage;
}
