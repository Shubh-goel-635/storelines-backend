package com.storelines.storelines.dto;

import com.storelines.storelines.model.VariantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
