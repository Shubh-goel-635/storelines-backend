package com.storelines.storelines.variant.dao;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.variant.bean.model.VariantModel;

import java.util.List;

public interface VariantDAO {
    List<VariantModel> saveVariantList(List<VariantModel> variantModelList) throws DAOException;
    void deleteVariantByProductID(String id) throws DAOException;
    VariantModel editVariant(VariantModel variantModel) throws DAOException;

    VariantModel getByVid(String vid) throws DAOException;
    List<VariantModel> getByPid(String pid) throws DAOException;
}
