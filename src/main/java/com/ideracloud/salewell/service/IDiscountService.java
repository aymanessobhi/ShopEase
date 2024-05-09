package com.ideracloud.salewell.service;

import com.ideracloud.salewell.dto.CountryDto;
import com.ideracloud.salewell.dto.DiscountDto;

import java.util.List;

public interface IDiscountService {
    DiscountDto create(DiscountDto request);
    List<DiscountDto> getAll();
}
