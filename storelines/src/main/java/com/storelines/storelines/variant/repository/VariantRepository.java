package com.storelines.storelines.variant.repository;

import com.storelines.storelines.variant.bean.model.VariantModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends MongoRepository<VariantModel, String> {
    void deleteByPid(String id);
    VariantModel findByVid(String vid);
    List<VariantModel> findByPid(String pid);
}

