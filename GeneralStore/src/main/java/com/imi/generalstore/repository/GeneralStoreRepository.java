package com.imi.generalstore.repository;

import com.imi.generalstore.model.GeneralStoreData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface GeneralStoreRepository extends JpaRepository<GeneralStoreData,Long> {
    @Query(value = "SELECT * from general-store WHERE product_name= :productName%",nativeQuery = true)
    Optional<GeneralStoreData> findByProductName(String productName);

    @Query(value = "SELECT * from general-store WHERE product_code= :productId",nativeQuery = true)
  Optional<GeneralStoreData> findByProductId(Long productId);

//    @Query(value = "SELECT * from general-store ORDER BY price", nativeQuery = true)
//    List<GeneralStoreData> sortGeneralStoresDesc();
//
//    @Query(value = "SELECT * from general-store ORDER BY price DESC", nativeQuery = true)
//    List<GeneralStoreData> sortGeneralStoresAsc();


}
