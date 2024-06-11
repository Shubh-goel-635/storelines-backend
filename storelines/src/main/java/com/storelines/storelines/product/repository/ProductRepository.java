package com.storelines.storelines.product.repository;

import com.storelines.storelines.product.bean.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {
    Page<ProductModel> findAll(Pageable pageable);
    Page<ProductModel> findAllByOrderByMinPriceAsc(Pageable pageable);
    Page<ProductModel> findAllByOrderByMinPriceDesc(Pageable pageable);
    Page<ProductModel> findAllByOrderByUpdatedAtAsc(Pageable pageable);
    Page<ProductModel> findAllByOrderByUpdatedAtDesc(Pageable pageable);
    Page<ProductModel> findByCategory(String category, Pageable pageable);
    Page<ProductModel> findByCategoryOrderByMinPriceAsc(String category, Pageable pageable);
    Page<ProductModel> findByCategoryOrderByMinPriceDesc(String category, Pageable pageable);
    Page<ProductModel> findByCategoryOrderByUpdatedAtAsc(String category, Pageable pageable);
    Page<ProductModel> findByCategoryOrderByUpdatedAtDesc(String category, Pageable pageable);

}

