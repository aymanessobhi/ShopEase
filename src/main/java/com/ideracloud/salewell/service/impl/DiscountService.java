package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Discount;
import com.ideracloud.salewell.dto.CountryDto;
import com.ideracloud.salewell.dto.DiscountDto;
import com.ideracloud.salewell.mapper.DiscountMapper;
import com.ideracloud.salewell.repository.DiscountRepository;
import com.ideracloud.salewell.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<DiscountDto> getAll() {
        List<Discount> entities = repository.findAll();
        return mapper.toDtos(entities);
    }
}
