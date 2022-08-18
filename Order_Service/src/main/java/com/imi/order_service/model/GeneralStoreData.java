package com.imi.order_service.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class GeneralStoreData {
    @Id
    @GeneratedValue

    private Long productId;
    private String  productName;
    private String productImg;
    private Integer productPrice;
    private Integer productQuantity;

    public GeneralStoreData(GeneralStoreData generalStoreData) {
        super();
        this.productId = generalStoreData.getProductId();
        this.productName = generalStoreData.getProductName();
        this.productImg = generalStoreData.getProductImg();
        this.productPrice = generalStoreData.getProductPrice();
        this.productQuantity = generalStoreData.getProductQuantity();
    }

    public GeneralStoreData() {
        super();
    }
}
