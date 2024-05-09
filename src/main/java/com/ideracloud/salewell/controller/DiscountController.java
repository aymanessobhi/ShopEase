package com.ideracloud.salewell.controller;


import com.ideracloud.salewell.dto.ApiResponse;
import com.ideracloud.salewell.dto.DiscountDto;
import com.ideracloud.salewell.dto.LocationDto;
import com.ideracloud.salewell.exception.BadRequestException;
import com.ideracloud.salewell.service.impl.DiscountService;
import com.ideracloud.salewell.utils.ResourceUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@CrossOrigin("*")
public class DiscountController {
    @Autowired
    DiscountService service;
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody @Valid DiscountDto dto, BindingResult result){
        if (result.hasErrors()) {
            throw new BadRequestException(result, ResourceUtil.getMessage("Invalid request"));
        } else {
            return ApiResponse.ok(HttpStatus.CREATED, service.create(dto), "Object successfuly created");
        }
    }

    @GetMapping("getAll")
    public ApiResponse<List<DiscountDto>> getAll() {
        return ApiResponse.ok(service.getAll());
    }
}
