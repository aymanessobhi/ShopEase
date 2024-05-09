package com.ideracloud.salewell.service.impl;
import com.ideracloud.salewell.domain.Country;
import com.ideracloud.salewell.domain.Staff;
import com.ideracloud.salewell.dto.StaffDto;
import com.ideracloud.salewell.exception.DataNotFoundException;
import com.ideracloud.salewell.mapper.StaffMapper;
import com.ideracloud.salewell.repository.CountryRepository;
import com.ideracloud.salewell.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ideracloud.salewell.service.IStaffService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StaffService  implements IStaffService {
    @Autowired
    StaffMapper mapper;
    @Autowired
    StaffRepository repository;
    @Autowired
    CountryRepository countryRepository;
    @Override
    public List<StaffDto> getAll() {
        List<Staff> entities = this.repository.findAll();
        return this.mapper.toDtos(entities);
    }

    @Override
    public StaffDto get(Long id) {
        Staff obj = repository.findById(id).orElseThrow(() -> {
            return new DataNotFoundException("Data not found for given parameters {} :" + id);
        });
        return this.mapper.toDto(obj);
    }

    @Override
    public StaffDto create(StaffDto dto) {
        Staff entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public StaffDto update(StaffDto dto) {

        return repository.findById(dto.getId())
                .map(entity -> {
                    entity.setName(dto.getName());
                    entity.setAccess(dto.getAccess());
                    entity.setPosRole(dto.getPosRole());
//                    entity.setPostalCode(dto.getPostalCode());
//                    entity.setLocation(dto.getLocation());
                    
                    return mapper.toDto(repository.save(entity));
                })
                .orElseGet(() -> dto);
    }

}
