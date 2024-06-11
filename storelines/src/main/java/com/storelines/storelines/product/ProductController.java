package com.storelines.storelines.product;

import com.storelines.storelines.product.bean.dto.ProductDTO;
import com.storelines.storelines.product.bean.ProductDTOMapper;
import com.storelines.storelines.variant.bean.VariantDTOMapper;
import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.product.bean.model.ProductModel;
import com.storelines.storelines.variant.bean.model.VariantModel;
import com.storelines.storelines.responseModel.ProductResponseModel;
import com.storelines.storelines.product.service.ProductService;
import com.storelines.storelines.variant.service.VariantService;
import com.storelines.storelines.util.ResponseEnvelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private VariantService variantService;

    @PostMapping
    @Transactional(rollbackFor = {ServiceException.class, DAOException.class, Exception.class})
    public ResponseEntity<ResponseEnvelope> addProduct(@RequestBody ProductDTO productDTO) {
        ProductModel productModel = ProductDTOMapper.convert(productDTO);

            try {
                ProductModel productModelResponse = productService.saveProduct(productModel);
                List<VariantModel> variantDTOList = VariantDTOMapper.convert(productDTO.getVariants(), productModelResponse.getId());
                variantService.saveVariantList(variantDTOList);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value(), productModelResponse), HttpStatus.OK);
            } catch (ServiceException e) {
                return new ResponseEntity<>(
                        new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = {ServiceException.class, DAOException.class, Exception.class})
    public ResponseEntity<ResponseEnvelope> deleteProduct(@PathVariable String id) {
        try {
            variantService.deleteVariantByProductID(id);
            productService.deleteProduct(id);
            return new ResponseEntity<>(
                    new ResponseEnvelope(HttpStatus.OK.value()), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(
                    new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseEnvelope> getProductById(@PathVariable String id) {
        try {
            ProductModel productModel = productService.getProductById(id);
            if (productModel != null) {
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value(), new ProductResponseModel(productModel)), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value(), new ProductResponseModel()), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseEnvelope> getProducts(@RequestParam(required = false) String category,
                                                                @RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "20") int pageSize,
                                                                @RequestParam(required = false) String sortBy,
                                                                @RequestParam(required = false, defaultValue = "0") int sortOrder) {
        try {
            Page<ProductModel> productModelList = productService.getProducts(category, page, pageSize, sortBy, sortOrder);
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value(), new ProductResponseModel(productModelList)), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
