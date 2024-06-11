package com.storelines.storelines.product.bean.model;

import com.storelines.storelines.variant.bean.model.VariantModel;
import com.storelines.storelines.util.BaseResponseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class ProductModel implements BaseResponseEntity {
    @Id
    private String id;

    @Indexed
    @Field(name = "category")
    private String category;

    @Indexed
    @Field(name = "minPrice")
    private int minPrice;

    @Field(name = "firstVariant")
    private VariantModel firstVariant;

    @Field(name = "minPriceVariant")
    private VariantModel minPriceVariant;

    @Field(name = "maxPriceVariant")
    private VariantModel maxPriceVariant;

    @Field(name = "createdAt")
    private LocalDateTime createdAt;

    @Indexed
    @Field(name = "updatedAt")
    private LocalDateTime updatedAt;
}
