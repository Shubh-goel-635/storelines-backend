package com.storelines.storelines.dao;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.model.VariantModel;
import com.storelines.storelines.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VariantDAOImpl implements VariantDAO{
    @Autowired
    private VariantRepository variantRepository;
    @Override
    public List<VariantModel> saveVariantList(List<VariantModel> variantModelList) throws DAOException {
        try {
            return variantRepository.saveAll(variantModelList);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteVariantByProductID(String id) throws DAOException {
        try {
            variantRepository.deleteByPid(id);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public VariantModel editVariant(VariantModel variantModel) throws DAOException {
        try {
            return  variantRepository.save(variantModel);
        } catch (Exception e) {
            throw  new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public VariantModel getByVid(String vid) throws DAOException {
        try {
            return  variantRepository.findByVid(vid);
        } catch (Exception e) {
            throw  new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public List<VariantModel> getByPid(String pid) throws DAOException {
        try {
            return  variantRepository.findByPid(pid);
        } catch (Exception e) {
            throw  new DAOException(e.getMessage(), e);
        }
    }
}
