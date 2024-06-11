package com.storelines.storelines.product.service;

import com.storelines.storelines.product.dao.ProductDAO;
import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.product.bean.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDAO productDAO;

    @Override
    public ProductModel saveProduct(ProductModel productModel) throws ServiceException {
        try {
            return productDAO.saveProduct(productModel);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteProduct(String id) throws ServiceException {
        try {
            productDAO.deleteProduct(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ProductModel getProductById(String id) throws ServiceException {
        try {
            return productDAO.getProductById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getProducts(String category, int page, int pageSize, String sortBy, int sortOrder) throws ServiceException {
        try {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            if (category == null) {
                if (sortBy != null && sortBy.equalsIgnoreCase("price")) {
                    return (sortOrder == 0) ? productDAO.getAllProductByOrderByMinPriceAsc(pageable) : productDAO.getAllProductByOrderByMinPriceDesc(pageable);
                } else if ( sortBy != null && sortBy.equalsIgnoreCase("time")) {
                    return (sortOrder == 0) ? productDAO.getAllProductByOrderByUpdatedAtAsc(pageable) : productDAO.getAllProductByOrderByUpdatedAtDesc(pageable);
                } else {
                    return productDAO.getAllProductBy(pageable);
                }
            } else {
                if (sortBy != null && sortBy.equalsIgnoreCase("price")) {
                    return (sortOrder == 0) ? productDAO.getProductByCategoryOrderByMinPriceAsc(category, pageable) : productDAO.getProductByCategoryOrderByMinPriceDesc(category, pageable);
                } else if ( sortBy != null && sortBy.equalsIgnoreCase("time")) {
                    return (sortOrder == 0) ? productDAO.getByCategoryOrderByUpdatedAtAsc(category, pageable) : productDAO.getByCategoryOrderByUpdatedAtDesc(category, pageable);
                } else {
                    return productDAO.getProductByCategory(category, pageable);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
