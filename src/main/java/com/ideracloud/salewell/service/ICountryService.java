package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.CategoryDto;
import com.ideracloud.salewell.dto.CountryDto;

import java.util.List;

public interface ICountryService {
    List<CountryDto> getAll();
    CountryDto get(Long id);
    CountryDto create(CountryDto request);
    CountryDto update(CountryDto dto);
}
