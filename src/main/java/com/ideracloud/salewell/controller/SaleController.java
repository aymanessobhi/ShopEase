package com.ideracloud.salewell.controller;

import lombok.extern.slf4j.Slf4j;
import ma.ideratech.stock.common.BadRequestException;
import ma.ideratech.stock.dto.*;
import ma.ideratech.stock.dto.request.SearchRequest;
import ma.ideratech.stock.dto.response.ApiResponse;
import ma.ideratech.stock.service.impl.SaleService;
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
@RequestMapping("/sale")
@CrossOrigin("*")
@Slf4j
public class SaleController{

    @Autowired
    SaleService service;

    @GetMapping({"getPage"})
    public ApiResponse<Pager<SaleOrderDto>> getPage(Pageable pageable) {
        return ApiResponse.ok(service.getPage(pageable));
    }

    @GetMapping({"getAll"})
    public ApiResponse<List<SaleOrderDto>> getAll() {
        return ApiResponse.ok(service.getAll());
    }

    @GetMapping({"{id}"})
    public ApiResponse<SaleOrderDto> getOne(@PathVariable Long id) {
        return ApiResponse.ok(service.get(id));
    }

    @PostMapping({"search"})
    public ApiResponse<Pager<SaleOrderDto>> search(@RequestBody SearchRequest<SaleOrderSearchDto> dto) {
        return ApiResponse.ok(service.search(dto));
    }

    @PostMapping({"create"})
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody @Valid SaleOrderDto dto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result, ResourceUtil.getMessage("Invalid request"));
        } else {
            return ApiResponse.ok(HttpStatus.CREATED, service.create(dto), "Object successfuly created");
        }
    }

    @PutMapping({"update"})
    public ApiResponse<SaleOrderDto> update(@RequestBody @Valid SaleOrderDto dto) {
        return ApiResponse.ok(service.update(dto));
    }

    @DeleteMapping("{orderId}/{lineId}")
    public ResponseEntity<?> deleteLine(@PathVariable Long orderId, @PathVariable Long lineId){
        try {
            service.deleteLine(orderId,lineId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
        try {
            service.deleteOrder(orderId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
