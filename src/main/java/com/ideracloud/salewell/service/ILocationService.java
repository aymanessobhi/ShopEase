package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.CategoryDto;
import com.ideracloud.salewell.dto.LocationDto;

import java.util.List;

public interface ILocationService {
    List<LocationDto> getAll();
    LocationDto get(Long id);
    LocationDto create(LocationDto request);
    LocationDto update(LocationDto dto);
}
