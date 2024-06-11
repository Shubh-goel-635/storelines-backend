package com.storelines.storelines.repository;

import com.storelines.storelines.model.ProductModel;
import com.storelines.storelines.model.VariantModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends MongoRepository<VariantModel, String> {
    void deleteByPid(String id);
    VariantModel findByVid(String vid);
    List<VariantModel> findByPid(String pid);
}

