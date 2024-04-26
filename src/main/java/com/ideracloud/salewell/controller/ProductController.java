package com.ideracloud.salewell.controller;

import ma.ideratech.stock.common.BadRequestException;
import ma.ideratech.stock.dto.*;
import ma.ideratech.stock.dto.request.SearchRequest;
import ma.ideratech.stock.dto.response.ApiResponse;
import ma.ideratech.stock.service.impl.ProductService;
import ma.ideratech.stock.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController{

    @Autowired
    ProductService service;

    @GetMapping({"getPage"})
    public ApiResponse<Pager<ProductDto>> getPage(Pageable pageable) {
        return ApiResponse.ok(service.getPage(pageable));
    }

    @GetMapping({"getAll"})
    public ApiResponse<List<CategoryDto>> getAll() {
        return ApiResponse.ok(service.getAll());
    }

    @GetMapping({"{id}"})
    public ApiResponse<ProductDto> getOne(@PathVariable Long id) {
        return ApiResponse.ok(service.get(id));
    }

    @GetMapping({"/barcode/{barcode}"})
    public ApiResponse<ProductDto> getByBarcode(@PathVariable String barcode) {
        return ApiResponse.ok(service.findByBarcode(barcode));
    }

    @PostMapping({"search"})
    public ApiResponse<Pager<ProductDto>> search(@RequestBody SearchRequest<ProductSearchDto> dto) {
        return ApiResponse.ok(service.search(dto));
    }

    @PostMapping({"create"})
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody @Valid ProductDto dto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result, ResourceUtil.getMessage("Invalid request"));
        } else {
            return ApiResponse.ok(HttpStatus.CREATED, service.create(dto), "Object successfuly created");
        }
    }

    @PutMapping({"update"})
    public ApiResponse<ProductDto> update(@RequestBody @Valid ProductDto dto) {
        return ApiResponse.ok(service.update(dto));
    }


    @PutMapping("image/update")
    public ResponseEntity<ProductImageDto> updateImage(@RequestBody @Valid ProductImageDto dto){
        return ResponseEntity.ok(service.updateImage(dto));
    }

    @DeleteMapping("image/delete/{fileId}")
    public void deleteFile(@PathVariable Long fileId) {
        service.deleteImage(fileId);
    }
}
