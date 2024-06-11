package com.storelines.storelines.responseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.storelines.storelines.variant.bean.model.VariantModel;
import com.storelines.storelines.util.BaseResponseEntity;
import lombok.Data;

import java.util.List;

@Data
public class VariantResponseModel implements BaseResponseEntity {
    @JsonIgnore
    private List<VariantModel> jsonList;
    @JsonIgnore
    private VariantModel jsonObject;

    @JsonProperty("json")
    public Object getJson() {
        if (jsonList != null) {
            return jsonList;
        } else {
            return jsonObject;
        }
    }

    public VariantResponseModel() {}
    public VariantResponseModel(VariantModel variantModel) {
        this.jsonObject = variantModel;
    }
    public VariantResponseModel(List<VariantModel> variantModelList) {
        this.jsonList = variantModelList;
    }
}