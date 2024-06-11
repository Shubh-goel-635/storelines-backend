package com.storelines.storelines.responseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.storelines.storelines.product.bean.model.ProductModel;
import com.storelines.storelines.util.BaseResponseEntity;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ProductResponseModel implements BaseResponseEntity {
    @JsonIgnore
    private List<ProductModel> jsonList;
    @JsonIgnore
    private Page<ProductModel> jsonPage;
    @JsonIgnore
    private ProductModel jsonObject;

    @JsonProperty("json")
    public Object getJson() {
        if (jsonList != null) {
            return jsonList;
        } else if (jsonPage != null) {
            return jsonPage;
        } else {
            return jsonObject;
        }
    }

    public ProductResponseModel() {}

    public ProductResponseModel(List<ProductModel> json) { this.jsonList = json; }

    public ProductResponseModel(ProductModel productModel) {
        this.jsonObject = productModel;
    }

    public ProductResponseModel(Page<ProductModel> productModel) {
        this.jsonPage = productModel;
    }
}