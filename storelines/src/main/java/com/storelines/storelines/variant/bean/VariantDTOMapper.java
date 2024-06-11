package com.storelines.storelines.variant.bean;

import com.storelines.storelines.variant.bean.dto.VariantDTO;
import com.storelines.storelines.variant.bean.model.VariantModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VariantDTOMapper {

    public VariantDTOMapper() {
    }

    public static VariantModel convert(VariantDTO variantDTO) {
        return convert(variantDTO, null);
    }

    public static VariantModel convert(VariantDTO variantDTO, String pid) {
        VariantModel variantModel = new VariantModel();

        variantModel.setId(variantDTO.getId());
        variantModel.setPid(pid != null ? pid : variantDTO.getPid());
        variantModel.setVid(variantDTO.getVid());
        variantModel.setPrice(variantDTO.getPrice());
        variantModel.setCategory(variantDTO.getCategory());
        variantModel.setBuyLink(variantDTO.getBuyLink());
        variantModel.setFeature(variantDTO.getFeature());
        variantModel.setImages(variantDTO.getImages());
        variantModel.setDescription(variantDTO.getDescription());
        variantModel.setVariant(variantDTO.getVariant());
        variantModel.setCreatedAt(variantDTO.getCreatedAt() == null ? LocalDateTime.now() : variantDTO.getCreatedAt());
        variantModel.setUpdatedAt(LocalDateTime.now());

        return variantModel;
    }

    public static List<VariantModel> convert(List<VariantDTO> variantDTOList, String pid) {
        List<VariantModel> variantModelList = new ArrayList<>();

        for (VariantDTO variantDTO : variantDTOList) {
            variantModelList.add(convert(variantDTO, pid));
        }
        return variantModelList;
    }

}
