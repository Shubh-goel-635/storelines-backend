package com.storelines.storelines.dao;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductDAO {
    ProductModel saveProduct(ProductModel productModel) throws DAOException;
    void deleteProduct(String id) throws DAOException;
    Page<ProductModel> getAllProductBy(Pageable pageable) throws DAOException;
    Page<ProductModel> getAllProductByOrderByMinPriceAsc(Pageable pageable) throws DAOException;
    Page<ProductModel> getAllProductByOrderByMinPriceDesc(Pageable pageable) throws DAOException;
    Page<ProductModel> getAllProductByOrderByUpdatedAtAsc(Pageable pageable) throws DAOException;
    Page<ProductModel> getAllProductByOrderByUpdatedAtDesc(Pageable pageable) throws DAOException;
    ProductModel getProductById(String id) throws DAOException;
    Page<ProductModel> getProductByCategory(String category, Pageable pageable) throws DAOException;
    Page<ProductModel> getProductByCategoryOrderByMinPriceAsc(String category, Pageable pageable) throws DAOException;
    Page<ProductModel> getProductByCategoryOrderByMinPriceDesc(String category, Pageable pageable) throws DAOException;
    Page<ProductModel> getByCategoryOrderByUpdatedAtAsc(String category, Pageable pageable) throws DAOException;
    Page<ProductModel> getByCategoryOrderByUpdatedAtDesc(String category, Pageable pageable) throws DAOException;
}
