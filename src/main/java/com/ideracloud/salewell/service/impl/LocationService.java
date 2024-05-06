package com.ideracloud.salewell.service.impl;

import com.ideracloud.salewell.domain.Country;
import com.ideracloud.salewell.domain.Location;
import com.ideracloud.salewell.dto.LocationDto;
import com.ideracloud.salewell.exception.DataNotFoundException;
import com.ideracloud.salewell.mapper.LocationMapper;
import com.ideracloud.salewell.repository.CountryRepository;
import com.ideracloud.salewell.repository.LocationRepository;
import com.ideracloud.salewell.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    LocationMapper mapper;
    @Autowired
    LocationRepository repository;
    @Autowired
    CountryRepository countryRepository;
    @Override
    public List<LocationDto> getAll() {
        List<Location> entities = this.repository.findAll();
        return this.mapper.toDtos(entities);
    }

    @Override
    public LocationDto get(Long id) {
        Location obj = repository.findById(id).orElseThrow(() -> {
            return new DataNotFoundException("Data not found for given parameters {} :" + id);
        });
        return this.mapper.toDto(obj);
    }

    @Override
    public LocationDto create(LocationDto request) {
        Location entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public LocationDto update(LocationDto dto) {
        Country dbCountry = countryRepository.findById(dto.getCountry().getId()).orElse(null);

        return repository.findById(dto.getId())
                .map(entity -> {
                    entity.setName(dto.getName());
                    entity.setAddress(dto.getAddress());
                    entity.setAppartment(dto.getAppartment());
                    entity.setPostalCode(dto.getPostalCode());
                    entity.setCity(dto.getCity());
                    entity.setPhone(dto.getPhone());
                    entity.setFulfillOnlineOrders(dto.isFulfillOnlineOrders());
                    entity.setPosEnabled(dto.isPosEnabled());

                    if (dbCountry != null) {
                        entity.setCountry(dbCountry);
                    }
                    return mapper.toDto(repository.save(entity));
                })
                .orElseGet(() -> dto);
    }

}
