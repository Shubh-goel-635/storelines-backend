package com.storelines.storelines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariantDTO {
    private String id;
    private String pid;
    private String vid;
    private int price;
    private String category;
    private String buyLink;
    private String feature;
    private List<String> images;
    private String description;
    private String variant;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
