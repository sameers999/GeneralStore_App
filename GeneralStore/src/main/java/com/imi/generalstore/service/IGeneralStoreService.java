package com.imi.generalstore.service;

import com.imi.generalstore.dto.GeneralStoreDTO;
import com.imi.generalstore.model.GeneralStoreData;

import java.util.List;

public interface IGeneralStoreService {
    GeneralStoreData insertGeneralStoreData(GeneralStoreDTO dto);

    List<GeneralStoreData> getAllProducts();

    GeneralStoreData getproductsByID(Long ProductId);
    List<GeneralStoreData> retrieveProducts();

    GeneralStoreData updateById(Long ProductId,GeneralStoreDTO dto);

    GeneralStoreData deleteById(Long ProductId);

    GeneralStoreData getproductByProductName(String productName);

    List<GeneralStoreData> sortGeneralStoresAsc();
    List<GeneralStoreData> sortGeneralStoresDesc();

    GeneralStoreData updateProductQuantity(long productId, String productQuantity);

    GeneralStoreData getproductByIdAPI(Long productId);
}
