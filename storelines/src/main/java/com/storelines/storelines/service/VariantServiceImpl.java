package com.storelines.storelines.service;

import com.storelines.storelines.dao.VariantDAO;
import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.model.VariantModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {
    @Autowired
    private VariantDAO variantDAO;
    @Override
    public List<VariantModel> saveVariantList(List<VariantModel> variantModelList) throws ServiceException {
        try{
            return variantDAO.saveVariantList(variantModelList);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public VariantModel editVariant(VariantModel variantModel) throws ServiceException {
        try{
            return variantDAO.editVariant(variantModel);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteVariantByProductID(String id) throws ServiceException {
        try{
            variantDAO.deleteVariantByProductID(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public VariantModel getByVid(String vid) throws ServiceException {
        try {
            return variantDAO.getByVid(vid);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<VariantModel> getByPid(String pid) throws ServiceException {
        try {
            return variantDAO.getByPid(pid);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
