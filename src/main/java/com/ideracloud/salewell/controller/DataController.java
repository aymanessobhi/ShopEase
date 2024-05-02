package com.ideracloud.salewell.controller;

import com.ideracloud.salewell.dto.ApiResponse;
import com.ideracloud.salewell.dto.DataDto;
import com.ideracloud.salewell.service.DataService;
import com.ideracloud.salewell.service.impl.CountryService;
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
    @Autowired
    CountryService countryService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GERANT')")
    @GetMapping("")
    public ApiResponse<DataDto> getData() {
        DataDto data = new DataDto();
        data.setAppliesTo(dataService.loadAppliesTo());
        data.setCustomerEligib(dataService.loadDiscountCustomerEligibility());
        data.setDiscountMethods(dataService.loadDiscountMethods());
        data.setDiscMinP(dataService.loadDiscountMinPurchases());
        data.setDiscStatus(dataService.loadDiscountStatuses());
        data.setDiscTypes(dataService.loadDiscountTypes());
        data.setDiscountValues(dataService.loadDiscountValues());
        data.setProdStatus(dataService.loadProductStatuses());
        data.setSaleStatus(dataService.loadSaleStatuses());
        data.setUnits(dataService.loadUnitTypes());
        data.setCountries(dataService.loadCountries());
        return ApiResponse.ok(data);
    }
}
