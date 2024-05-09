package com.ideracloud.salewell.controller;



import com.ideracloud.salewell.dto.ApiResponse;
import com.ideracloud.salewell.dto.CategoryDto;
import com.ideracloud.salewell.dto.StaffDto;
import com.ideracloud.salewell.dto.ProductDto;
import com.ideracloud.salewell.exception.BadRequestException;
import com.ideracloud.salewell.service.impl.StaffService;
import com.ideracloud.salewell.service.impl.ProductService;
import com.ideracloud.salewell.utils.ResourceUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin("*")

public class StaffController {

    @Autowired
    StaffService service;
    @GetMapping({"getAll"})
    public ApiResponse<List<StaffDto>> getAll() {
        return ApiResponse.ok(service.getAll());
    }

    @GetMapping({"{id}"})
    public ApiResponse<StaffDto> getOne(@PathVariable Long id) {
        return ApiResponse.ok(service.get(id));
    }

    @PostMapping({"create"})
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody @Valid StaffDto dto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result, ResourceUtil.getMessage("Invalid request"));
        } else {
            return ApiResponse.ok(HttpStatus.CREATED, service.create(dto), "Object successfuly created");
        }
    }
    @PutMapping({"update"})
    public ApiResponse<StaffDto> update(@RequestBody @Valid StaffDto dto) {
        return ApiResponse.ok(service.update(dto));
    }

}
