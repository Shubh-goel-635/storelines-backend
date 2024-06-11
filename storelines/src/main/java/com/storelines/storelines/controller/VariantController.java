package com.storelines.storelines.controller;

import com.storelines.storelines.dto.VariantDTO;
import com.storelines.storelines.dtoMapper.VariantDTOMapper;
import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.model.VariantModel;
import com.storelines.storelines.responseModel.VariantResponseModel;
import com.storelines.storelines.service.VariantService;
import com.storelines.storelines.util.ResponseEnvelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variant")
public class VariantController {

    @Autowired
    private VariantService variantService;

    @PutMapping
    public ResponseEntity<ResponseEnvelope> editVariant (@RequestBody VariantDTO variantDTO) {
        VariantModel variantModel = VariantDTOMapper.convert(variantDTO);
        try {
            VariantModel variantResponseModel = variantService.editVariant(variantModel);
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value(), new VariantResponseModel(variantResponseModel)), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{vid}")
    public ResponseEntity<ResponseEnvelope> getAllVariantsByVid(@PathVariable String vid) {
        try {
            VariantModel variantWithVid = variantService.getByVid(vid);
            if (variantWithVid == null) {
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.NO_CONTENT.value(), "No variant found with this vid"), HttpStatus.OK);
            }
            String pid = variantWithVid.getPid();

            List<VariantModel> variantsWithPid = variantService.getByPid(pid);
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value(), new VariantResponseModel(variantsWithPid)), HttpStatus.OK);

        } catch (ServiceException e) {
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
