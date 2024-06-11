package com.storelines.storelines.service;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.model.VariantModel;

import java.util.List;

public interface VariantService {
    List<VariantModel> saveVariantList(List<VariantModel> variantModelList) throws ServiceException;
    VariantModel editVariant(VariantModel variantModel) throws ServiceException;
    void deleteVariantByProductID(String id) throws ServiceException;

    VariantModel getByVid(String vid) throws ServiceException;
    List<VariantModel> getByPid(String pid) throws ServiceException;

}
