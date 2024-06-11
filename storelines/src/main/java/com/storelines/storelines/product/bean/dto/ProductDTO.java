package com.storelines.storelines.product.bean.dto;

import com.storelines.storelines.variant.bean.dto.VariantDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String id;
    private String category;
    private int minPrice;
    private VariantDTO minPriceVariant;
    private VariantDTO maxPriceVariant;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<VariantDTO> variants;
}
