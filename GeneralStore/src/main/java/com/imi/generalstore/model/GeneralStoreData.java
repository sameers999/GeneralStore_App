package com.imi.generalstore.model;

import com.imi.generalstore.dto.GeneralStoreDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "general-store")
public @Data class GeneralStoreData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_code")
    private Long productId ;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_quantity")
    private String   productQuantity;
    @Column(name = "product_price")
    private String  productPrice;
    @Column(name = "product_image")
    private String productImage;

    public GeneralStoreData(GeneralStoreDTO dto) {
        super();
        this.productName = dto.getProductName();
        this.productQuantity = dto.getQuantity();
        this.productPrice = dto.getPrice();
        this.productImage = dto.getProductImage();
    }

    public GeneralStoreData(long productId, GeneralStoreDTO dto) {
        super();
        this.productId = productId;
        this.productName = dto.getProductName();
        this.productQuantity = dto.getQuantity();
        this.productPrice = dto.getPrice();
        this.productImage = dto.getProductImage();
    }

    public GeneralStoreData() {
        super();
 }
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public String getProductQuantity() {
//        return productQuantity;
//    }
//
//    public void setProductQuantity(String productQuantity) {
//        this.productQuantity = productQuantity;
//    }
//
//    public String getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(String productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public String getProductImage() {
//        return productImage;
//    }
//
//    public void setProductImage(String productImage) {
//        this.productImage = productImage;
//    }
}
