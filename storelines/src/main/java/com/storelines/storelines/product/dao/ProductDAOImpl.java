package com.storelines.storelines.product.dao;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.product.bean.model.ProductModel;
import com.storelines.storelines.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductModel saveProduct(ProductModel productModel) throws DAOException {
        try {
            return productRepository.save(productModel);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteProduct(String id) throws DAOException {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getAllProductBy(Pageable pageable) throws DAOException {
        try {
            return productRepository.findAll(pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getAllProductByOrderByMinPriceAsc(Pageable pageable) throws DAOException {
        try {
            return productRepository.findAllByOrderByMinPriceAsc(pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getAllProductByOrderByMinPriceDesc(Pageable pageable) throws DAOException {
        try {
            return productRepository.findAllByOrderByMinPriceDesc(pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getAllProductByOrderByUpdatedAtAsc(Pageable pageable) throws DAOException {
        try {
            return productRepository.findAllByOrderByUpdatedAtAsc(pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getAllProductByOrderByUpdatedAtDesc(Pageable pageable) throws DAOException {
        try {
            return productRepository.findAllByOrderByUpdatedAtDesc(pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public ProductModel getProductById(String id) throws DAOException {
        try {
            Optional<ProductModel> productModel = productRepository.findById(id);
            return  productModel.orElse(null);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getProductByCategory(String category, Pageable pageable) throws DAOException {
        try {
            return productRepository.findByCategory(category, pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getProductByCategoryOrderByMinPriceAsc(String category, Pageable pageable) throws DAOException {
        try {
            return productRepository.findByCategoryOrderByMinPriceAsc(category, pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getProductByCategoryOrderByMinPriceDesc(String category, Pageable pageable) throws DAOException {
        try {
            return productRepository.findByCategoryOrderByMinPriceDesc(category, pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getByCategoryOrderByUpdatedAtAsc(String category, Pageable pageable) throws DAOException {
        try {
            return productRepository.findByCategoryOrderByUpdatedAtDesc(category, pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductModel> getByCategoryOrderByUpdatedAtDesc(String category, Pageable pageable) throws DAOException {
        try {
            return productRepository.findByCategoryOrderByUpdatedAtAsc(category, pageable);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}
