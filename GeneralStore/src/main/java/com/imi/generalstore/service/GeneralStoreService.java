package com.imi.generalstore.service;

import com.imi.generalstore.dto.GeneralStoreDTO;
import com.imi.generalstore.exception.GeneralStoreException;
import com.imi.generalstore.model.GeneralStoreData;
import com.imi.generalstore.repository.GeneralStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class GeneralStoreService implements IGeneralStoreService {
    @Autowired
    GeneralStoreRepository repository;


    @Override
    public GeneralStoreData insertGeneralStoreData(GeneralStoreDTO dto) {
        GeneralStoreData generalStoreData = new GeneralStoreData(dto);
        repository.save(generalStoreData);
        return generalStoreData;
    }

    @Override
    public List<GeneralStoreData> getAllProducts() {
        List<GeneralStoreData> list = repository.findAll();
        if (list.isEmpty()) {
            throw new NullPointerException("there are no Products inserted yet");
        }
        return list;
    }

    @Override
    public GeneralStoreData getproductsByID(Long ProductId) {
        Optional<GeneralStoreData> generalStoreData = repository.findById(ProductId);
        if (generalStoreData.isEmpty()) {
            throw new GeneralStoreException("There are no Products with given id");
        }
        return generalStoreData.get();
    }

    @Override
    public List<GeneralStoreData> retrieveProducts() {
        return null;
    }

    @Override
    public GeneralStoreData updateById(Long productId, GeneralStoreDTO dto) {
        Optional<GeneralStoreData> generalStoreData = repository.findById(productId);
        if (generalStoreData.isPresent()) {
            GeneralStoreData newGeneralStoreData = new GeneralStoreData(productId,dto);
            repository.save(newGeneralStoreData);
            return newGeneralStoreData;
        } else {
            throw new GeneralStoreException("GeneralStoreData is not Found");
        }
    }

    @Override
    public GeneralStoreData deleteById(Long productId) {
        Optional<GeneralStoreData> generalStoreData = repository.findById(productId);
        if (generalStoreData.isEmpty()) {
            throw new GeneralStoreException("Invalid ProductId.. please input valid Id");
        }
        repository.deleteById(productId);
        return generalStoreData.get();
    }

    @Override
    public GeneralStoreData getproductByProductName(String productName) {
        return null;
    }

    @Override
    public List<GeneralStoreData> sortGeneralStoresAsc() {
        return null;
    }

    @Override
    public List<GeneralStoreData> sortGeneralStoresDesc() {
        return null;
    }

    @Override
    public GeneralStoreData updateProductQuantity(long productId, String productQuantity) {
        return null;
    }

    @Override
    public GeneralStoreData getproductByIdAPI(Long productId) {
        Optional<GeneralStoreData> generalStoreData =repository.findById(productId);
        if (generalStoreData.isEmpty()){
            throw new GeneralStoreException("there are no Productswith given Id");
        }
        return generalStoreData.get();
    }
}
