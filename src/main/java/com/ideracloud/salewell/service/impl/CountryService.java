package com.ideracloud.salewell.service.impl;


import com.ideracloud.salewell.domain.Category;
import com.ideracloud.salewell.domain.Country;
import com.ideracloud.salewell.dto.CountryDto;
import com.ideracloud.salewell.exception.DataNotFoundException;
import com.ideracloud.salewell.mapper.CategoryMapper;
import com.ideracloud.salewell.mapper.CountryMapper;
import com.ideracloud.salewell.repository.CategoryRepository;
import com.ideracloud.salewell.repository.CountryRepository;
import com.ideracloud.salewell.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICountryService {

    @Autowired
    CountryMapper mapper;
    @Autowired
    CountryRepository repository;

    @Override
    public List<CountryDto> getAll() {
        List<Country> entities = this.repository.findAll();
        return this.mapper.toDtos(entities);
    }

    @Override
    public CountryDto get(Long id) {
        Country obj = repository.findById(id).orElseThrow(() -> {
            return new DataNotFoundException("Data not found for given parameters {} :" + id);
        });
        return this.mapper.toDto(obj);
    }

    @Override
    public CountryDto create(CountryDto dto) {
         Country entity = mapper.toEntity(dto);
         return mapper.toDto(repository.save(entity));
    }

    @Override
    public CountryDto update(CountryDto dto) {
        return repository.findById(dto.getId())
                .map(entity -> {
                    entity.setCode(dto.getCode());
                    entity.setDescription(dto.getDescription());
                    return mapper.toDto(repository.save(entity));
                })
                .orElseGet(() -> dto);
    }
}
