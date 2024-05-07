package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Discount;
import com.ideracloud.salewell.dto.DiscountDto;
import com.ideracloud.salewell.mapper.DiscountMapper;
import com.ideracloud.salewell.repository.DiscountRepository;
import com.ideracloud.salewell.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService implements IDiscountService {
    @Autowired
    DiscountMapper mapper;
    @Autowired
    DiscountRepository repository;
    @Override
    public DiscountDto create(DiscountDto dto) {
        Discount entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }
}
