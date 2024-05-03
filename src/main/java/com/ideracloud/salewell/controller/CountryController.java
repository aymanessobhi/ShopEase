package com.ideracloud.salewell.controller;



import com.ideracloud.salewell.dto.ApiResponse;
import com.ideracloud.salewell.dto.CategoryDto;
import com.ideracloud.salewell.dto.CountryDto;
import com.ideracloud.salewell.exception.BadRequestException;
import com.ideracloud.salewell.service.impl.CountryService;
import com.ideracloud.salewell.utils.ResourceUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@CrossOrigin("*")
public class CountryController {
    @Autowired
    CountryService service;

    @GetMapping({"getAll"})
    public ApiResponse<List<CountryDto>> getAll() {
        return ApiResponse.ok(service.getAll());
    }

    @GetMapping({"/{id}"})
    public ApiResponse<CountryDto> getOne(@PathVariable Long id) {
        return ApiResponse.ok(service.get(id));
    }

    @PostMapping({"create"})
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody @Valid CountryDto dto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result, ResourceUtil.getMessage("Invalid request"));
        } else {
            return ApiResponse.ok(HttpStatus.CREATED, service.create(dto), "Object successfuly created");
        }
    }
    @PutMapping({"update"})
    public ApiResponse<CountryDto> update(@RequestBody @Valid CountryDto dto) {
        return ApiResponse.ok(service.update(dto));
    }

}
