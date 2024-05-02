package com.ideracloud.salewell.controller;

import com.ideracloud.salewell.dto.ApiResponse;
import com.ideracloud.salewell.dto.DataDto;
import com.ideracloud.salewell.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
@CrossOrigin("*")
@Slf4j
public class DataController {

    @Autowired
    DataService dataService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GERANT')")
    @GetMapping("")
    public ApiResponse<DataDto> getData() {
        DataDto data = new DataDto();
        data.setAppliesTo(dataService.loadAppliesTo());

        return ApiResponse.ok(data);
    }
}
