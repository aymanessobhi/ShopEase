package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.CountryDto;
import com.ideracloud.salewell.dto.DiscountDto;
import com.ideracloud.salewell.dto.LocationDto;

import java.util.List;

public interface IDiscountService {
    DiscountDto create(DiscountDto request);
    List<DiscountDto> getAll();
    DiscountDto updateDiscount(DiscountDto dto);
    void deleteDiscount(Long id);
    DiscountDto findDiscountById(Long id);
}
