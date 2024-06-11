package com.storelines.storelines.dtoMapper;

import com.storelines.storelines.dto.ProductDTO;
import com.storelines.storelines.dto.VariantDTO;
import com.storelines.storelines.model.ProductModel;
import com.storelines.storelines.model.VariantModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDTOMapper {

    public ProductDTOMapper() {}

    public static ProductModel convert(ProductDTO productDTO) {
        ProductModel productModel = new ProductModel();
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = Integer.MIN_VALUE;
        int minPriceVariantIndex = -1;
        int maxPriceVariantIndex = -1;

        for (int i = 0; i < productDTO.getVariants().size(); i++) {
            VariantDTO variant = productDTO.getVariants().get(i);
            if (variant.getPrice() < minPrice) {
                minPrice = variant.getPrice();
                minPriceVariantIndex = i;
            }
            if (variant.getPrice() > maxPrice) {
                maxPrice = variant.getPrice();
                maxPriceVariantIndex = i;
            }
        }

        productModel.setId(productDTO.getId());
        productModel.setCategory(productDTO.getVariants().get(0).getCategory());
        productModel.setMinPrice(minPrice);
        productModel.setFirstVariant(VariantDTOMapper.convert(productDTO.getVariants().get(0)));
        productModel.setMinPriceVariant(VariantDTOMapper.convert(productDTO.getVariants().get(minPriceVariantIndex)));
        productModel.setMaxPriceVariant(VariantDTOMapper.convert(productDTO.getVariants().get(maxPriceVariantIndex)));
        productModel.setCreatedAt(productDTO.getCreatedAt() == null ? LocalDateTime.now() : productDTO.getCreatedAt());
        productModel.setUpdatedAt(LocalDateTime.now());



        return productModel;
    }
}
