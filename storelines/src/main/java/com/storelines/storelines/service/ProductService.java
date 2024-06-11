package com.storelines.storelines.service;

import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.model.ProductModel;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductModel saveProduct(ProductModel productModel) throws ServiceException;
    void deleteProduct(String id) throws ServiceException;
    ProductModel getProductById(String id) throws ServiceException;
    Page<ProductModel> getProducts(String category, int page, int pageSize, String sortBy, int sortOrder) throws ServiceException;
}
