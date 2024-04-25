package com.ideracloud.salewell.controller;

import com.ideracloud.geomap.geomapapi.dto.ApiResponse;
import com.ideracloud.geomap.geomapapi.dto.DataDto;
import com.ideracloud.geomap.geomapapi.service.DataService;
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
        data.setTotalPFemales(dataService.getTotalPFemales());
        data.setTotalPlanteurs(dataService.getTotalPlanteurs());
        data.setTotalUsers(dataService.getTotalUsers());
        data.setTotalPlantations(dataService.getTotalPlantations());
        data.setTotalPMales(dataService.getTotalPMales());
        data.setPlantationTypes(dataService.getPlantationsTypes());
        return ApiResponse.ok(data);
    }
}
