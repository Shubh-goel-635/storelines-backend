package com.storelines.storelines.variant.bean.model;

import com.storelines.storelines.util.BaseResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "variants")
public class VariantModel implements BaseResponseEntity {
    @Id
    private String id;

    @Field("pid")
    @Indexed
    private String pid;

    @Field("vid")
    @Indexed(unique = true)
    private String vid;

    @Field("price")
    private int price;

    @Field("category")
    private String category;

    @Field("buyLink")
    private String buyLink;

    @Field("feature")
    private String feature;

    @Field("images")
    private List<String> images;

    @Field("description")
    private String description;

    @Field("variant")
    private String variant;

    @Field("createdAt")
    private LocalDateTime createdAt;

    @Indexed
    @Field("updatedAt")
    private LocalDateTime updatedAt;
}
